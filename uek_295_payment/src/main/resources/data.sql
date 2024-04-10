INSERT INTO roles("role_id", "role_name")
VALUES (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO payment ( "payment_method", "chf_exchange_rate", "currency")
VALUES (

        'Card',
        3.5,
        'euro'),
(

    'Cash',
    1.0,
    'dollar'),
(

    'Twint',
    1.4,
    'CHF'),
(

    'PayPal',
    0.9,
    'GBP'),
(

    'Bank Transfer',
    0.5,
    'JPY'),
(

    'Bitcoin',
    8000.0,
    'BTC'),
(

    'Venmo',
    1.2,
    'USD'),
(
    'Apple Pay',
    0.8,
    'EUR');

INSERT INTO authorities ("authority_id", "authority_name")
VALUES(1, 'READ'),
    (2, 'CREATE'),
    (3, 'UPDATE'),
    (4, 'DELETE');

INSERT INTO roles_authorities("role_id", "authority_id")
VALUES(1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 1);
    
INSERT INTO users ("password", "name", "user_role")
VALUES ('12345678', 'admin', 1);
