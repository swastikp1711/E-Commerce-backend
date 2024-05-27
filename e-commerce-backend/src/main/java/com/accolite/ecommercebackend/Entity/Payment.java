package com.accolite.ecommercebackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name="paymentId", columnDefinition = "BINARY(16)")
    private String paymentId;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    private LocalDateTime paymentDate;

    private String paymentStatus;

    private Double amount;

}
