-- liquibase formatted sql
-- changeset ityapkin:2
  CREATE TABLE notifications_task (
    id SERIAL PRIMARY KEY,
    dateTime TIMESTAMP NOT NULL,
    task TEXT NOT NULL,
    chat_id VARCHAR(128)
);