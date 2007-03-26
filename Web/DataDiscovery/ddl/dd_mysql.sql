
drop database if exists dbs_history;
create database dbs_history;
use dbs_history;

Drop table if exists DD_HISTORY;
Drop table if exists DD_COMMAND;
Drop table if exists DD_INSTANCE;
Drop table if exists DD_USER;
CREATE TABLE DD_USER (
	id BIGINT UNSIGNED NOT NULL auto_increment, 
	userid VARCHAR(60) NOT NULL, 
	PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE DD_INSTANCE (
	id BIGINT UNSIGNED NOT NULL auto_increment, 
	dbsinstance VARCHAR(60) NOT NULL, 
	PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE DD_COMMAND (
	id BIGINT UNSIGNED NOT NULL auto_increment, 
	command VARCHAR(1000), 
	alias VARCHAR(1000),
	PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE DD_HISTORY (
	id BIGINT UNSIGNED NOT NULL auto_increment, 
	userid BIGINT UNSIGNED NOT NULL, 
	cmdid BIGINT UNSIGNED NOT NULL, 
	dbsid BIGINT UNSIGNED NOT NULL, 
        history_date DATE NOT NULL,
        history_time VARCHAR(100),
	PRIMARY KEY (id), 
        FOREIGN KEY(userid) REFERENCES `DD_USER` (id), 
        FOREIGN KEY(dbsid) REFERENCES `DD_INSTANCE` (id), 
        FOREIGN KEY(cmdid) REFERENCES `DD_COMMAND` (id)
) ENGINE = InnoDB;

