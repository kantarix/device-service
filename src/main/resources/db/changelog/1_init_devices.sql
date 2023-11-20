--liquibase formatted sql

--changeset v.petrova:1 endDelimiter:/

create table devices
(
    id                      int not null generated always as identity,
    name                    varchar not null,
    category                varchar not null,
    primary key (id)
);

create table capabilities
(
    device_id               int not null unique references devices(id) on delete cascade,
    switch_led              boolean not null,
    work_mode               varchar not null,
    temperature             int not null,
    brightness              int not null,
    color_hue               int,
    color_saturation        int,
    color_value             int,
    primary key (device_id)
);