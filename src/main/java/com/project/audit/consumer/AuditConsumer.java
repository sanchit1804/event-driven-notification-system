package com.project.audit.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.project.audit.service.AuditService;
import com.project.event.OrderCreatedEvent;

@Component
public class AuditConsumer {

    private final AuditService auditService;

    public AuditConsumer(AuditService auditService) {
        this.auditService = auditService;
    }


    @KafkaListener(
            topics = "order-events",
            groupId = "audit-group"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println("Audit event received: " + event.getOrderId());

        auditService.saveAudit(event);
    }
}