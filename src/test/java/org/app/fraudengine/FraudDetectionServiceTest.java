package org.app.fraudengine;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.entity.RiskLevel;
import org.app.fraudengine.rule.FraudRule;
import org.app.fraudengine.service.FraudDetectionService;
import org.app.fraudengine.repository.FraudAlertRepository;
import org.app.fraudengine.service.RiskScoringService;
import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        TransactionRepository repository =
                mock(TransactionRepository.class);

        FraudAlertRepository fraudAlertRepository =
                mock(FraudAlertRepository.class);

        RiskScoringService riskScoringService =
                mock(RiskScoringService.class);

        // Tell Mockito what risk level to return
        when(riskScoringService.determineRiskLevel(anyInt()))
                .thenReturn(RiskLevel.HIGH);

        FraudDetectionService service =
                new FraudDetectionService(
                        List.of(testRule),
                        repository,
                        fraudAlertRepository,
                        riskScoringService
                );

        Transaction transaction = new Transaction();

        List<String> result =
                service.evaluateTransaction(transaction);

        assertEquals(List.of("TEST_RULE"), result);
    }

    @Test
    void shouldSaveFraudAlertWithCombineRiskScore() {

        //Arrange: Create two fraud rules that always trigger
        FraudRule highAmountRule = new FraudRule() {
            @Override
            public boolean evaluate(Transaction transaction) {
                return true;
            }

            @Override
            public String getRuleName() {
                return "HIGH_AMOUNT";
            }
        };

        FraudRule rapidTransactionRule = new FraudRule() {
            @Override
            public boolean evaluate(Transaction transaction) {
                return true;
            }

            @Override
            public String getRuleName() {
                return "RAPID_TRANSACTIONS";
            }
        };

        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        FraudAlertRepository fraudAlertRepository = mock(FraudAlertRepository.class);
        RiskScoringService riskScoringService = new RiskScoringService();

        FraudDetectionService service = new FraudDetectionService(
                List.of(highAmountRule, rapidTransactionRule),
                transactionRepository, fraudAlertRepository, riskScoringService
        );

        Transaction transaction = new Transaction();

        //Act: Evaluate the transaction
        service.evaluateTransaction(transaction);

        //Assert: Capture the saved fraud alerts
        ArgumentCaptor<FraudAlert> alertCaptor = ArgumentCaptor.forClass(FraudAlert.class);

        verify(fraudAlertRepository, times(2))
                .save(alertCaptor.capture());

        List<FraudAlert> savedAlerts = alertCaptor.getAllValues();

        //Both alerts should have the combined score
        assertEquals(70, savedAlerts.get(0).getRiskScore());
        assertEquals(70, savedAlerts.get(1).getRiskScore());

        //Both alerts should have HIGH severity
        assertEquals("HIGH", savedAlerts.get(1).getSeverity());
    }
}
