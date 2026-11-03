package org.app.fraudengine.service;

import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.repository.FraudAlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FraudAlertService {

    private final FraudAlertRepository fraudAlertRepository;

    public FraudAlertService(FraudAlertRepository fraudAlertRepository) {
        this.fraudAlertRepository = fraudAlertRepository;
    }

    //Retrieve all fraud alerts
    public List<FraudAlert>getAllFraudAlerts() {
        return fraudAlertRepository.findAll();
    }

    //Retrieve a fraud alert by ID
    public Optional<FraudAlert>getFraudAlertById(Long id) {
        return fraudAlertRepository.findById(id);
    }
}
