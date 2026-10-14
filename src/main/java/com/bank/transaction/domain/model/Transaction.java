package com.bank.transaction.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
    @NotNull
    UUID id,

    @NotNull
    UUID accountId,

    @NotNull
    @Positive
    BigDecimal amount,

    @NotNull
    @Size(min = 3, max = 3)
    String currency,

    @NotNull
    TransactionType type,

    @NotNull
    LocalDateTime timestamp,

    @NotNull
    @Size(max = 255)
    String description,

    @NotNull
    TransactionStatus status
) {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT, REFUND
    }

    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

    public Transaction {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter code");
        }
    }

    public boolean isHighValue() {
        return amount.compareTo(new BigDecimal("10000.00")) > 0;
    }

    public boolean requiresAudit() {
        return isHighValue() || type == TransactionType.TRANSFER;
    }
}