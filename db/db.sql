create database parking_ticket_management;
use parking_ticket_management;
create table user (
                      user_id INT AUTO_INCREMENT primary key,
                      username VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(225) NOT NULL,
                      registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      UNIQUE (email)
);