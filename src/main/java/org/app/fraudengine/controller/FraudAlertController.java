package org.app.fraudengine.controller;

import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.service.FraudAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud_alerts")
public class FraudAlertController {

    private final FraudAlertService fraudAlertService;

    public FraudAlertController(FraudAlertService fraudAlertService) {
        this.fraudAlertService = fraudAlertService;
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
}
