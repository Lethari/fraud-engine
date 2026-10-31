package org.app.fraudengine.rule;

import org.app.fraudengine.entity.Transaction;

public interface FraudRule {
    // Method to evaluate a transaction against the fraud rule
    boolean evaluate(Transaction transaction);

    String getRuleName();
}
