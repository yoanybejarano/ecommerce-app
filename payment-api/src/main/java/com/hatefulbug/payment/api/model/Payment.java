package com.hatefulbug.payment.api.model;

import com.hatefulbug.payment.api.enums.CurrencyType;
import com.hatefulbug.payment.api.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Currency", nullable = false, length = 10)
    private CurrencyType currency;

    //@ColumnDefault("'Pending'")
    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentStatus")
    private PaymentStatus paymentStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "PaymentDate")
    private Instant paymentDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PaymentMethodID", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "TransactionID", nullable = false, length = 100)
    private String transactionID;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private Instant updatedAt;

}