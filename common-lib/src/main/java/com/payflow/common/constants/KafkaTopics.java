package com.payflow.common.constants;

public final class KafkaTopics {

    private KafkaTopics() {
    }

    public static final String TRANSACTION_CREATED =
            "transaction-created";

    public static final String TRANSACTION_FRAUD_RESULT =
            "transaction-fraud-result";

    public static final String DEBIT_REQUESTED =
            "debit-requested";

    public static final String DEBIT_COMPLETED =
            "debit-completed";

    public static final String CREDIT_REQUESTED =
            "credit-requested";

    public static final String CREDIT_COMPLETED =
            "credit-completed";

    public static final String REVERSAL_REQUESTED =
            "reversal-requested";

    public static final String REVERSAL_COMPLETED =
            "reversal-completed";

    public static final String DEBIT_REQUESTED_DLT =
            "debit-requested-dlt";

    public static final String CREDIT_REQUESTED_DLT =
            "credit-requested-dlt";

    public static final String REVERSAL_REQUESTED_DLT =
            "reversal-requested-dlt";
}