package com.project.notification.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private Long userId;

    private String type; // EMAIL, SMS

    private String recipient;

    private String message;

    private String status; // SENT, FAILED, PENDING

}