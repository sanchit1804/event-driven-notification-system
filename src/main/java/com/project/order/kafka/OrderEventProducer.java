package com.project.order.kafka;

import com.project.order.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;


    public OrderEventProducer(
            KafkaTemplate<String, Order> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendOrderEvent(Order order) {

        kafkaTemplate.send(
                topicName,
                order.getOrderId(),
                order
        );
    }
}