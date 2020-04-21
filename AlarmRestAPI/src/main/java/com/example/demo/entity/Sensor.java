package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "alarm_sensor")
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sensor_id")
	private int sensorId;
	
    @Column(name = "floor_no")
	private int floorNo;
    
    @Column(name = "room_no")
	private int roomNo;
    
    @Column(name = "smoke", columnDefinition = "integer default 0")
    @Min(0)
    @Max(10)
	private int smoke;
    
    @Column(name = "co2", columnDefinition = "integer default 0")
    @Min(0)
    @Max(10)
	private int co2;
    
    @Column(name = "active")
	private int active;
	
	
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public int getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getSmoke() {
		return smoke;
	}
	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}
	public int getCo2() {
		return co2;
	}
	public void setCo2(int co2) {
		this.co2 = co2;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	
	
}
