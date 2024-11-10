CREATE DATABASE Book;

USE Book;

CREATE TABLE USERS(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(255)
);

CREATE TABLE BOOKS(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(255),
    PUBLISHER VARCHAR(255),
    PUBLISH_DATE date,
    CREATE_AT timestamp default CURRENT_TIMESTAMP
);