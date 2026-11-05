package org.app.fraudengine.controller;

import jakarta.validation.Valid;
import org.app.fraudengine.dto.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.app.fraudengine.entity.Transaction;
import org.app.fraudengine.service.FraudDetectionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final FraudDetectionService fraudDetectionService;

    // Constructor injection
    public TransactionController(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    // Handle incoming POST requests
    @PostMapping
    public ResponseEntity<List<String>> createTransaction(
            @Valid @RequestBody TransactionRequest request) {

        Transaction transaction = new Transaction();

        transaction.setTransactionReference(request.getTransactionReference());
        transaction.setCustomerId(request.getCustomerId());
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setCategory(request.getCategory());
        transaction.setLocation(request.getLocation());
        transaction.setCountry(request.getCountry());
        transaction.setTransactionDateTime(request.getTransactionDateTime());
        transaction.setTransactionType(request.getTransactionType());

        List<String> triggeredRules =
                fraudDetectionService.saveAndEvaluateTransaction(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(triggeredRules);
    }

    //Retrieves all transactions belonging to a specific customer
    @GetMapping("/customer/{customerId}/history")
    public ResponseEntity<List<Transaction>> getCustomerTransactionHistory(@PathVariable String customerId) {
        List<Transaction> transactions =fraudDetectionService.getCustomerTransactionHistory(customerId);
        return ResponseEntity.ok(transactions);
    }
}

