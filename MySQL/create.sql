#DROP DATABASE IF EXISTS sahlgrenska;
CREATE DATABASE IF NOT EXISTS sahlgrenska;
USE sahlgrenska;

CREATE TABLE IF NOT EXISTS address (
	id INT NOT NULL PRIMARY KEY,
	country VARCHAR(255),
    city VARCHAR(255),
    zip VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS archive (
	id INT PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS sjukhus (
	name VARCHAR(255) PRIMARY KEY NOT NULL,
    balance FLOAT,
    maxCapacity INT,
    storage_id INT,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES address(id),
	archive_id INT,
    FOREIGN KEY (archive_id) REFERENCES archive(id)

);





CREATE TABLE IF NOT EXISTS person (
    person_number VARCHAR(255) PRIMARY KEY NOT NULL,
	name VARCHAR(255),
    phone_number VARCHAR(255),
    gender ENUM("MALE", "FEMALE", "APACHE HELICOPTER"),
    address_id int,
    FOREIGN KEY(address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS login_details (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    employee_id INT NOT NULL,
    PRIMARY KEY(username, password)
);

CREATE TABLE IF NOT EXISTS employee (
	id INT PRIMARY KEY NOT NULL,
    #List<bookings>
    salary FLOAT,
    working_hours FLOAT,
    accessibility ENUM("NONE", "DOCTOR", "RECEPTIONIST", "ALL")
);

CREATE TABLE IF NOT EXISTS ward (
	id INT NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS room (
	id INT NOT NULL PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS booking (
	id INT NOT NULL PRIMARY KEY,
	date DATETIME,
    # List<emp>
    # List<patient>
    ward_id INT,
    room_id INT,
    FOREIGN KEY(ward_id) REFERENCES ward(id),
    FOREIGN KEY(room_id) REFERENCES room(id)
);




