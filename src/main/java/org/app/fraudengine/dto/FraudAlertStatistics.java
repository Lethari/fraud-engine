package org.app.fraudengine.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FraudAlertStatistics {
    private long totalAlerts;
    private long highSeverity;
    private long mediumSeverity;
    private long lowSeverity;

    private long openAlerts;
    private long investigatingAlerts;
    private long rejectedAlerts;
    private long closedAlerts;
}
