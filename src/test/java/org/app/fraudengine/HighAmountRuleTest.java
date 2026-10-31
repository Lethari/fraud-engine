package org.app.fraudengine;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.entity.TransactionType;
import org.app.fraudengine.rule.HighAmountRule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class HighAmountRuleTest {
    private HighAmountRule rule;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        rule = new HighAmountRule();
        transaction = new Transaction();
    }

    @ParameterizedTest
    @CsvSource({
            "25000,true",
            "30000,true",
            "15000, false",
            "20000, false"
    })
    void
   shouldEvaluateOnlinePurchaseAmounts(
           String amount, boolean expectedResult) {

        transaction.setTransactionType(TransactionType.ONLINE_PURCHASE);
        transaction.setAmount(new BigDecimal(amount));

        assertEquals(expectedResult, rule.evaluate(transaction));
    }

    @ParameterizedTest
    @CsvSource({
            "12000, true",
            "15000, true",
            "8000, false",
            "10000, false",
    })
    void
    shouldEvaluateCashWithdrawalAmounts(
            String amount, boolean expectedResult) {

        transaction.setTransactionType(TransactionType.CASH_WITHDRAWAL);
        transaction.setAmount(new BigDecimal(amount));

        assertEquals(expectedResult, rule.evaluate(transaction));
    }

    @ParameterizedTest
    @CsvSource({
            "35000, true",
            "40000, true",
            "20000, false",
            "30000, false",
    })
    void
    shouldEvaluateMoneyTransferAmounts(
            String amount, boolean expectedResult) {

        transaction.setTransactionType(TransactionType.MONEY_TRANSFER);
        transaction.setAmount(new BigDecimal(amount));


        assertEquals(expectedResult, rule.evaluate(transaction));
    }
}
