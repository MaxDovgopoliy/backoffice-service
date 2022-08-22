create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );

# CREATE TABLE IF NOT EXISTS 'tariffs'(
#     'id' bigint NOT NULL AUTO_INCREMENT,
#     'name' varchar(255) NOT NULL
# );
create table orders (
    id bigint not null,
    car_id integer not null,
    car_type varchar(255),
    end_date_time datetime(6),
    prise integer not null,
    start_date_time datetime(6),
    user_id integer not null,
    tariff_id bigint,
    primary key (id));


    create table tariffs (
        id bigint not null,
        car_type varchar(255),
        description varchar(2048),
        name varchar(255),
        rate_per_hour integer not null,
        primary key (id));

     alter table orders add constraint tariff_id foreign key (tariff_id) references tariffs (id);

    create table areas (
        id bigint not null,
        city varchar(255),
        country varchar(255),
        primary key (id));

    create table coordinates (
        id bigint not null,
        latitude double precision not null,
        longitude double precision not null,
        area_id bigint, primary key (id));

alter table coordinates add constraint areas_id foreign key (area_id) references areas (id)