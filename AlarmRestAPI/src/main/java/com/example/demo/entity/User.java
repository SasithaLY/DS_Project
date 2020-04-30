package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
 
    @NotBlank
    @Column(name = "username")
    private String username;
 
    @NotBlank
    @Column(name = "password")
    private String password;
    
    @NotBlank
    @Column(name = "role")
	private String role;
    
    @Column(name = "phone")
	private String phone;
    
    @Column(name = "email")
	private String email;

	@Column(name = "active")
	private boolean active;

	public User(String username, String password, String role, String phone, String email, boolean active) {
	
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.active = active;
	}

	
	public User() {
		super();
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}
