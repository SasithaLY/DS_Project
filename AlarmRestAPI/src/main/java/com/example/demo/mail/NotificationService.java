package com.example.demo.mail;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class NotificationService {
    private JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
}
