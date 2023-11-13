--liquibase formatted sql

--changeset v.petrova:1 endDelimiter:/

create table devices
(
    id                  int not null generated always as identity,
    name                varchar not null,
    primary key (id)
);