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
    is_admin boolean     not null
);

create table beers
(
    beer_id    int auto_increment primary key,
    name       varchar(50)    not null,
    abv        decimal(10, 1) not null,
    style_id   int            not null,
    created_by int            not null,
    constraint beers_styles_style_id_fk
        foreign key (style_id) references styles (style_id),
    constraint beers_users_user_id_fk
        foreign key (created_by) references users (user_id)
);

create table users_beers
(
    user_id int        not null,
    beer_id int        not null,
    drunk   boolean not null default 0,
    constraint users_beers_beers_beer_id_fk
        foreign key (beer_id) references beers (beer_id),
    constraint users_beers_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

INSERT INTO beers.styles (style_id, name)
VALUES (1, 'Pilsner');
INSERT INTO beers.styles (style_id, name)
VALUES (2, 'Pale ale');
INSERT INTO beers.styles (style_id, name)
VALUES (3, 'Red Ale');
INSERT INTO beers.styles (style_id, name)
VALUES (4, 'Porter');
INSERT INTO beers.styles (style_id, name)
VALUES (5, 'Stout');
INSERT INTO beers.styles (style_id, name)
VALUES (6, 'Indian pale ale');
INSERT INTO beers.styles (style_id, name)
VALUES (7, 'Weissbier');
INSERT INTO beers.styles (style_id, name)
VALUES (8, 'Special ale');

INSERT INTO beers.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (1, 'todor', 'pass1', 'Todor', 'Andonov', 'todor@company.com', true);
INSERT INTO beers.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (2, 'vladi', 'pass2', 'Vladi', 'Venkov', 'vladi@company.com', false);
INSERT INTO beers.users (user_id, username, password, first_name, last_name, email, is_admin)
VALUES (3, 'pesho', 'pass3', 'Petar', 'Raykov', 'pesho@company.com', false);

INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (1, 'Glarus English Ale', 4.6, 8, 1);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (2, 'Rhombus Porter', 5.0, 4, 1);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (3, 'Opasen char', 6.6, 6, 1);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (4, 'Ailyak', 6.6, 6, 1);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (5, 'Basi Kefa', 6.7, 6, 2);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (6, 'Vitoshko lale', 5.5, 2, 2);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (7, 'Divo Pivo', 4.5, 2, 2);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (8, 'Bloody Muddy', 5.0, 3, 2);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (9, 'Black Head', 5.0, 5, 3);
INSERT INTO beers.beers (beer_id, name, abv, style_id, created_by)
VALUES (10, 'Pilsner Urquell', 4.4, 1, 3);

INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (1, 1, 0);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (1, 2, 0);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (1, 3, 1);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (1, 5, 1);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (2, 3, 0);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (2, 4, 0);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (3, 3, 1);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (3, 5, 1);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (3, 7, 1);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (3, 8, 1);
INSERT INTO beers.users_beers (user_id, beer_id, drunk)
VALUES (3, 10, 1);