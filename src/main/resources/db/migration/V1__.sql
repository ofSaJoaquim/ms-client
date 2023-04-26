create table module (

	id SERIAL,
	name VARCHAR NOT NULL UNIQUE,
	key VARCHAR NOT NULL  UNIQUE,
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	disabled BOOLEAN NOT NULL default false,
	primary key(id)


);

create table client (

	id SERIAL,
	name VARCHAR NOT NULL UNIQUE,
	uuid VARCHAR NOT NULL  UNIQUE,
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	disabled BOOLEAN NOT NULL default false,	
	primary key(id)


);


create table client_module (
	id SERIAL,
	client_id INTEGER NOT NULL,
	module_id INTEGER NOT NULL,
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	data_base_name VARCHAR NOT NULL,
	disabled BOOLEAN NOT NULL default false,	
	primary key(id),
	foreign key(client_id) references client(id),
	foreign key(module_id) references module(id)
);
