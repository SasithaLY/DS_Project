package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mail.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;


public class SmsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public Message sendSMS() {
        return smsService.sendSMS();
    }

}
