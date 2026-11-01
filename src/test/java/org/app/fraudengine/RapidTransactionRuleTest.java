package org.app.fraudengine;

import org.app.fraudengine.rule.RapidTransactionRule;
import org.app.fraudengine.repository.TransactionRepository;
import org.app.fraudengine.entity.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RapidTransactionRuleTest {
    private RapidTransactionRule rule;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        rule = new RapidTransactionRule(transactionRepository);
    }

    @Test
    void shouldNotFlagWhenFewerThanThreeTransactions() {

        Transaction transaction = new Transaction();

        transaction.setCustomerId("CUST001");
        LocalDateTime transactionTime = LocalDateTime.of(2026,11,1,14,0);

        transaction.setTransactionDateTime(transactionTime);

        when(transactionRepository.findByCustomerIdAndTransactionDateTimeBetween(
                "CUST001",

                transactionTime.minusMinutes(2), transactionTime
        ))
                .thenReturn(List.of(transaction, new Transaction()));

        boolean result = rule.evaluate(transaction);
        assertFalse(result);

    }
    @Test
    void shouldFlagWhenThreeOrMoreTransactionsOccur() {

        Transaction transaction = new Transaction();

        transaction.setCustomerId("CUST001");

        LocalDateTime transactionTime =
                LocalDateTime.of(2026, 11, 1, 14, 0);

        transaction.setTransactionDateTime(transactionTime);

        when(transactionRepository
                .findByCustomerIdAndTransactionDateTimeBetween(
                        "CUST001",
                        transactionTime.minusMinutes(2),
                        transactionTime
                ))
                .thenReturn(List.of(
                        transaction,
                        new Transaction(),
                        new Transaction()
                ));

        boolean result = rule.evaluate(transaction);

        assertTrue(result);
    }

    @Test
    void shouldNotFlagTransactionsFromDifferentCustomers() {

        Transaction transaction = new Transaction();

        transaction.setCustomerId("CUST001");

        LocalDateTime transactionTime =
                LocalDateTime.of(2026, 11, 1, 14, 0);

        transaction.setTransactionDateTime(transactionTime);

        when(transactionRepository
                .findByCustomerIdAndTransactionDateTimeBetween(
                        "CUST001",
                        transactionTime.minusMinutes(2),
                        transactionTime
                ))
                .thenReturn(List.of(
                        new Transaction(),
                        new Transaction()
                ));

        boolean result = rule.evaluate(transaction);

        assertFalse(result);
    }

}
