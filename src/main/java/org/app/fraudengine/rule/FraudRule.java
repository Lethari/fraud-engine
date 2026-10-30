package org.app.fraudengine.rule;

import org.app.fraudengine.entity.Transaction;

public interface FraudRule {
    boolean evaluate(Transaction transaction);

    String getRuleName();
}
