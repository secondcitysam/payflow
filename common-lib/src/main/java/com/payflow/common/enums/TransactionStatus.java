package com.payflow.common.enums;

public enum TransactionStatus {

    CREATED,
    VALIDATING,
    FRAUD_CHECK,
    APPROVED,
    MANUAL_REVIEW,
    DEBITED,
    PROCESSING,
    CREDITED,
    SUCCESS,
    FAILED,
    REVERSAL_INITIATED,
    REVERSED
}