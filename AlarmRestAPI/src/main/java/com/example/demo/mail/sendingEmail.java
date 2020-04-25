package com.example.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;


public class sendingEmail implements CommandLineRunner{

    @Autowired
    private JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(sendingEmail.class, args);
    }
    @Override
    public void run(String... args) {

        System.out.println("Sending Email...");

        try {

            sendEmail();
            //sendEmailWithAttachment();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }


    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("onlinekee@gmail.com");

        msg.setSubject("Alert Message");
        msg.setText("Hello");

        javaMailSender.send(msg);

    }


    

}
