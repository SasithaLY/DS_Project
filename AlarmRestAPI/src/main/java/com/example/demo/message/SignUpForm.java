package com.example.demo.message;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class SignUpForm {

    @NotBlank
    private String username;
    
    @NotBlank  
    private String password;
    
    @NotBlank
    private String role;
    
    private boolean active;
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
      return this.role;
    }
    
    public void setRole(String role) {
      this.role = role;
    }
}