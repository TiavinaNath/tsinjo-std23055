INSERT INTO donor (email, full_name) VALUES
    ('real@hei.school', 'Vrai Donateur Prod');

INSERT INTO payment (id, date, amount, method, status, payer_email, psp_payment_id) VALUES
    ('pay_prod1', CURRENT_TIMESTAMP, 200.00, 'ORANGE_MONEY', 'SUCCEEDED', 'real@hei.school', 'MP250804.0908.D15807');

INSERT INTO donation (donor_email, payment_id) VALUES
    ('real@hei.school', 'pay_prod1');