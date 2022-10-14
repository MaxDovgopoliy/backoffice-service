ALTER TABLE orders RENAME COLUMN prise TO price;
ALTER TABLE orders Modify column price DECIMAL;
ALTER TABLE areas DROP COLUMN address;
create table addresses
(
    id         bigint           not null,
    street     varchar(255),
    number     integer not null,
    area_id bigint,
    primary key (id)
);

alter table addresses
    add constraint area_id foreign key (area_id) references areas (id);

