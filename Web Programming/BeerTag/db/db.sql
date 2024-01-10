use beers;

drop table if exists beers,styles,users;

create table styles
(
    style_id int auto_increment primary key,
    name     varchar(50) not null
);

insert into styles (style_id, name)
values (1, 'Special Ale');

insert into styles (style_id, name)
values (2, 'English Porter');

insert into styles (style_id, name)
values (3, 'Indian Pale Ale');

create table users
(
    user_id    int auto_increment primary key,
    username   varchar(50) not null,
    password   varchar(50) not null,
    first_name varchar(50),
    last_name  varchar(50),
    email      varchar(50),
    is_admin   bool default false
);

insert into users(user_id, username, password, is_admin)
values (1, 'pesho', '1234', true);

insert into users(user_id, username, password)
values (2, 'gosho', '0000');

insert into users(user_id, username, password)
values (3, 'ivan', '4321');


create table beers
(
    beer_id  int auto_increment primary key,
    name     varchar(50)    not null,
    abv      decimal(10, 1) not null,
    style_id int            not null,
    constraint fk_style foreign key (style_id) references styles (style_id),
    user_id  int            not null,
    constraint fk_user foreign key (user_id) references users (user_id)
);

insert into beers(beer_id, name, abv, style_id, user_id)
values (1, 'Glarus Enslish Ale', 4.6, 1, 1);

insert into beers(beer_id, name, abv, style_id, user_id)
values (2, 'Rhombus Porter', 5.0, 2, 2);

insert into beers(beer_id, name, abv, style_id, user_id)
values (3, 'Opasen Char', 6.6, 3, 3);

insert into beers(beer_id, name, abv, style_id, user_id)
values (4, 'Ailyak', 6.6, 1, 1);

insert into beers(beer_id, name, abv, style_id, user_id)
values (5, 'Basi Kefa', 6.7, 2, 2);

insert into beers(beer_id, name, abv, style_id, user_id)
values (6, 'Vitoshko lale', 5.5, 3, 3);

insert into beers(beer_id, name, abv, style_id, user_id)
values (7, 'Divo Pivo', 4.5, 1, 1);

insert into beers(beer_id, name, abv, style_id, user_id)
values (8, 'Bloody Muddy', 5.0, 2, 2);

insert into beers(beer_id, name, abv, style_id, user_id)
values (9, 'Black Head', 5.0, 3, 3);

insert into beers(beer_id, name, abv, style_id, user_id)
values (10, 'Pilsner Urquell', 4.4, 1, 1);