package com.payflow.switchservice.entity;

import com.payflow.common.enums.BankType;
import com.payflow.common.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 50
    )
    private String transactionReference;

    @Column(nullable = false)
    private String senderAccount;

    @Column(nullable = false)
    private String receiverAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankType senderBank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankType receiverBank;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;




}