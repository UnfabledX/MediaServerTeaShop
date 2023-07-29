CREATE SCHEMA IF NOT EXISTS teashop_db;

CREATE TABLE IF NOT EXISTS teashop_db.images
(
    id                bigserial,
    file_name         character varying NOT NULL UNIQUE,
    file_type         character varying NOT NULL,
    image_size        bigint            NOT NULL,
    created_by        character varying,
    created_date      timestamp without time zone,
    updated_by        character varying,
    updated_date      timestamp without time zone,
    data              bytea             NOT NULL,
    PRIMARY KEY (id)

);