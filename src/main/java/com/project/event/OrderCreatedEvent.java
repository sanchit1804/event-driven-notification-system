package com.project.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private String eventType;
    private String orderId;
    private Long userId;
    private String productName;
    private int quantity;
    private double price;
    private String status;
}