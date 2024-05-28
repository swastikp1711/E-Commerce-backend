package com.accolite.ecommercebackend.Service;
import com.accolite.ecommercebackend.dto.Request.UpdateOrderStatusRequest;
import com.accolite.ecommercebackend.dto.Response.AdminOrderDetailsResponse;
import java.util.List;
import java.util.UUID;
public interface AdminOrderDetailsService {
    List<AdminOrderDetailsResponse> getAllOrders();
    void updateOrderStatus(UUID orderId,UpdateOrderStatusRequest request);
}
