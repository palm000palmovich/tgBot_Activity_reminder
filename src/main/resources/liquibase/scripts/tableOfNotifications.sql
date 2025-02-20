-- liquibase formatted sql
-- changeset ityapkin:1
CREATE TABLE notification_task (
    id SERIAL PRIMARY KEY,
    dateTime TIMESTAMP NOT NULL,
    task TEXT NOT NULL,
    chat_id BIGINT
);