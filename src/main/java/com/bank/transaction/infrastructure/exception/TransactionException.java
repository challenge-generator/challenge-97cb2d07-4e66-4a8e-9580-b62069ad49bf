package com.bank.transaction.infrastructure.exception;

public class TransactionException extends RuntimeException {
    private final String errorCode;
    private final String details;

    public TransactionException(String message) {
        super(message);
        this.errorCode = "TXN_ERROR";
        this.details = null;
    }

    public TransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "TXN_ERROR";
        this.details = null;
    }

    public TransactionException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = null;
    }

    public TransactionException(String message, String errorCode, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    public TransactionException(String message, String errorCode, String details, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDetails() {
        return details;
    }
}