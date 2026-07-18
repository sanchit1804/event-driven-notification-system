package com.project.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger log =
            LoggerFactory.getLogger(SmsService.class);


    public void sendSms(String phoneNumber, String message) {

        // Simulate sending SMS
        log.info(
            "Sending SMS | Phone: {} | Message: {}",
            phoneNumber,
            message
        );
    }
}