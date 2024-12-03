package com.hatefulbug.payment.api.model;

import com.hatefulbug.payment.api.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "refunds")
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RefundID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PaymentID", nullable = false)
    private Payment payment;

    @Column(name = "RefundAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal refundAmount;

    @Lob
    @Column(name = "RefundReason")
    private String refundReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "RefundStatus")
    private RefundStatus refundStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "RefundDate")
    private Instant refundDate;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private Instant updatedAt;

}