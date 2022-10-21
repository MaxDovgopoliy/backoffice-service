drop table if exists coordinates;
drop table if exists areas;
drop table if exists cities_tariffs;
drop table if exists cities;
drop table if exists orders;
drop table if exists tariffs;
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
    id       bigint not null,
    currency varchar(255),
    unit_of_speed varchar(255),
    name     varchar(255),
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
    currency        varchar(255),
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
    currency      varchar(255),
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

INSERT INTO `countries` (`id`, `currency`,`unit_of_speed`, `name`)
VALUES ('1', 'UAH', 'KPH', 'Ukraine');
INSERT INTO `countries` (`id`, `currency`, `unit_of_speed`, `name`)
VALUES ('2', 'USD', 'MPH', 'USA');

INSERT INTO `cities` (`id`, `coefficient_for_tariff`, `name`, `country_id`)
VALUES ('1', '1.1', 'Lviv', '1');
INSERT INTO `cities` (`id`, `coefficient_for_tariff`, `name`, `country_id`)
VALUES ('2', '1.2', 'Kyiv', '1');
INSERT INTO `cities` (`id`, `coefficient_for_tariff`, `name`, `country_id`)
VALUES ('3', '2', 'Vashington', '2');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('1', '45', '30', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('2', '45', '20', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('3', '55', '20', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('4', '55', '30', '1');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('5', '22', '12', '2');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('6', '12', '12', '2');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('7', '20', '0', '2');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('8', '0', '0', '3');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('9', '10', '10', '3');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('10', '10', '0', '3');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('11', '0', '10', '3');

INSERT INTO `tariffs` (`id`, `car_type`, `currency`, `description`, `name`, `rate_per_hour`)
VALUES ('1', 'medium', 'UAH', 'good for family trips', 'family', '200');
INSERT INTO `tariffs` (`id`, `car_type`, `currency`, `description`, `name`, `rate_per_hour`)
VALUES ('2', 'premium', 'UAH', 'convenient and prestigious', 'lakshery', '300');
INSERT INTO `tariffs` (`id`, `car_type`, `currency`, `description`, `name`, `rate_per_hour`)
VALUES ('3', 'economy', 'UAH', 'good for fast and regular trips', 'standart', '100');
INSERT INTO `tariffs` (`id`, `car_type`, `currency`, `description`, `name`, `rate_per_hour`)
VALUES ('4', 'business', 'UAH', 'good for work trips', 'work', '250');

INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('1', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('2', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('3', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('4', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('1', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('2', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('3', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('4', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('1', '3');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('2', '3');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('3', '3');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('4', '3');

INSERT INTO `orders` (`id`, `car_id`, `car_type`, `currency`, `end_date_time`, `price`, `start_date_time`, `user_id`,
                      `tariff_id`)
VALUES ('1', '1', 'medium', 'UAH', '2021-01-01 08:00:00.000000', '200', '2021-01-01 07:00:00.000000', '7', '1');
INSERT INTO `orders` (`id`, `car_id`, `car_type`, `currency`, `end_date_time`, `price`, `start_date_time`, `user_id`,
                      `tariff_id`)
VALUES ('2', '13', 'premium', 'UAH', '2021-01-01 08:00:00.000000', '300', '2021-01-01 07:00:00.000000', '9', '2');
INSERT INTO `orders` (`id`, `car_id`, `car_type`, `currency`, `end_date_time`, `price`, `start_date_time`, `user_id`,
                      `tariff_id`)
VALUES ('3', '16', 'premium', 'UAH', '2021-02-01 08:00:00.000000', '300', '2021-02-01 07:00:00.000000', '71', '2');

INSERT INTO `areas` (`id`, `city_id`) VALUES ('1', '1');
INSERT INTO `areas` (`id`, `city_id`) VALUES ('2', '2');
INSERT INTO `areas` (`id`, `city_id`) VALUES ('3', '3');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('12', '45', '30', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('13', '45', '20', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('14', '55', '20', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('15', '55', '30', '1');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('16', '22', '12', '2');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('17', '12', '12', '2');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('18', '20', '0', '2');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('19', '0', '0', '3');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('20', '10', '10', '3');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('21', '10', '0', '3');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `area_id`)
VALUES ('22', '0', '10', '3');
