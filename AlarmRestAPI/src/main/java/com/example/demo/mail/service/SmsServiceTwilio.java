package com.example.demo.mail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsServiceTwilio implements SmsService{

	@Value("${TWILIO_SID}")
    public String SID;
	 
	@Value("${TWILIO_AUTH_TOKEN}")
    public String TOKEN;
	
	@Value("${PHONE_NO}")
	public String twilioPhoneNo;
	
    public Message sendSMS(String phoneNo, String msg) {
        Twilio.init(SID, TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phoneNo),//The phone number you are sending text to
                new com.twilio.type.PhoneNumber(twilioPhoneNo),//The Twilio phone number
                msg)
                .create();

        return message;
    }

}
