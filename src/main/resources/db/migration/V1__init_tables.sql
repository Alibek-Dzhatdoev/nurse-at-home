create schema if not exists nurse_at_home;

drop table if exists patient;
create table patients
(
    id            bigserial primary key,
    email         varchar not null unique,
    first_name    varchar not null,
    last_name     varchar not null,
    mobile_phone  varchar not null unique,
    date_of_birth date,
    user_id       uuid    not null unique,
    is_active     boolean default false
);

drop table if exists regions;
create table regions
(
    id   bigserial primary key,
    name varchar not null unique
);

drop table if exists cities;
create table cities
(
    id        bigserial primary key,
    name      varchar not null,
    region_id bigint references regions (id),
    constraint city_in_region_unique unique (name, region_id)
);

drop table if exists streets;
create table streets
(
    id      bigserial primary key,
    name    varchar not null unique,
    city_id bigint references cities (id)
);

drop table if exists addresses;
create table addresses
(
    id        bigserial primary key,
    street_id bigint references streets (id),
    city_id   bigint references cities (id),
    building  varchar not null,
    apartment integer,
    entrance  integer,
    latitude  double precision,
    longitude double precision
);

drop table if exists patients_addresses;
create table patients_addresses
(
    id         bigserial primary key,
    patient_id bigint references patients (id),
    address_id bigint references addresses (id),
    is_primary boolean
);

drop table if exists nurses;
create table nurses
(
    id          bigserial primary key,
    name        varchar not null,
    diploma_url varchar
);

drop table if exists bids;
create table bids
(
    id             bigserial primary key,
    patient_id     bigint references patients (id),
    nurse_id       bigint references nurses (id),
    requested_time timestamp,
    scheduled_time timestamp,
    status         varchar,
    address_id     bigint references addresses (id)
);

drop table if exists services;
create table services
(
    id        bigserial primary key,
    name      varchar          not null,
    price     double precision not null,
    image_url varchar
);