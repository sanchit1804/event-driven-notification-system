package com.project.analytics.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.project.analytics.service.AnalyticsService;
import com.project.event.OrderCreatedEvent;

@Component
public class AnalyticsConsumer {

    private final AnalyticsService analyticsService;


    public AnalyticsConsumer(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }


    @KafkaListener(
            topics = "order-events",
            groupId = "analytics-group"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println(
                "Analytics event received: "
                + event.getOrderId()
        );

        analyticsService.processOrderEvent(event);
    }
}