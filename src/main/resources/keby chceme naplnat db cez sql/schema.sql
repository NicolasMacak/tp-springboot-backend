drop table if exists Clients;
drop table if exists Contacts;

create table Contacts (
id bigint AUTO_INCREMENT primary key,
first_name varchar(250) not null,
last_name varchar(250) not null,
phone_number varchar(250) not null
);

create table Clients (
id bigint AUTO_INCREMENT primary key,
FOREIGN KEY (contact_id) REFERENCES Contacts(id)
);

