package com.example.demo.mail.service;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;

@Service
public interface SmsService {
    public Message sendSMS(String phoneNo, String message);
}
