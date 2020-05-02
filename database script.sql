
-- create the database `projectdb`
CREATE SCHEMA `projectdb`;

-- Table structure for table `alarm_sensor`
CREATE TABLE `projectdb`.`alarm_sensor` (
  `sensor_id` INT NOT NULL AUTO_INCREMENT,
  `floor_no` INT NOT NULL,
  `room_no` INT NOT NULL,
  `co2` INT NOT NULL,
  `smoke` INT NOT NULL,
  `active` INT NOT NULL,
  PRIMARY KEY (`sensor_id`));
  
--   insert sample data into table `alarm_sensor`
  INSERT INTO `projectdb`.`alarm_sensor` (`sensor_id`, `floor_no`, `room_no`, `co2`, `smoke`, `active`) VALUES
(1, 2, 1, 0, 0, 1),
(2, 1, 2, 0, 0, 1),
(3, 1, 1, 0, 0, 1);

-- Table structure for table `user`
CREATE TABLE `projectdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `phone` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `role` VARCHAR(10) NOT NULL,
  `active` INT NOT NULL,
  PRIMARY KEY (`id`));
  
  --   insert sample data into table `user`
  INSERT INTO `projectdb`.`user` (`id`,`username`, `password`, `phone`, `email`, `role`, `active`) VALUES
(1, 'admin', '$2a$04$Kms/VD145HFnxUPpoSX0s.1F3YgzuhhksOPVXqA1fOVvd7kIwWNRm', '+94710559937', 'mail.dsproject@gmail.com', 'Admin', 1)

