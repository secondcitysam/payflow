package com.payflow.bankservice.entity;

import com.payflow.common.enums.BankType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String accountNumber;

    @Column(nullable = false)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankType bankType;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal balance;

    @Version
    private Long version;
}