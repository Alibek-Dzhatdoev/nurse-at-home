create schema if not exists nurse_at_home;

drop table if exists patient;
create table patients
(
    id            uuid primary key,
    email         varchar not null unique,
    firstname     varchar not null,
    lastname      varchar not null,
    mobile_phone  varchar not null unique,
    date_of_birth date,
    sso_user_id   uuid    not null unique,
    is_active     boolean default false
);

CREATE INDEX patient_sso_user_id_index
    ON patients (sso_user_id);

CREATE INDEX patient_email_index
    ON patients (email);

DROP TABLE IF EXISTS countries;
create table countries
(
    id   bigserial primary key,
    name varchar(50) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS provinces;
create table provinces
(
    id   bigserial primary key,
    name varchar(50) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS areas;
create table areas
(
    id   bigserial primary key,
    name varchar(50) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS localities;
create table localities
(
    id   bigserial primary key,
    name varchar(50) NOT NULL UNIQUE
);

drop table if exists addresses;
create table addresses
(
    id          bigserial primary key,
    country_id  bigint references countries (id)  NOT NULL,
    province_id bigint references provinces (id),
    area_id     bigint references areas (id),
    locality_id bigint references localities (id) NOT NULL,
    street      VARCHAR,
    house       VARCHAR                           NOT NULL,
    entrance    int,
    apartment   int,
    floor       int,
    intercom    int,
    timezone    int,
    latitude    double precision,
    longitude   double precision,
    constraint address_unique_constraint unique (country_id, province_id, area_id, locality_id, street,
                                                 house, entrance,
                                                 apartment, floor, intercom, latitude, longitude)
);

CREATE INDEX address_index
    ON addresses (country_id, province_id, area_id, locality_id, street, house, entrance, apartment, floor,
                  intercom);

drop table if exists patients_addresses;
create table patients_addresses
(
    id         bigserial primary key,
    patient_id uuid references patients (id),
    address_id bigint references addresses (id),
    is_primary boolean,
    constraint patient_address_unique_constraint unique (patient_id, address_id)
);

CREATE INDEX patient_address_index
    ON patients_addresses (patient_id, address_id);

CREATE UNIQUE INDEX patient_address_primary_unique_idx
    ON patients_addresses (patient_id)
    WHERE is_primary = true;

drop table if exists nurses;
create table nurses
(
    id            uuid primary key,
    firstname     varchar not null,
    lastname      varchar not null,
    email         varchar not null unique,
    diploma_url   varchar,
    passport_url  varchar,
    photo_url     varchar,
    address_id    bigint references addresses (id),
    search_radius varchar not null,
    sso_user_id   uuid    not null unique,
    is_available  boolean default false,
    is_verified   boolean default false,
    rating        double precision
);

CREATE INDEX nurses_user_id_index
    ON nurses (sso_user_id);

drop table if exists bids;
create table bids
(
    id             bigserial primary key,
    patient_id     uuid references patients (id),
    nurse_id       uuid references nurses (id),
    requested_time timestamp,
    scheduled_time timestamp,
    status         varchar,
    address_id     bigint references addresses (id)
);

CREATE INDEX nurse_bid_status_index
    ON bids (nurse_id, status);

CREATE INDEX nurse_bids_index
    ON bids (nurse_id);

CREATE INDEX patient_bids_index
    ON bids (patient_id, status);

drop table if exists reviews;
create table reviews
(
    id     bigserial primary key,
    bid_id bigint references bids (id) not null,
    rate   int                         not null,
    date   date                        not null
);

CREATE INDEX bids_review_index
    ON reviews (bid_id);

drop table if exists procedures;
create table procedures
(
    id          bigserial primary key,
    name        varchar          not null,
    description varchar          not null,
    price       double precision not null,
    image_url   varchar,
    is_active   boolean default true
);

drop table if exists nurse_patient_blacklist;
create table nurse_patient_blacklist
(
    id         bigserial primary key,
    nurse_id   uuid references nurses (id)   not null,
    patient_id uuid references patients (id) not null,
    initiator  varchar                       not null,
    constraint blacklist_unique_constraint unique (nurse_id, patient_id, initiator)
);

CREATE INDEX nurse_initiator_index
    ON nurse_patient_blacklist (nurse_id, initiator);

CREATE INDEX patient_initiator_index
    ON nurse_patient_blacklist (patient_id, initiator);

drop table if exists nurses_procedures;
create table nurses_procedures
(
    id           bigserial primary key,
    nurse_id     uuid references nurses (id)       not null,
    procedure_id bigint references procedures (id) not null,
    constraint nurse_procedure_unique_constraint unique (nurse_id, procedure_id)
);

CREATE INDEX nurse_procedure_index
    ON nurses_procedures (nurse_id);
