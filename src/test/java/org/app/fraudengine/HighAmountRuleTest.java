package org.app.fraudengine;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.entity.TransactionType;
import org.app.fraudengine.rule.HighAmountRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HighAmountRuleTest {
    private HighAmountRule rule;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        rule = new HighAmountRule();
        transaction = new Transaction();
    }

    //boundary testing at or around the threshold limit
    @Test
    void
    shouldFlagOnlinePurchaseAboveThresholdId() {
        transaction.setTransactionType(TransactionType.ONLINE_PURCHASE);
        transaction.setAmount(new BigDecimal("25000"));

        boolean result = rule.evaluate(transaction);
               assertTrue(result);
    }
    @Test
    void
    shouldNotFlagOnlinePurchaseBelowThresholdId() {
        transaction.setTransactionType(TransactionType.ONLINE_PURCHASE);
        transaction.setAmount(new BigDecimal("13000"));

        assertFalse(rule.evaluate(transaction));
    }
    @Test
    void
    shouldFlagCashWithdrawalAboveThresholdId() {
        transaction.setTransactionType(TransactionType.CASH_WITHDRAWAL);
        transaction.setAmount(new BigDecimal("12000"));

        assertTrue(rule.evaluate(transaction));
    }
    @Test
    void
    shouldNotFlagCashWithdrawalBelowThresholdId() {
        transaction.setTransactionType(TransactionType.CASH_WITHDRAWAL);
        transaction.setAmount(new BigDecimal("10000"));

        assertFalse(rule.evaluate(transaction));
    }
    @Test
    void
    shouldFlagMoneyTransferAboveThresholdId() {
        transaction.setTransactionType(TransactionType.MONEY_TRANSFER);
        transaction.setAmount(new BigDecimal("35500"));

        assertTrue(rule.evaluate(transaction));
    }
    @Test
    void
    shouldNotFlagMoneyTransferBelowThresholdId() {
        transaction.setTransactionType(TransactionType.MONEY_TRANSFER);
        transaction.setAmount(new BigDecimal("20000"));

        assertFalse(rule.evaluate(transaction));
    }
}
