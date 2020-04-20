package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sensor;
import com.example.demo.repository.SensorRepository;

@Service
public class SensorService {

	@Autowired
	private SensorRepository sensorRepository;
	
	public Sensor saveSensor(Sensor sensor) {
				
		return sensorRepository.save(sensor);	
	}
	
	public List<Sensor> getAllSensors(){
		
		return sensorRepository.findAll();
	}
	
	public Sensor getSensorById(int id) {
		
		return sensorRepository.findById(id).orElse(null);
	}
	
	public String deleteSensor(int id) {
		
		sensorRepository.deleteById(id);
		
		return "sensor removed "+id;
	}
	
	public Sensor updateSensor(Sensor sensor) {
	
		Sensor currentSensor = sensorRepository.findById(sensor.getSensorId()).orElse(null);
		currentSensor.setRoomNo(sensor.getRoomNo());
		currentSensor.setFloorNo(sensor.getFloorNo());
		currentSensor.setSmoke(sensor.getSmoke());
		currentSensor.setCo2(sensor.getCo2());
		currentSensor.setActive(sensor.getActive());
		
		return sensorRepository.save(currentSensor);
		
	}
	
}
