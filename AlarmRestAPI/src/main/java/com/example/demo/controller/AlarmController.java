package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController {

	//these are testing methods, will be implemented later
	 @GetMapping("/api/getSensors")
	  public String userAccess() {
	    return ">>> All Alarm Sensors!";
	  }
	 
	  
	 //all paths with (/api/admin..) will need to login
	  @GetMapping("/api/admin/addSensor")
	  public String adminAccess() {
	    return ">>> Admin Function";
	  }
}
