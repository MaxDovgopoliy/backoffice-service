# drop table  if exists hibernate_sequence;
# drop table  if exists orders;
# drop table  if exists tariffs;
# drop table  if exists coordinates;
# drop table  if exists areas;
#
# create table areas
# (
#     id      bigint           not null,
#     address varchar(255),
#     square  double precision not null,
#     city_id bigint,
#     primary key (id)
# );
# create table cities
# (
#     id         bigint           not null,
#     name       varchar(255),
#     square     double precision not null,
#     country_id bigint,
#     primary key (id)
# );
# create table countries
# (
#     id     bigint           not null,
#     name   varchar(255),
#     square double precision not null,
#     primary key (id)
# );
# create table hibernate_sequence
# (
#     next_val bigint
# );
# insert into hibernate_sequence
# values (1);
# create table orders
# (
#     id              bigint  not null,
#     car_id          integer not null,
#     car_type        varchar(255),
#     end_date_time   datetime(6),
#     prise           integer not null,
#     start_date_time datetime(6),
#     user_id         integer not null,
#     tariff_id       bigint,
#     primary key (id)
# );
# create table tariffs
# (
#     id            bigint  not null,
#     car_type      varchar(255),
#     description   varchar(255),
#     name          varchar(255),
#     rate_per_hour integer not null,
#     primary key (id)
# );
# alter table areas
#     add constraint city_id foreign key (city_id) references cities (id);
# alter table cities
#     add constraint country_id foreign key (country_id) references countries (id);
# alter table orders
#     add constraint tariff_id foreign key (tariff_id) references tariffs (id)