package com.project.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    private String orderId;

    private Long userId;

    private String productName;

    private int quantity;

    private double price;

    private String status;

}