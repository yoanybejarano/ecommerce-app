package com.hatefulbug.payment.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentMethodID", nullable = false)
    private Integer id;

    @Column(name = "MethodName", nullable = false, length = 50)
    private String methodName;

    @Column(name = "Description")
    private String description;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private Instant updatedAt;

}