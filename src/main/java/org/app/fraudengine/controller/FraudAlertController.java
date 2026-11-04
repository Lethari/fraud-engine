package org.app.fraudengine.controller;

import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.service.FraudAlertService;
import org.app.fraudengine.service.FraudDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/fraud_alerts")
public class FraudAlertController {

    private final FraudAlertService fraudAlertService;
    private final FraudDetectionService fraudDetectionService;

    public FraudAlertController(FraudAlertService fraudAlertService, FraudDetectionService fraudDetectionService) {
        this.fraudAlertService = fraudAlertService;
        this.fraudDetectionService = fraudDetectionService;
    }

    //Retrieve all fraud alerts
    @GetMapping
    public List<FraudAlert>getAllFraudAlerts() {
        return fraudAlertService.getAllFraudAlerts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudAlert>getFraudAlertById(@PathVariable Long id) {
        return fraudAlertService.getFraudAlertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public  List<FraudAlert>getFraudAlertsByCustomerId(@PathVariable String customerId) {
        return fraudAlertService.getFraudAlertByCustomerId(customerId);
    }

    @PutMapping("/{alertId}/status")
    public ResponseEntity<FraudAlert>updateFraudAlertStatus(@PathVariable Long alertId, @RequestParam String status) {

        FraudAlert updatedAlert = fraudDetectionService.updateFraudAlertStatus(alertId, status);
        return ResponseEntity.ok(updatedAlert);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FraudAlert>>getFraudAlertsByStatus(@PathVariable String status) {
        List<FraudAlert> alerts = fraudAlertService.getFraudAlertByStatus(status);
        return ResponseEntity.ok(alerts);
    }
}
