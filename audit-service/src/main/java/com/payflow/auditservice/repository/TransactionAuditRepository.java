package com.payflow.auditservice.repository;

import com.payflow.auditservice.entity.TransactionAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionAuditRepository
        extends JpaRepository<TransactionAudit, Long> {
}