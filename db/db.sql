create database parking_ticket_management;
use parking_ticket_management;
create table user
(
    user_id    INT AUTO_INCREMENT primary key,
    username   VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(225) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (email)
);
create table ticket
(
    ticket_id      INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_number VARCHAR(20)    NOT NULL,
    entry_time     TIMESTAMP      NULL,
    exit_time      TIMESTAMP      NULL,
    total_time     BIGINT,
    total_amount   DECIMAL(10, 2) NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by     INT,
    updated_by     INT,
    phone          VARCHAR(10) NULL,
    note           VARCHAR(256)
);





