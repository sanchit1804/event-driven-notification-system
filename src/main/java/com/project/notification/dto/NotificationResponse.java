package com.project.notification.dto;

import lombok.Data;

@Data
public class NotificationResponse {

    private String message;

    private String orderId;

    private String status;

}