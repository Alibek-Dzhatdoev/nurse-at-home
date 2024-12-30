create schema if not exists nurse_at_home;

drop table if exists patient;
create table patients
(
    id            bigserial primary key,
    email         varchar not null unique,
    firstname     varchar not null,
    lastname      varchar not null,
    mobile_phone  varchar not null unique,
    date_of_birth date,
    user_id       uuid    not null unique,
    is_active     boolean default false
);

CREATE INDEX patient_user_id_index
    ON patients (user_id);

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
    name      varchar                        not null,
    region_id bigint references regions (id) not null,
    constraint city_in_region_unique_constraint unique (name, region_id)
);

drop table if exists streets;
create table streets
(
    id      bigserial primary key,
    name    varchar                       not null,
    city_id bigint references cities (id) not null,
    constraint street_in_city_unique_constraint unique (name, city_id)
);

drop table if exists addresses;
create table addresses
(
    id        bigserial primary key,
    street_id bigint references streets (id),
    city_id   bigint references cities (id),
    entrance  int,
    building  varchar not null,
    apartment int,
    latitude  double precision,
    longitude double precision,
    constraint address_unique_constraint unique (street_id, city_id, building, apartment, entrance)
);

CREATE INDEX address_index
    ON addresses (street_id, city_id, building, entrance, apartment);

drop table if exists patients_addresses;
create table patients_addresses
(
    id         bigserial primary key,
    patient_id bigint references patients (id),
    address_id bigint references addresses (id),
    is_primary boolean,
    constraint patient_address_unique_constraint unique (patient_id, address_id)
);

CREATE INDEX patient_address_index
    ON patients_addresses (patient_id, address_id);

drop table if exists nurses;
create table nurses
(
    id           bigserial primary key,
    firstname    varchar not null,
    lastname     varchar not null,
    diploma_url  varchar,
    passport_url varchar,
    photo_url    varchar,
    address_id   bigint references addresses (id),
    searchRadius varchar not null,
    user_id      uuid    not null unique,
    is_available boolean not null default false,
    is_verified  boolean not null default false,
    rating       double precision
);

CREATE INDEX nurses_user_id_index
    ON nurses (user_id);

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

CREATE INDEX nurse_bid_status_index
    ON bids (nurse_id, status);

CREATE INDEX nurse_bids_index
    ON bids (nurse_id);

CREATE INDEX patient_bids_index
    ON bids (patient_id, status);

drop table if exists reviews;
create table reviews
(
    id        bigserial primary key,
    bid_id bigint references bids (id) not null,
    text      text,
    rate      int                         not null,
    date      date                        not null
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
    nurse_id   bigint references nurses (id)   not null,
    patient_id bigint references patients (id) not null,
    initiator  varchar                         not null,
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
    nurse_id     bigint references nurses (id)     not null,
    procedure_id bigint references procedures (id) not null,
    constraint nurse_procedure_unique_constraint unique (nurse_id, procedure_id)
);

CREATE INDEX nurse_procedure_index
    ON nurses_procedures (nurse_id);
