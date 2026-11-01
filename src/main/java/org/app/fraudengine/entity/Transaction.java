package org.app.fraudengine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor

public class Transaction {
    // Primary key for the Transaction entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionReference;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false,
    precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false)
    private LocalDateTime
    transactionDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType
    transactionType;
}
