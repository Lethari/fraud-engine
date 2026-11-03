package org.app.fraudengine.repository;

import org.app.fraudengine.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudAlertRepository extends JpaRepository<FraudAlert, Long> {
    List<FraudAlert>findByTransactionCustomerId(String customerId);
}
