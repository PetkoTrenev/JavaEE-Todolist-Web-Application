create database test;

use test;

CREATE TABLE user (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(32) DEFAULT NULL,
  email varchar(32) NOT NULL,
  password varchar(32) DEFAULT NULL
);

ALTER TABLE user ADD CONSTRAINT unique_email UNIQUE (email);

CREATE TABLE todo (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  name varchar(512) DEFAULT NULL,
  category varchar(255),
  priority tinyint NOT NULL
);

alter table todo add constraint user_fk foreign key (userId) references user(id) ON DELETE CASCADE;