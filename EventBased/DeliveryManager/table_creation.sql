Hibernate: create table completed_delivery (id integer not null, x integer not null, y integer not null, time datetime(6), drone_id integer, item_id integer, primary key (id)) engine=InnoDB
Hibernate: create table delivery (id integer not null, x integer not null, y integer not null, drone_id integer, item_id integer, primary key (id)) engine=InnoDB
Hibernate: create table drone (id integer not null, battery double precision not null, x integer not null, y integer not null, weight_limit double precision not null, primary key (id)) engine=InnoDB
Hibernate: create table hibernate_sequence (next_val bigint) engine=InnoDB
Hibernate: insert into hibernate_sequence values ( 1 )
Hibernate: create table item (id integer not null, description varchar(255), weight double precision not null, primary key (id)) engine=InnoDB
Hibernate: alter table completed_delivery add constraint FK4ch3x40md02vbo1y7j1tamj9n foreign key (drone_id) references drone (id)
    Hibernate: alter table completed_delivery add constraint FKqaei44lsfhttj3fv3cc17x46s foreign key (item_id) references item (id)
    Hibernate: alter table delivery add constraint FK4y03p3qe1k40wjerkrecqscl4 foreign key (drone_id) references drone (id)
    Hibernate: alter table delivery add constraint FKo9grlj8a03tc7ox0v1k3u9nlg foreign key (item_id) references item (id)