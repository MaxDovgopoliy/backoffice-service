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
    end_date datetime(6),
    prise integer not null,
    start_date datetime(6),
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

     alter table orders add constraint FKjqfqg1m4y9bjrxovw7mw5ost5 foreign key (tariff_id) references tariffs (id);