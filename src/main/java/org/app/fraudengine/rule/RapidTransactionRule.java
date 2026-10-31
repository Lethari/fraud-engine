package org.app.fraudengine.rule;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RapidTransactionRule implements FraudRule {

    private static final int TRANSACTION_LIMIT = 3;

    private static final int TIME_WINDOW_MINUTES = 2;

    private final TransactionRepository transactionRepository;

    public RapidTransactionRule(
            TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean evaluate(Transaction transaction) {

        LocalDateTime endTime = transaction.getTransactionDateTime();

        LocalDateTime startTime = endTime.minusMinutes(
                TIME_WINDOW_MINUTES
        );

        // Count the number of transactions for the same client within the time window
        long transactionCount = transactionRepository
                .findByCustomerIdAndTransactionDateTimeBetween(
                        transaction.getCustomerId(),
                        startTime,
                        endTime
                )
                .size();

        return transactionCount >= TRANSACTION_LIMIT;
    }

    @Override
    public String getRuleName() {
        return "RAPID_TRANSACTIONS";
    }
}

