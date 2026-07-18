package com.project.audit.service;

import org.springframework.stereotype.Service;

import com.project.audit.entity.AuditLog;
import com.project.audit.repository.AuditRepository;
import com.project.event.OrderCreatedEvent;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }


    public void saveAudit(OrderCreatedEvent event) {

        AuditLog auditLog = new AuditLog();

        auditLog.setAction(event.getEventType().toString());
        auditLog.setEntity("ORDER");
        auditLog.setEntityId(event.getOrderId());
        auditLog.setUserId(String.valueOf(event.getUserId()));
        auditLog.setTimestamp(LocalDateTime.now());

        auditRepository.save(auditLog);
    }
}