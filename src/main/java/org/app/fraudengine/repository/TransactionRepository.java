package org.app.fraudengine.repository;

import org.app.fraudengine.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerIdAndTransactionDateTimeBetween(
            String customerID,
            LocalDateTime start,
            LocalDateTime end
    );
}
