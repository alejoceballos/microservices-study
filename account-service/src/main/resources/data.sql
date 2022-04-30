INSERT INTO `customer` (
  `name`
, `email`
, `phone_number`
, `create_date`
) VALUES (
  'Alejo Ceballos'
, 'alejoceballos75@gmail.com'
, '5581987654321'
, CURRENT_TIMESTAMP()
);

INSERT INTO `account` (
  `customer_id`
, `account_number`
, `account_type`
, `branch_address`
, `create_date`
) VALUES (
  1
, '1234567890'
, 'Savings'
, 'Somewhere in the Night, 333, Far From Here'
, CURRENT_TIMESTAMP()
);
