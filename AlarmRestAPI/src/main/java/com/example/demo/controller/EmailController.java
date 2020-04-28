package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.mail.EmailService;
import com.example.demo.service.UserService;
import com.twilio.rest.api.v2010.account.Message;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EmailController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	private String emails;

	@PostMapping("api/sendEmail")
	public void sendEmail(@RequestBody String message) {
		
		List<User> userList = userService.getAllUsers();

		emails = "";
		
		try {
			userList.forEach((u) -> {

				if (u.getEmail() != null) {
					if (!u.getEmail().isEmpty()) {
						emailService.sendEmail(message, u.getEmail().toString());
					}
				}

			});
			
			/*
			 * if(!emails.equals("")) { emailService.sendEmail(message, emails); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
