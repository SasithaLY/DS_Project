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

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(addresses);

        email.setSubject("Alarm Alert Message (Critical)");
        email.setText(text);

        javaMailSender.send(email);

    }
}
