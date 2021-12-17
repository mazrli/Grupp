SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS sahlgrenska;
USE sahlgrenska;


CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `zip` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `archive` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `booking` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `ward_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `disease` (
  `id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `latin_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL,
  `person_number` varchar(12) NOT NULL,
  `salary` float DEFAULT NULL,
  `working_hours` float DEFAULT NULL,
  `accessibility` enum('NONE','DOCTOR','RECEPTIONIST','ADMIN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `hospital` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `balance` float DEFAULT NULL,
  `max_capacity` int(11) DEFAULT NULL,
  `storage_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `archive_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `item` (
  `hospital_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(450) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float NOT NULL,
  `type` enum('MEDICINE','EQUIPMENT') NOT NULL,
  `reusable` tinyint(1) DEFAULT '1',
  `expiration_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `journal` (
  `id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `note` varchar(1200) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `login_details` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `online` (
  `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `patient` (
  `id` int(11) NOT NULL,
  `condition_description` varchar(1200) NOT NULL,
  `critical_condition` tinyint(1) NOT NULL DEFAULT '0',
  `blood_type` enum('A','B','AB','O') NOT NULL,
  `person_number` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `person` (
  `person_number` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `gender` enum('MALE','FEMALE','APACHE_HELICOPTER') DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `room` (
  `id` int(11) NOT NULL,
  `size` int(11) NOT NULL DEFAULT '20',
  `ward_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ward` (
  `id` int(11) NOT NULL,
  `hospital_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `archive`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ward_id` (`ward_id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `patient_id` (`patient_id`);

ALTER TABLE `disease`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `person_number` (`person_number`);

ALTER TABLE `hospital`
  ADD PRIMARY KEY (`id`),
  ADD KEY `address_id` (`address_id`),
  ADD KEY `archive_id` (`archive_id`);

ALTER TABLE `journal`
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `patient_id` (`patient_id`);

ALTER TABLE `login_details`
  ADD PRIMARY KEY (`username`,`password`);

ALTER TABLE `online`
  ADD PRIMARY KEY (`employee_id`);

ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `person_number` (`person_number`);

ALTER TABLE `person`
  ADD PRIMARY KEY (`person_number`),
  ADD KEY `address_id` (`address_id`);

ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ward_id` (`ward_id`);

ALTER TABLE `ward`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hospital_id` (`hospital_id`);


ALTER TABLE `disease`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `hospital`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `booking_ibfk_4` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`person_number`) REFERENCES `person` (`person_number`);

ALTER TABLE `hospital`
  ADD CONSTRAINT `hospital_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `hospital_ibfk_2` FOREIGN KEY (`archive_id`) REFERENCES `archive` (`id`);

ALTER TABLE `journal`
  ADD CONSTRAINT `journal_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `journal_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`person_number`) REFERENCES `person` (`person_number`);

ALTER TABLE `person`
  ADD CONSTRAINT `person_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`);

ALTER TABLE `ward`
  ADD CONSTRAINT `ward_ibfk_1` FOREIGN KEY (`hospital_id`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
