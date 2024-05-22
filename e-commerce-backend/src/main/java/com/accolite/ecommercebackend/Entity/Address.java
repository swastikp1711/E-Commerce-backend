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
public class Address {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="addressId", columnDefinition = "BINARY(16)")
    private UUID addressId;

    private String address;

    private String city;

    private String state;

    private String name;

    private String phoneNumber;

    private String postalCode;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Orders> orders;

    public Address(UUID addressId, String address, String city, String state, String name, String phoneNumber, String postalCode, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, User user) {
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.user = user;
    }
}
