package org.app.fraudengine.service;

import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.entity.RiskLevel;
import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.repository.TransactionRepository;
import org.app.fraudengine.repository.FraudAlertRepository;
import org.app.fraudengine.rule.FraudRule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
public class FraudDetectionService {

    private final List<FraudRule> fraudRules;
    private final TransactionRepository transactionRepository;
    private final FraudAlertRepository fraudAlertRepository;
    private final RiskScoringService riskScoringService;

    // Constructor
    public FraudDetectionService(
            List<FraudRule> fraudRules,
            TransactionRepository transactionRepository,
            FraudAlertRepository fraudAlertRepository,
            RiskScoringService riskScoringService) {

        this.fraudRules = fraudRules;
        this.transactionRepository = transactionRepository;
        this.fraudAlertRepository = fraudAlertRepository;
        this.riskScoringService = riskScoringService;
    }

    public List<String> evaluateTransaction(Transaction transaction) {

        List<String> triggeredRules = new ArrayList<>();

        // Evaluate all fraud rules first
        for (FraudRule rule : fraudRules) {
            if (rule.evaluate(transaction)) {
                triggeredRules.add(rule.getRuleName());
            }
        }

        // Calculate the overall transaction risk score
        int score = riskScoringService.calculateScore(triggeredRules);

        // Determine overall risk level
        RiskLevel riskLevel =
                riskScoringService.determineRiskLevel(score);

        // Create an alert for each triggered rule
        for (FraudRule rule : fraudRules) {
            if (triggeredRules.contains(rule.getRuleName())) {
                createFraudAlert(transaction, rule, score, riskLevel);
            }
        }

        return triggeredRules;
    }

    // Create and save a fraud alert
    private void createFraudAlert(Transaction transaction, FraudRule rule,
                                  int score, RiskLevel riskLevel) {
            FraudAlert fraudAlert = new FraudAlert();

            fraudAlert.setTransaction(transaction);
            fraudAlert.setRuleName(rule.getRuleName());
            fraudAlert.setReason("Fraud rule triggered: " + rule.getRuleName());
            fraudAlert.setRiskScore(score);
            fraudAlert.setSeverity(riskLevel.name());
            fraudAlert.setDetectedAt(LocalDateTime.now());
            fraudAlert.setStatus("OPEN");

            fraudAlertRepository.save(fraudAlert);
    }

    // Save transaction first, then evaluate it
    public List<String> saveAndEvaluateTransaction(Transaction transaction) {

        Transaction savedTransaction = transactionRepository.save(transaction);

        return evaluateTransaction(savedTransaction);
    }
}
