package org.app.fraudengine.service;

import org.app.fraudengine.entity.FraudAlert;
import org.app.fraudengine.repository.FraudAlertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.app.fraudengine.dto.FraudAlertStatistics;

import java.time.LocalDateTime;
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

    //Retrieve fraud alerts for a customer
    public List<FraudAlert>getFraudAlertByCustomerId(String customerId) {
        return fraudAlertRepository.findByTransactionCustomerId(customerId);
    }

    //Retrieve/Filter fraud alert by status
    public List<FraudAlert>getFraudAlertByStatus(String status) {
        return fraudAlertRepository.findByStatus(status);
    }

    //Allow to handle large dataset by requesting page and no. of records
    public Page<FraudAlert>getFraudAlerts(Pageable pageable) {
        return fraudAlertRepository.findAll(pageable);
    }

    //Allows filtering by severity: HIGH, MEDIUM, LOW
    public List<FraudAlert>getFraudAlertsBySeverity(String severity) {
        return fraudAlertRepository.findBySeverity(severity);
    }

    //Allows filtering by passing a specific date frame
    public List<FraudAlert>getFraudAlertsByDateRange(LocalDateTime start, LocalDateTime end) {
        return fraudAlertRepository.findByDetectedAtBetween(start, end);
    }

    //Assists with statistic dashboard reporting
    public long getTotalFraudAlertsBySeverity(String severity) {
        return fraudAlertRepository.countBySeverity(severity);
    }
    public long getTotalFraudAlertsByStatus(String status) {
        return fraudAlertRepository.countByStatus(status);
    }

    public FraudAlertStatistics getFraudAlertStatistics() {

        FraudAlertStatistics statistics = new FraudAlertStatistics();
        statistics.setTotalAlerts(fraudAlertRepository.count());

        statistics.setHighSeverity(getTotalFraudAlertsBySeverity("HIGH"));

        statistics.setMediumSeverity(getTotalFraudAlertsBySeverity("MEDIUM"));

        statistics.setLowSeverity(getTotalFraudAlertsBySeverity("LOW"));

        statistics.setOpenAlerts(getTotalFraudAlertsByStatus("OPEN"));

        statistics.setInvestigatingAlerts(getTotalFraudAlertsByStatus("INVESTIGATING"));

        statistics.setClosedAlerts(getTotalFraudAlertsByStatus("CLOSED"));

        statistics.setRejectedAlerts(getTotalFraudAlertsByStatus("REJECTED"));

        return statistics;
    }
}
