--liquibase formatted sql

--changeset v.petrova:1 endDelimiter:/

create table devices
(
    id                  int not null generated always as identity,
    name                varchar not null,
    category            varchar not null,
    primary key (id)
);

create table capabilities
(
    id                  int not null generated always as identity,
    code                varchar not null,
    value               varchar not null,
    device              int not null references devices on delete cascade,
    primary key (id)
);