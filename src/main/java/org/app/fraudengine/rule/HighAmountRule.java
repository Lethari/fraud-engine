package org.app.fraudengine.rule;

import org.app.fraudengine.entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HighAmountRule implements FraudRule {

    private static final BigDecimal HIGH_AMOUNT_LIMIT = new BigDecimal("20000");

    @Override
    public boolean evaluate(Transaction transaction) {
        return transaction.getAmount().compareTo(HIGH_AMOUNT_LIMIT) > 0;
    }

    @Override
    public String getRuleName() {
        return "HIGH_AMOUNT";
    }
}