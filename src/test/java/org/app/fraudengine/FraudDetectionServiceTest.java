package org.app.fraudengine;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.rule.FraudRule;
import org.app.fraudengine.service.FraudDetectionService;
import org.junit.jupiter.api.Test;

import org.app.fraudengine.repository.TransactionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FraudDetectionServiceTest {

    @Test
    void shouldReturnTriggeredRules() {

        FraudRule testRule = new FraudRule() {

            @Override
            public boolean evaluate(Transaction transaction) {
                return true;
            }

            @Override
            public String getRuleName() {
                return "TEST_RULE";
            }
        };

        TransactionRepository repository = mock(TransactionRepository.class);

        FraudDetectionService service = new FraudDetectionService(List.of(testRule), repository);

        Transaction transaction = new Transaction();

        List<String> result = service.evaluateTransaction(transaction);

        assertEquals(List.of("TEST_RULE"), result);
    }
}