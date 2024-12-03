package com.hatefulbug.payment.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "security")
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SecurityID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PaymentID", nullable = false)
    private Payment payment;

    @Lob
    @Column(name = "EncryptedCardDetails")
    private String encryptedCardDetails;

    @Column(name = "Token")
    private String token;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Instant createdAt;

}