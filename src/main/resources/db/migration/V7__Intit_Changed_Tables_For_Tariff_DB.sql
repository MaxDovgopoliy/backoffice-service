drop table if exists coordinates;
drop table if exists areas;
drop table if exists cities;
drop table if exists orders;
drop table if exists tariffs;
drop table if exists cities_tariffs;
drop table if exists countries;
drop table if exists hibernate_sequence;

create table areas
(
    id      bigint not null,
    city_id bigint,
    primary key (id)
);
create table cities
(
    id                     bigint           not null,
    coefficient_for_tariff double precision not null,
    name                   varchar(255),
    country_id             bigint,
    primary key (id)
);
create table cities_tariffs
(
    tariff_id bigint not null,
    city_id   bigint not null,
    primary key (city_id, tariff_id)
);
create table coordinates
(
    id        bigint           not null,
    latitude  double precision not null,
    longitude double precision not null,
    area_id   bigint,
    city_id   bigint,
    primary key (id)
);
create table countries
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);
create table hibernate_sequence
(
    next_val bigint
);
insert into hibernate_sequence
values (1);
create table orders
(
    id              bigint  not null,
    car_id          integer not null,
    car_type        varchar(255),
    end_date_time   datetime(6),
    price           decimal(19, 2),
    start_date_time datetime(6),
    user_id         integer not null,
    tariff_id       bigint,
    primary key (id)
);
create table tariffs
(
    id            bigint           not null,
    car_type      varchar(255),
    description   varchar(255),
    name          varchar(255),
    rate_per_hour double precision not null,
    primary key (id)
);
alter table areas
    add constraint city_id_areas foreign key (city_id) references cities (id);
alter table cities
    add constraint country_id foreign key (country_id) references countries (id);
alter table cities_tariffs
    add constraint city_id foreign key (city_id) references cities (id);
alter table cities_tariffs
    add constraint tariff_id foreign key (tariff_id) references tariffs (id);
alter table coordinates
    add constraint area_id foreign key (area_id) references areas (id);
alter table coordinates
    add constraint city_id_coordinates foreign key (city_id) references cities (id);
alter table orders
    add constraint tariff_id_orders foreign key (tariff_id) references tariffs (id);

INSERT INTO `countries` (`id`, `name`) VALUES ('1', 'Ukraine');
INSERT INTO `cities` (`id`, `coefficient_for_tariff`, `name`, `country_id`) VALUES ('1', '1.1', 'Lviv', '1');
INSERT INTO `cities` (`id`, `coefficient_for_tariff`, `name`, `country_id`) VALUES ('2', '1.2', 'Kyiv', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`) VALUES ('1', '0', '0', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`) VALUES ('2', '1', '1', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`) VALUES ('3', '1', '-1', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`) VALUES ('4', '2', '0', '2');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`) VALUES ('5', '1', '1', '2');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`) VALUES ('6', '1', '-1', '2');