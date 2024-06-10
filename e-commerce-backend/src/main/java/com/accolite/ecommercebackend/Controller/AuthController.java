package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.Exception.IncorrectPasswordException;
import com.accolite.ecommercebackend.Exception.UserAlreadyExistException;
import com.accolite.ecommercebackend.Exception.UserNotFoundException;
import com.accolite.ecommercebackend.Repository.OrderRepository;
import com.accolite.ecommercebackend.Repository.PaymentRepository;
import com.accolite.ecommercebackend.Service.*;
import com.accolite.ecommercebackend.dto.Request.*;
import com.accolite.ecommercebackend.dto.Response.ChangePasswordResponse;
import com.accolite.ecommercebackend.dto.Response.OtpResponse;
import com.accolite.ecommercebackend.dto.Response.UserAuthResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthController {
    @Autowired
    private OtpService otpService;
    @Autowired
    private AuthService authService;

    @Autowired
    private ChangePasswordService changePasswordService;

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentRepository;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpUserRequest signUpUserRequest) {
        try {
            UserAuthResponse response = this.authService.createUser(signUpUserRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/sendotp")
    public ResponseEntity<OtpResponse> sendOtp(@Valid @RequestBody SendOtpRequest sendOtpRequest) {
        return otpService.sendOtp(sendOtpRequest.getEmail(),sendOtpRequest.isResend());
    }

    @PutMapping("/auth/reset/password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return changePasswordService.updatePassword(changePasswordRequest.getEmail(),changePasswordRequest.getPassword());
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        try {
            UserAuthResponse response = authService.login(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (IncorrectPasswordException e) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/auth/check/token/expiry")
    public ResponseEntity<String> extractToken(@RequestBody Authorization authorization) {

        Boolean isExpired = authService.checkTokenExpiry(authorization.getToken());

        if(isExpired){
            return new ResponseEntity<>("Token Expired",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Token not expired",HttpStatus.OK);
    }

    @PostMapping("/auth/razorpay-webhook")
    public ResponseEntity<String> handleRazorpayWebhook(@RequestBody String payload) {System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++entered");
        try {
            JSONObject event = new JSONObject(payload);
            String eventType = event.getString("event");

            System.out.println(eventType);

            if ("payment.failed".equals(eventType) || "payment_link.expired".equals(eventType)) {
                JSONObject payment = event.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
//                String paymentId = payment.getString("id");
                String orderIdStr = payment.getJSONObject("notes").getString("order_id");
                UUID orderId = UUID.fromString(orderIdStr);

                // Handle the payment failure or expiry

//                restoreProductQuantity

                paymentService.restoreProductQuantity(orderId);

                Orders order = orderService.findOrderbyId(orderId);
                order.setOrderStatus("Cancelled");
                Orders updatedOrder = orderRepository.save(order);
                System.out.println("cancelled order status: "+updatedOrder.getOrderStatus());

                System.out.println("Payment failed or expired for order: " + orderId);
            } else if ("payment.captured".equals(eventType)) {
                System.out.println("payment Captured");
                JSONObject payment = event.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
                String paymentId = payment.getString("id");
                String orderIdStr = payment.getJSONObject("notes").getString("order_id");
                UUID orderId = UUID.fromString(orderIdStr);

                System.out.println("orderId after payment successful: "+orderId);
                // Handle the successful payment
                Orders order = orderService.findOrderbyId(orderId);
                paymentService.addPayment(order, paymentId);
                order.setOrderStatus("Placed");
                Orders updatedOrder = orderRepository.save(order);
                System.out.println("order status: "+updatedOrder.getOrderStatus());
            }

            return ResponseEntity.ok("Webhook handled");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error handling webhook");
        }
    }

    @Scheduled(fixedRate = 3600000) // Run every hour
    public void checkPendingPayments() {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            // Fetch orders with pending payment status
            List<Orders> pendingOrders = orderService.getPendingOrders();
            for (Orders order : pendingOrders) {
                String paymentLinkId = order.getPaymentLinkId();
                if (paymentLinkId != null) {
                    PaymentLink paymentLink = razorpayClient.paymentLink.fetch(paymentLinkId);
                    String status = paymentLink.get("status");

                    if ("expired".equals(status)) {
                        paymentService.restoreProductQuantity(order.getOrderId());
                        order.setOrderStatus("Cancelled");
                        orderRepository.save((order));
                    } else if ("paid".equals(status)) {
                        order.setOrderStatus("Placed");
                        orderRepository.save((order));
                    }
                }
            }
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }


}

