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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="paymentId", columnDefinition = "BINARY(16)")
    private UUID paymentId;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    private LocalDateTime paymentDate;

    private String paymentStatus;

    private Double amount;

}
