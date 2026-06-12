package com.payflow.auditservice.entity;

import com.payflow.common.enums.BankType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_audits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionReference;

    private String senderAccount;

    private String receiverAccount;

    @Enumerated(EnumType.STRING)
    private BankType senderBank;

    @Enumerated(EnumType.STRING)
    private BankType receiverBank;

    private BigDecimal amount;

    private LocalDateTime receivedAt;
}