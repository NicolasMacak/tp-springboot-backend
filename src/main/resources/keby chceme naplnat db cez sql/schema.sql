drop table if exists Clients;

create table Clients (
id int AUTO_INCREMENT primary key,
first_name varchar(250) not null,
last_name varchar(250) not null
);