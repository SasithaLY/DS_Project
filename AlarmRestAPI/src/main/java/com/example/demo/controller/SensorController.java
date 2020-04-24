package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SensorController {

	@Autowired
	private SensorService sensorService;
	
	@PostMapping("/api/admin/addSensor")
	public Sensor addSensor(@RequestBody Sensor sensor) {
		
		return sensorService.saveSensor(sensor);
	}
	
	@GetMapping("/api/getSensors")
	public List<Sensor> getAllSensors(){
		
		return sensorService.getAllSensors();
	}
	
	@GetMapping("/api/admin/getSensor/{id}")
	public Sensor getSensorById(@PathVariable int id) {
		
		return sensorService.getSensorById(id);
	}
	
	@PutMapping("/api/admin/updateSensor")
	public Sensor updateSensor(@RequestBody Sensor sensor) {
		
		return sensorService.updateSensor(sensor);
	}
	
	@DeleteMapping("/api/admin/deleteSensor/{id}")
	public String deleteSensor(@PathVariable int id) {
		
		return sensorService.deleteSensor(id);
	}
	
	@PutMapping("/api/setSensorLevels")
	public int setSensorLevels(@RequestBody Sensor sensor) {
		
		return sensorService.setSensorLevels(sensor);
	}
}
