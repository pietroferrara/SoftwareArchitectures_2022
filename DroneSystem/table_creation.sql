create table delivery (
    id integer not null,
    drone_id integer,
    item_id integer,
    primary key (id)
)

create table drone (
    id integer not null,
    battery double precision not null,
    x integer not null,
    y integer not null,
    weight_limit double precision not null,
    primary key (id)
)

create table item (
    id integer not null,
    description varchar(255),
    weight double precision not null,
    primary key (id)
)
