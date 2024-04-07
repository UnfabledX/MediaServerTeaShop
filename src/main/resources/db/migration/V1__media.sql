CREATE SCHEMA IF NOT EXISTS media_service;

CREATE TABLE IF NOT EXISTS media_service.images
(
    id                bigserial,
    file_name         character varying NOT NULL,
    file_type         character varying NOT NULL,
    image_size        bigint            NOT NULL,
    created_date      timestamp without time zone,
    updated_date      timestamp without time zone,
    data              bytea             NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (file_name, file_type)
);