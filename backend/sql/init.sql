DROP DATABASE IF EXISTS "event_manager_db";

CREATE DATABASE "event_manager_db" WITH OWNER = "root";

\c event_manager_db;

CREATE TABLE IF NOT EXISTS events (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    status SMALLINT NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE events OWNER to "root";

INSERT INTO events (title, start_date, end_date, price, status) VALUES
('Tech Conference', '2024-09-10 09:00:00', '2024-09-10 11:00:00', 200.0, 1),
('Music Festival', '2024-09-15 14:00:00', '2024-09-15 16:00:00', 250.0, 1),
('Art Exhibition', '2024-09-20 18:00:00', '2024-09-20 20:00:00', 300.0, 0),
('Startup Pitch', '2024-10-05 10:00:00', '2024-10-05 12:00:00', 350.0, 0),
('Book Fair', '2024-10-12 08:00:00', '2024-10-12 10:00:00', 400.0, 1),
('Food Expo', '2024-10-20 15:00:00', '2024-10-20 17:00:00', 450.0, 1),
('Film Screening', '2024-11-01 19:00:00', '2024-11-01 21:00:00', 500.0, 0),
('Charity Run', '2024-11-10 11:00:00', '2024-11-10 13:00:00', 550.0, 0),
('Science Fair', '2024-11-15 07:00:00', '2024-11-15 09:00:00', 600.0, 1),
('Gaming Tournament', '2024-11-25 16:00:00', '2024-11-25 18:00:00', 650.0, 1);