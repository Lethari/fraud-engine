package org.app.fraudengine.rule;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.entity.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HighAmountRule implements FraudRule {

    private static final BigDecimal ONLINE_PURCHASE_LIMIT =
            new BigDecimal("20000");

    private static final BigDecimal CASH_WITHDRAWAL_LIMIT =
            new BigDecimal("10000");

    private static final BigDecimal MONEY_TRANSFER_LIMIT =
            new BigDecimal("30000");

    @Override
    public boolean evaluate(Transaction transaction) {

        BigDecimal limit;

        //Switch statement checks transaction type
        switch (transaction.getTransactionType()) {

            case ONLINE_PURCHASE:
                limit = ONLINE_PURCHASE_LIMIT;
                break;

            case CASH_WITHDRAWAL:
                limit = CASH_WITHDRAWAL_LIMIT;
                break;

            case MONEY_TRANSFER:
                limit = MONEY_TRANSFER_LIMIT;
                break;

            default:
                return false;
        }

        //User transaction amount/type gets compared and evaluates
        return transaction.getAmount().compareTo(limit) > 0;
    }

    @Override
    public String getRuleName() {
        return "HIGH_AMOUNT";
    }
}