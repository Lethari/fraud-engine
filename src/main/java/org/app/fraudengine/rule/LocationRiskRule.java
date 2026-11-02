package org.app.fraudengine.rule;

import org.app.fraudengine.entity.RiskLevel;
import org.app.fraudengine.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LocationRiskRule implements FraudRule {

    // Define risk levels for different countries
    private final Map<String, RiskLevel> countryRiskLevels = Map.of(
            "COUNTRY_A", RiskLevel.HIGH,
            "COUNTRY_B", RiskLevel.MEDIUM,
            "COUNTRY_C", RiskLevel.LOW
    );

    @Override
    public boolean evaluate(Transaction transaction) {

        String country = transaction.getCountry()
                .trim()
                .toUpperCase();

        RiskLevel riskLevel = countryRiskLevels.getOrDefault(
                country, RiskLevel.LOW
        );

        return riskLevel == RiskLevel.HIGH;
    }

    // Enum to represent different risk levels
    @Override
    public String getRuleName() {
        return "LOCATION_RISK";
    }
}
