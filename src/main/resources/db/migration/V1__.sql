create table module (

	id SERIAL,
	name VARCHAR NOT NULL UNIQUE,
	key VARCHAR NOT NULL  UNIQUE,
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	disabled BOOLEAN NOT NULL default false,
	primary key(id)


);