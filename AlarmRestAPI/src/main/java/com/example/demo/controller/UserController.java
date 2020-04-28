package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/api/admin/getAllUsers")
	public List<User> getAllUsers() {

		return userService.getAllUsers();
	}
	
	@GetMapping("/api/admin/getUser/{id}")
	public User getUserById(@PathVariable int id) {

		return userService.getUserById(id);
	}

	@PutMapping("/api/admin/updateUser")
	public User updateUser(@RequestBody User user) {

		return userService.updateUser(user);
	}

	@DeleteMapping("/api/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {

		return userService.deleteUser(id);
	}
}
