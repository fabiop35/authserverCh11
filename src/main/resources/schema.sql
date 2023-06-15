CREATE TABLE IF NOT EXISTS spring.user (username VARCHAR(45) NULL, password TEXT NULL,PRIMARY KEY (username));

CREATE TABLE otp (username VARCHAR(45) NOT NULL, code VARCHAR(45), PRIMARY KEY (username));


