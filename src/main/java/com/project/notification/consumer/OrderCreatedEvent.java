package com.project.event;

import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;

    private Long userId;

    private String productName;

    private Integer quantity;

    private Double price;

    private String status;

}