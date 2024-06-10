package com.accolite.ecommercebackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="orderId", columnDefinition = "BINARY(16)")
    private UUID orderId;

    private String orderStatus;

    private Double deliveryCharges;

    private Double totalAmount;

    private String PaymentLinkId;


    private LocalDateTime orderDate;

    private LocalDateTime shipped;

    private LocalDateTime delivered;

    private LocalDateTime cancelled;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Payment payment;


    public Orders(UUID orderId, String orderStatus, Double deliveryCharges, Double totalAmount, LocalDateTime orderDate, LocalDateTime shipped, LocalDateTime delivered, LocalDateTime cancelled, User user, Address address) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.deliveryCharges = deliveryCharges;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.shipped = shipped;
        this.delivered = delivered;
        this.cancelled = cancelled;
        this.user = user;
        this.address = address;
    }
}

