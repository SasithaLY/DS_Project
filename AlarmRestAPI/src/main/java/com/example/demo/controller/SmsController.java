package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.User;
import com.example.demo.mail.service.SmsService;
import com.example.demo.service.UserService;
import com.twilio.rest.api.v2010.account.Message;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SmsController {

	@Autowired
	private SmsService smsService;

	@Autowired
	private UserService userService;
	
	private Message msg;

	@PostMapping("api/sendSMS")
	public Message sendSMS(@RequestBody String message) {
		
		List<User> userList = userService.getAllUsers();
		
		try {
			userList.forEach((u) -> {
				
				if (u.getPhone() != null) {
					if (!u.getPhone().isEmpty()) {
						msg = smsService.sendSMS(u.getPhone(), message);
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;
	}
}
