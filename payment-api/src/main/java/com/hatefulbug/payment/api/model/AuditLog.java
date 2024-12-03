package com.hatefulbug.payment.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID", nullable = false)
    private Integer id;

    @Column(name = "Action", nullable = false, length = 100)
    private String action;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Lob
    @Column(name = "Details")
    private String details;

    @CreationTimestamp
    @Column(name = "Timestamp")
    private Instant timestamp;

}