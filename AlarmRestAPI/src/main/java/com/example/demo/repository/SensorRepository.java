package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Sensor s SET s.co2 = ?1 , s.smoke = ?2 WHERE s.sensorId = ?3")
	int setSensorLevels(int co2, int smoke, int id);
}
