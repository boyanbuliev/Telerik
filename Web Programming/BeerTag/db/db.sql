use beers;

drop table if exists users_beers,beers,styles,users;

create table styles
(
    style_id int auto_increment primary key,
    name     varchar(50) not null
);

create table users
(
    user_id    int auto_increment primary key,
    username   varchar(50) not null,
    password   varchar(50) not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    email      varchar(50) not null,
    is_admin   bool default false
);

create table beers
(
    beer_id  int auto_increment primary key,
    name     varchar(50)    not null,
    abv      decimal(10, 1) not null,
    style_id int            not null,
    constraint beers_styles_style_id_fk foreign key (style_id) references styles (style_id),
    created_by  int            not null,
    constraint beers_users_user_id_fk foreign key (created_by) references users (user_id)
);

create table users_beers
(
    user_id int     not null,
    beer_id int     not null,
    drunk   boolean default false,
    constraint users_beers_beers_beer_id_fk foreign key (beer_id) references beers (beer_id),
    constraint users_beers_beers_user_id_fk foreign key (user_id) references users (user_id)
);

insert into styles (style_id, name)
values (1, 'Special Ale');

insert into styles (style_id, name)
values (2, 'English Porter');

insert into styles (style_id, name)
values (3, 'Indian Pale Ale');



insert into users(user_id, username, password, first_name, last_name, email, is_admin)
values (1, 'pesho', '1234', 'Pesho', 'Peshov', 'pesho@peshomail.com', true);

insert into users(user_id, username, password, first_name, last_name, email)
values (2, 'gosho', '0000', 'Gosho', 'Goshov', 'gosho@goshomail.com');

insert into users(user_id, username, password, first_name, last_name, email)
values (3, 'ivan', '4321', 'Ivan', 'Ivanov', 'ivan@ivanmail.com');



insert into beers(beer_id, name, abv, style_id, created_by)
values (1, 'Glarus Enslish Ale', 4.6, 1, 1);

insert into beers(beer_id, name, abv, style_id, created_by)
values (2, 'Rhombus Porter', 5.0, 2, 2);

insert into beers(beer_id, name, abv, style_id, created_by)
values (3, 'Opasen Char', 6.6, 3, 3);

insert into beers(beer_id, name, abv, style_id, created_by)
values (4, 'Ailyak', 6.6, 1, 1);

insert into beers(beer_id, name, abv, style_id, created_by)
values (5, 'Basi Kefa', 6.7, 2, 2);

insert into beers(beer_id, name, abv, style_id, created_by)
values (6, 'Vitoshko lale', 5.5, 3, 3);

insert into beers(beer_id, name, abv, style_id, created_by)
values (7, 'Divo Pivo', 4.5, 1, 1);

insert into beers(beer_id, name, abv, style_id, created_by)
values (8, 'Bloody Muddy', 5.0, 2, 2);

insert into beers(beer_id, name, abv, style_id, created_by)
values (9, 'Black Head', 5.0, 3, 3);

insert into beers(beer_id, name, abv, style_id, created_by)
values (10, 'Pilsner Urquell', 4.4, 1, 1);

insert into users_beers (user_id, beer_id, drunk)
values (1, 1, false);

insert into users_beers (user_id, beer_id, drunk)
values (1, 2, false);

insert into users_beers (user_id, beer_id, drunk)
values (2, 1, true);

insert into users_beers (user_id, beer_id, drunk)
values (2, 2, false);

insert into users_beers (user_id, beer_id, drunk)
values (3, 1, false);

insert into users_beers (user_id, beer_id, drunk)
values (3, 2, true);