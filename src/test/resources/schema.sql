-- Suppression des tables
DROP TABLE IF EXISTS Users;


-- Création de la table users
CREATE TABLE Users (
	id				INT				PRIMARY KEY,
    name            varchar(40)     not null,
    lastname        varchar(40)     not null,
    email           varchar(50)     not null,
    password        varchar(150)    not null,
    token			varchar(255)	null,
    expiration_time	datetime		null,
    role            char(4)         not null default 'cust',

    check( role in('cust', 'admi', 'staf'))
);
