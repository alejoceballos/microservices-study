DROP TABLE IF EXISTS customer;

CREATE TABLE `customer` (
    `customer_id`  BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`         VARCHAR(100) NOT NULL,
    `email`        VARCHAR(320) NOT NULL,
    `phone_number` VARCHAR(15),
    `create_date`  TIMESTAMP    NOT NULL
);

DROP TABLE IF EXISTS account;

CREATE TABLE `account` (
    `account_id`     BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id`    BIGINT       NOT NULL,
    `account_number` VARCHAR(10)  NOT NULL,
    `account_type`   VARCHAR(100) NOT NULL,
    `branch_address` VARCHAR(500) NOT NULL,
    `create_date`    TIMESTAMP    NOT NULL
);
