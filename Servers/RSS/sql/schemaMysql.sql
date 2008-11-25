drop database if exists RSS;
create database RSS;
use RSS;
CREATE TABLE RunSeq (
	ID 			BIGINT	UNSIGNED 		not null 	auto_increment,
	Name 			varchar(500)		unique 	not null,
	StartNumber 		BIGINT UNSIGNED,
	EndNumber 		BIGINT,
	CurrentNumber 		BIGINT,
	primary key(ID)
) ENGINE = InnoDB ;


