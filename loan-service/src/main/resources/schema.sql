DROP TABLE IF EXISTS loan;

CREATE TABLE `loan` (
    `loan_id`            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id`        BIGINT       NOT NULL,
    `loan_number`        BIGINT       NOT NULL,
    `loan_type`          VARCHAR(100) NOT NULL,
    `total_loan`         INT          NOT NULL,
    `amount_paid`        INT          NOT NULL,
    `outstanding_amount` INT          NOT NULL,
    `start_date`         TIMESTAMP    NOT NULL,
    `create_date`        TIMESTAMP    NOT NULL,
    PRIMARY KEY (`loan_id`)
);
