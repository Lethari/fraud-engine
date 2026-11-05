package org.app.fraudengine.service;

import org.app.fraudengine.entity.RiskLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskScoringService {

    public RiskLevel determineRiskLevel(int score) {

        if (score >= 60) {
            return RiskLevel.HIGH;
        }else if (score >= 30) {
            return RiskLevel.MEDIUM;
        }else {
            return RiskLevel.LOW;
        }
    }

    public int calculateScore(List<String> triggeredRules) {

        int score = 0;
        for (String ruleName : triggeredRules) {

            switch (ruleName) {
                case "HIGH_AMOUNT":
                    score += 40;
                    break;

                case "RAPID_TRANSACTIONS":
                    score += 30;
                    break;

                case "LOCATION_RISK": //separated from merging(HIGH_AMOUNT) for future updates
                    score += 40;
                    break;

                default:
                    break;
            }
        }
        return score;
    }
}
