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
('Event 1', '2024-10-01 08:00:00', '2024-10-01 12:00:00', 100.0, 0),
('Event 2', '2024-10-02 20:00:00', '2024-10-02 23:00:00', 150.0, 0);