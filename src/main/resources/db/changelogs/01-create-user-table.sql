--liquibase formatted sql

CREATE TABLE "users" (
                                id                VARCHAR(36)    NOT NULL,
                                created_at        TIMESTAMP,
                                first_name        VARCHAR (255),
                                last_name         VARCHAR (255),
                                email             VARCHAR (255),
                                password          VARCHAR (255),

                                PRIMARY KEY (id)
);


--rollback drop table users;