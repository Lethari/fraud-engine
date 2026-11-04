package org.app.fraudengine.controller;

import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.service.FraudAlertService;
import org.app.fraudengine.service.FraudDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/api/fraud_alerts")
public class FraudAlertController {

    /**Retrieves all fraud alerts.
     *Exposure: GET/api/fraud_alerts
     * Return all fraud alerts stored in the database
     */
    private final FraudAlertService fraudAlertService;
    private final FraudDetectionService fraudDetectionService;

    public FraudAlertController(FraudAlertService fraudAlertService, FraudDetectionService fraudDetectionService) {
        this.fraudAlertService = fraudAlertService;
        this.fraudDetectionService = fraudDetectionService;
    }

    @GetMapping
    public List<FraudAlert>getAllFraudAlerts() {
        return fraudAlertService.getAllFraudAlerts();
    }

    //Retrieves fraud alert by its ID
    @GetMapping("/{id}")
    public ResponseEntity<FraudAlert>getFraudAlertById(@PathVariable Long id) {
        return fraudAlertService.getFraudAlertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Allows filtering fraud alert by customer ID
    @GetMapping("/customer/{customerId}")
    public  List<FraudAlert>getFraudAlertsByCustomerId(@PathVariable String customerId) {
        return fraudAlertService.getFraudAlertByCustomerId(customerId);
    }

    //Updates status of an existing fraud alert
    @PutMapping("/{alertId}/status")
    public ResponseEntity<FraudAlert>updateFraudAlertStatus(@PathVariable Long alertId, @RequestParam String status) {

        FraudAlert updatedAlert = fraudDetectionService.updateFraudAlertStatus(alertId, status);
        return ResponseEntity.ok(updatedAlert);
    }

    //Exposure: Filtering by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FraudAlert>>getFraudAlertsByStatus(@PathVariable String status) {
        List<FraudAlert> alerts = fraudAlertService.getFraudAlertByStatus(status);
        return ResponseEntity.ok(alerts);
    }

    //Returns a page of fraud alerts sorted by a specified field
    @GetMapping("/page")
    public ResponseEntity<Page<FraudAlert>> getFraudAlertsPaginated(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<FraudAlert> alerts = fraudAlertService.getFraudAlerts(pageable);
        return ResponseEntity.ok(alerts);
    }

    //Returns alerts matching HIGH, MEDIUM, LOW
    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<FraudAlert>> getFraudAlertsBySeverity(@PathVariable String severity) {
        List<FraudAlert> alerts = fraudAlertService.getFraudAlertsBySeverity(severity);
        return ResponseEntity.ok(alerts);
    }
}
