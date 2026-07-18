package com.project.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.notification.entity.Notification;
import com.project.notification.repository.NotificationRepository;

@Service
public class NotificationService {

    private static final Logger log =
            LoggerFactory.getLogger(NotificationService.class);

    private final EmailService emailService;
    private final SmsService smsService;
    private final NotificationRepository notificationRepository;


    public NotificationService(
            EmailService emailService,
            SmsService smsService,
            NotificationRepository notificationRepository
    ) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.notificationRepository = notificationRepository;
    }


    public void sendNotification(
            String type,
            String recipient,
            String message,
            String orderId,
            Long userId
    ) {

        log.info("Processing notification type: {}", type);


        Notification notification = new Notification();

        notification.setType(type);
        notification.setRecipient(recipient);
        notification.setMessage(message);
        notification.setOrderId(orderId);
        notification.setUserId(userId);
        notification.setStatus("PENDING");


        try {

            if ("EMAIL".equalsIgnoreCase(type)) {

                emailService.sendEmail(
                        recipient,
                        "Order Notification",
                        message
                );


            } else if ("SMS".equalsIgnoreCase(type)) {

                smsService.sendSms(
                        recipient,
                        message
                );


            } else {

                log.warn("Unsupported notification type: {}", type);
                notification.setStatus("FAILED");

                notificationRepository.save(notification);
                return;
            }


            notification.setStatus("SENT");

        } catch (Exception e) {

            log.error("Notification failed", e);

            notification.setStatus("FAILED");
        }


        notificationRepository.save(notification);

        log.info("Notification saved with status: {}",
                notification.getStatus());
    }
}