package org.app.fraudengine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alerts")
@Getter
@Setter
@NoArgsConstructor
public class FraudAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(nullable = false)
    private String ruleName;

    @Column(nullable = false, length = 500)
    private String reason;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private LocalDateTime detectedAt;

    @Column(nullable = false)
    private String status;
}
