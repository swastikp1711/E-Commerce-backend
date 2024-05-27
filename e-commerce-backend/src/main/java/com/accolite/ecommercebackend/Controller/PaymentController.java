package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.Repository.OrderRepository;
import com.accolite.ecommercebackend.Repository.ProductRepository;
import com.accolite.ecommercebackend.Service.CartService;
import com.accolite.ecommercebackend.Service.OrderService;
import com.accolite.ecommercebackend.Service.PaymentService;
import com.accolite.ecommercebackend.dto.Response.PaymentLinkResponse;
import com.accolite.ecommercebackend.dto.Response.PaymentResponse;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@CacheConfig(cacheNames = "payment")
public class PaymentController {
	@Value("${razorpay.api.key}")
	String apiKey;

	@Value("${razorpay.api.secret}")
	String apiSecret;

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private OrderService orderService;



	@PostMapping("/payments/{orderId}")
	public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable UUID orderId) throws RazorpayException {
		Orders order=orderService.findOrderbyId(orderId);
		System.out.println(apiKey);
		System.out.println(apiSecret);

		try{
			RazorpayClient razorpayClient=new RazorpayClient(apiKey,apiSecret);
			JSONObject paymentLinkRequest=new JSONObject();
			paymentLinkRequest.put("amount",Math.ceil(order.getTotalAmount()*100));
			paymentLinkRequest.put("currency","INR");

			JSONObject customer=new JSONObject();
			customer.put("name",order.getUser().getFirstName());
			customer.put("email",order.getUser().getEmail());
			paymentLinkRequest.put("customer",customer);

			JSONObject notify=new JSONObject();
			notify.put("sms",true);
			notify.put("email",true);
			paymentLinkRequest.put("notify",notify);

			paymentLinkRequest.put("callback_url","http://localhost:5173/paymentsuccessfull/"+orderId.toString());
			paymentLinkRequest.put("callback_method","get");
			PaymentLink payment=razorpayClient.paymentLink.create(paymentLinkRequest);

			String paymentLinkId=payment.get("id");

			String paymentLinkUrl=payment.get("short_url");
			System.out.println("Payment Link ID: "+paymentLinkId);
			System.out.println("Payment Link URL: "+paymentLinkUrl);

			PaymentLinkResponse res = new PaymentLinkResponse();
			res.setPayment_link_id(paymentLinkId);
			res.setPayment_link_url(paymentLinkUrl);

			return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.CREATED);
		}catch(Exception e){
			throw new RazorpayException(e.getMessage());

		}
	}

	@PostMapping("/payments")
	public ResponseEntity<PaymentResponse> redirect(@RequestParam(name="payment_id") String paymentId, @RequestParam(name="order_id") UUID orderId) throws RazorpayException {
		Orders orders=orderService.findOrderbyId(orderId);
		RazorpayClient razorpayClient=new RazorpayClient(apiKey,apiSecret);
		try{
			Payment payment=razorpayClient.payments.fetch(paymentId);
			if(payment.get("captured")){
				paymentService.addPayment(orders, paymentId);
				paymentService.setStatus(orders, paymentId);
				paymentService.reduceProductQuantity(orderId);
			}
			PaymentResponse res=new PaymentResponse();
			res.setMessage("Order Placed");
			res.setStatus(true);
			return new ResponseEntity<PaymentResponse>(res,HttpStatus.ACCEPTED);

		}catch (Exception e){
			throw new RazorpayException(e.getMessage());
		}
	}
}
