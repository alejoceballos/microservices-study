DROP TABLE IF EXISTS card;

CREATE TABLE `card` (
    `card_id`          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id`      BIGINT       NOT NULL,
    `card_number`      VARCHAR(100) NOT NULL,
    `card_type`        VARCHAR(100) NOT NULL,
    `total_limit`      INT          NOT NULL,
    `amount_used`      INT          NOT NULL,
    `available_amount` INT          NOT NULL,
    `create_date`      TIMESTAMP    NOT NULL,
    PRIMARY KEY (`card_id`)
);
