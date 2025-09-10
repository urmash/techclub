INSERT INTO hobby_group (name, description, created_at) VALUES
('Music Club', 'School choir and band rehearsals', '2025-01-01 10:00:00'),
('Sports Club', 'Football and basketball', '2025-02-01 12:00:00'),
('Art Club', 'Drawing and painting', '2025-03-01 14:00:00');

INSERT INTO participant (first_name, last_name, joined_at) VALUES
('Jaan', 'Tamm', '2025-01-15 09:00:00'),
('Kati', 'Saar', '2025-02-10 11:00:00'),
('Marten', 'Põder', '2025-03-05 13:00:00');

INSERT INTO activity (hobby_group_id, name, scheduled_at) VALUES
(1, 'Choir Rehearsal', '2025-04-01 15:00:00'),
(1, 'Band Rehearsal', '2025-04-08 15:00:00'),
(2, 'Football Practice', '2025-04-10 16:00:00');

INSERT INTO membership (hobby_group_id, participant_id, joined_at) VALUES
(1, 1, '2025-01-20 10:00:00'),
(1, 2, '2025-02-15 11:00:00'),
(2, 2, '2025-02-15 11:00:00'),
(3, 3, '2025-03-10 12:00:00');
