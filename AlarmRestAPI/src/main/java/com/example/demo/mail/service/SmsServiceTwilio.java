package com.example.demo.mail.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsServiceTwilio implements SmsService{

    public static final String ACCOUNT_No = "0771776848";
    public static final String AUTH_To= "0710559937";

    public Message sendSMS() {
        Twilio.init(ACCOUNT_No, AUTH_To);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+94771776848"),//The phone number you are sending text to
                new com.twilio.type.PhoneNumber("+94710559937"),//The Twilio phone number
                "You have a new Alert")
                .create();

        return message;
    }

}
