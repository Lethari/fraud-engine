package org.app.fraudengine.service;

import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.repository.TransactionRepository;
import org.app.fraudengine.rule.FraudRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionService {

    private final List<FraudRule> fraudRules;
    private final TransactionRepository transactionRepository;

    public FraudDetectionService(
            List<FraudRule> fraudRules,
            TransactionRepository transactionRepository) {

        this.fraudRules = fraudRules;
        this.transactionRepository = transactionRepository;
    }

    public List<String> evaluateTransaction(Transaction transaction) {

        List<String> triggeredRules = new ArrayList<>();

        for (FraudRule rule : fraudRules) {
            if (rule.evaluate(transaction)) {
                triggeredRules.add(rule.getRuleName());
            }
        }

        return triggeredRules;
    }

    public List<String> saveAndEvaluateTransaction(Transaction transaction) {
       //Save the transaction first
        Transaction savedTransaction = transactionRepository.save(transaction);

        //Evaluate saved transaction against all fraud rules
        return evaluateTransaction(savedTransaction);
    }
}
