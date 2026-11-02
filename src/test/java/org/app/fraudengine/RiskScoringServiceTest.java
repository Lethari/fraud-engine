package org.app.fraudengine;

import org.app.fraudengine.entity.RiskLevel;
import org.app.fraudengine.service.RiskScoringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskScoringServiceTest {

    private RiskScoringService riskScoringService;

    @BeforeEach
    void setUp() {
        riskScoringService = new RiskScoringService();
    }

    @Test
    void shouldCalculateScoreFromTriggeredRules() {
        List<String> triggeredRules = List.of("HIGH_AMOUNT", "RAPID_TRANSACTIONS");

        int score = riskScoringService.calculateScore(triggeredRules);

        assertEquals(70, score);
    }

    @Test
    void shouldDetermineHighRiskLevel() {
        assertEquals(RiskLevel.HIGH, riskScoringService.determineRiskLevel(70)
        );
    }

    @Test
    void shouldDetermineMediumRiskLevel() {
        assertEquals(RiskLevel.MEDIUM, riskScoringService.determineRiskLevel(40)
        );
    }

    @Test
    void shouldDetermineLowRiskLevel() {
        assertEquals(RiskLevel.LOW, riskScoringService.determineRiskLevel(10)
        );
    }

    @Test
    void shouldDetermineLowRiskAtUpperBoundary() {
        assertEquals(RiskLevel.LOW, riskScoringService.determineRiskLevel(29));
    }

    @Test
    void shouldDetermineMediumRiskAtLowerBoundary() {
        assertEquals(RiskLevel.MEDIUM, riskScoringService.determineRiskLevel(30));
    }

    @Test
    void shouldDetermineMediumRiskAtUpperBoundary() {
        assertEquals(RiskLevel.MEDIUM, riskScoringService.determineRiskLevel(59));
    }

    @Test
    void shouldDetermineHighRiskAtLowerBoundary() {
        assertEquals(RiskLevel.HIGH, riskScoringService.determineRiskLevel(60));
    }
}
