--liquibase formatted sql

--changeset v.petrova:1 endDelimiter:/

create table devices
(
    id                      int not null generated always as identity,
    tuya_id                 varchar not null unique,
    owner_id                int not null,
    home_id                 int not null,
    room_id                 int,
    name                    varchar not null,
    category                varchar not null,
    primary key (id)
);

create table capabilities
(
    device_id               int not null unique references devices(id),
    switch_led              boolean not null,
    work_mode               varchar not null,
    temperature             int not null,
    brightness              int not null,
    color_hue               int,
    color_saturation        int,
    color_value             int,
    primary key (device_id)
);