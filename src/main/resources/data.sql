-- ED-259-SA    -- ED-239-AWS Removed id from sql
-- ED-318-SA : removed creator from table
INSERT INTO edufy_user (user_sub, user_username, user_email, user_active) VALUES
('00000000-0000-0000-0001-000000000001', 'Sunstar', 'emil.beckstom@gmail.com', true),
( '00000000-0000-0000-0000-000000000002', 'Neon Pulse', 'mia.nickleson@gmail.com', true),
( '00000000-0000-0000-0003-000000000003', 'WillStorm', 'william.strom@gmail.com',  false),
('00000000-0000-0000-0004-000000000004', 'Woodsburo', 'sydney.prescott@gmail.com',  true),
('00000000-0000-0000-0000-000000000005', 'Pixel Muse', 'mona.lisa@gmail.com', true),
('00000000-0000-0000-0006-000000000006', 'Musse', 'micke.ericson@gmail.com', true),
('00000000-0000-0000-0007-000000000007', 'Mollweee', 'molle.nordstrom@gmail.com', true),
('00000000-0000-0000-0008-000000000008', 'KalleA', 'kalle.anka@gmail.com', true),
('00000000-0000-0000-0009-000000000009', 'MimiP', 'mimi.pid@gmail.com', false),
('00000000-0000-0000-0010-000000000010', 'DustKiller', 'anna.willson@gmail.com', true);
