package com.hatefulbug.payment.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", nullable = false)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private Instant updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<AuditLog> auditLogs;

}