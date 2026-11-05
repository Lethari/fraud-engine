package org.app.fraudengine.repository;

import org.app.fraudengine.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FraudAlertRepository extends JpaRepository<FraudAlert, Long> {
    List<FraudAlert>findByTransactionCustomerId(String customerId);
    List<FraudAlert>findByStatus(String status);
    List<FraudAlert>findBySeverity(String severity);
    List<FraudAlert>findByDetectedAtBetween(LocalDateTime start, LocalDateTime end);
    long countBySeverity(String severity);
    long countByStatus(String status);
}
