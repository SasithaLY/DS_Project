package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

	
}
