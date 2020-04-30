package com.example.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender javaMailSender;

	public void sendEmail(String text, String addresses) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(addresses);

        message.setSubject("Alarm Alert Message (Critical)");
        message.setText(text);

        javaMailSender.send(message);

    }
}
