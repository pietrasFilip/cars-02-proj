use cars_02;

create table if not exists cars_bodies (
    id integer primary key auto_increment,
    color varchar(50) not null,
    type varchar(50) not null
);

create table if not exists components (
    id integer primary key auto_increment,
    name varchar(50) not null
);

create table if not exists cars_bodies_with_components (
    car_body_id integer not null,
    component_id integer not null,
    primary key (car_body_id, component_id),
    foreign key (car_body_id) references cars_bodies(id) on delete cascade on update cascade,
    foreign key (component_id) references components(id) on delete cascade on update cascade
);

create table if not exists engines (
    id integer primary key auto_increment,
    type varchar(50) not null,
    power double
);

create table if not exists wheels (
    id integer primary key auto_increment,
    type varchar(50) not null,
    model varchar(50) not null,
    size integer
);

create table if not exists cars (
    id integer primary key auto_increment,
    model varchar(50) not null,
    price decimal,
    mileage integer,
    engine_id integer not null,
    car_body_id integer not null,
    wheel_id integer not null,
    foreign key (engine_id) references engines(id) on delete cascade on update cascade,
    foreign key (car_body_id) references cars_bodies(id) on delete cascade on update cascade,
    foreign key (wheel_id) references wheels(id) on delete cascade on update cascade
);

create table if not exists users (
                                     id integer primary key auto_increment,
                                     username varchar(50) not null,
                                     email varchar(50) not null,
                                     password varchar(512) not null,
                                     role varchar(50) not null,
                                     is_active bool default false
);