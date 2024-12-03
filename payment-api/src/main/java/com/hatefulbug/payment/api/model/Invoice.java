package com.hatefulbug.payment.api.model;

import com.hatefulbug.payment.api.enums.InvoiceStatus;
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
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "TotalAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "InvoiceDate")
    private Instant invoiceDate;

    @Column(name = "DueDate", nullable = false)
    private Instant dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "InvoiceStatus")
    private InvoiceStatus invoiceStatus;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private Instant updatedAt;

}