package com.payflow.settlementservice.repository;

import com.payflow.settlementservice.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementRepository
        extends JpaRepository<Settlement,Long> {
}