package org.app.fraudengine.dto;

import lombok.Getter;
import lombok.Setter;
import org.app.fraudengine.entity.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest {
    @Setter
    @Getter
    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @Getter
    @Setter
    @NotNull(message = "Transaction amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Setter
    @Getter
    @NotBlank(message = "Transaction reference is required")
    private String transactionReference;

    @Setter
    @Getter
    @NotBlank(message = "Currency is required")
    private String currency;

    @Setter
    @Getter
    @NotBlank(message = "Category is required")
    private String category;

    @Setter
    @Getter
    @NotBlank(message = "Location is required")
    private String location;

    @Setter
    @Getter
    @NotBlank(message ="Country is required")
    private String country;

    @Setter
    @Getter
    @NotNull(message ="Transaction type is required")
    private TransactionType transactionType;

    @Setter
    @Getter
    @NotBlank(message = "Transaction date and Time are required")
    private LocalDateTime transactionDateTime;
}
