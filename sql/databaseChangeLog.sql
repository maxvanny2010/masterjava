--liquibase formatted sql

--changeset gkislin:1
CREATE SEQUENCE common_seq START 100000;

CREATE TABLE city
(
    ref  TEXT PRIMARY KEY,
    name TEXT NOT NULL
);

ALTER TABLE users
    ADD COLUMN city_ref TEXT REFERENCES city (ref) ON UPDATE CASCADE;

--changeset gkislin:2
CREATE TABLE project
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('common_seq'),
    name        TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TYPE GROUP_TYPE AS ENUM ('REGISTERING', 'CURRENT', 'FINISHED');

CREATE TABLE groups
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('common_seq'),
    name       TEXT UNIQUE NOT NULL,
    type       GROUP_TYPE  NOT NULL,
    project_id INTEGER     NOT NULL REFERENCES project (id)
);

CREATE TABLE user_group
(
    user_id  INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    group_id INTEGER NOT NULL REFERENCES groups (id),
    CONSTRAINT users_group_idx UNIQUE (user_id, group_id)
);
--changeset maxvanny:3
CREATE SEQUENCE addressee_seq START 100000;
CREATE TABLE addressee
(
    id    INTEGER PRIMARY KEY DEFAULT nextval('addressee_seq'),
    email TEXT UNIQUE NOT NULL,
    name  TEXT        NOT NULL
);
--changeset maxvanny:4
CREATE SEQUENCE emails_seq START 100000;
CREATE TABLE emails
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('emails_seq'),
    sender_id   INTEGER NOT NULL REFERENCES addressee (id),
    receiver_id INTEGER NOT NULL REFERENCES addressee (id),
    header      TEXT    NOT NULL,
    body        TEXT    NOT NULL
);
--changeset maxvanny:5
DROP INDEX email_idx;
ALTER TABLE users
    DROP COLUMN email;
ALTER TABLE users
    ADD COLUMN email_id INTEGER REFERENCES emails (id) ON DELETE CASCADE;
--changeset maxvanny:6
ALTER TABLE addressee
    ADD CONSTRAINT addressee_unique_idx UNIQUE (email, name);
--changeset maxvanny:7
ALTER TABLE users
    DROP COLUMN email_id;
ALTER TABLE users
    ADD COLUMN email_id INTEGER REFERENCES emails (id) ON DELETE SET NULL;
--changeset maxvanny:8
ALTER TABLE users
    DROP COLUMN email_id;
ALTER TABLE users
    ADD COLUMN email TEXT UNIQUE NOT NULL default 0;


