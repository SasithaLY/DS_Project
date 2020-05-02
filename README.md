# alarm_rest_api
DS Project Fire Alarm Sensor Rest API

>>> API Documentation

*Login user

	url: http://localhost:8080/api/auth/signin

	Inpu JSON:{
		"username": "sasitha",
		"password": "sasitha123"
	}

*SignUp User (need admin login authentication)

	url: http://localhost:8080/api/auth/signup

	Input JSON: {
		"username":"test",
		"password":"test",
		"phone":"+94710669965"
		"email":"abcd@gmail.com"
		"role": "Admin",
		"active":1
	}

*Add Sensor (need admin login authentication)

	url: http://localhost:8080/api/admin/addSensor

	Input JSON: {
		"floorNo": 1,
		"roomNo": 1,
		"active":1
	}

*update Sensor (need admin login authentication)

	url: http://localhost:8080/api/admin/updateSensor

	Input JSON: {
		"sensorId": 4,
		"floorNo": 5,
		"roomNo": 1,
		"active": 1
	}

*Get All Sensors

	url: http://localhost:8080/api/getSensors

	It will return a JSON array

*Get Single Sensor (need admin login authentication)

	url: http://localhost:8080/api/admin/getSensor/id

	ex:- http://localhost:8080/api/admin/getSensor/5

	It will return a JSON object


*Set Sensor Levels (smoke and co2)

	url: http://localhost:8080/api/setSensorLevels

	JSON Input: {
		"sensorId":4,
		"smoke": 5,
		"co2": 5
	}
