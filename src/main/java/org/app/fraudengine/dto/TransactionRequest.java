package org.app.fraudengine.dto;

import org.app.fraudengine.entity.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest {
    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Transaction amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Transaction reference is required")
    private String transactionReference;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message ="Country is required")
    private String country;

    @NotNull(message ="Transaction date and time are required")
    private LocalDateTime transactionDate;

    @NotNull(message ="Transaction type is required")
    private TransactionType transactionType;

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionReference() {
        return transactionReference;
    }
    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDate;
    }
    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDate = transactionDateTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
