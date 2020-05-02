package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	PasswordEncoder encoder;

	public List<User> getAllUsers() {

		return userRepo.findAll();
	}

	public User getUserById(int id) {

		return userRepo.findById(id).orElse(null);
	}

	public String deleteUser(int id) {

		userRepo.deleteById(id);

		return "user removed " + id;
	}

	public User updateUser(User user) {

		User currentUser = userRepo.findById(user.getId()).orElse(null);
		
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(encoder.encode(user.getPassword()));
		currentUser.setPhone(user.getPhone());
		currentUser.setEmail(user.getEmail());
		currentUser.setRole(user.getRole());
		currentUser.setActive(user.isActive());

		return userRepo.save(currentUser);

	}
}
