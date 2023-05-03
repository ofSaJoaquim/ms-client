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

create table data_base_connection (

	id SERIAL,
	url VARCHAR NOT NULL,
	password VARCHAR NOT NULL,
	login VARCHAR NOT NULL,
	driver VARCHAR NOT NULL,
	default_data_base VARCHAR NOT NULL,
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	disabled BOOLEAN NOT NULL default false,
	primary key(id)


);


create table application (

	id SERIAL,
	name VARCHAR NOT NULL UNIQUE,	
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	disabled BOOLEAN NOT NULL default false,
	data_base_connection_id INTEGER,
	primary key(id),
	foreign key(data_base_connection_id) references data_base_connection(id) 


);


create table client_application (
	id SERIAL,
	client_id INTEGER NOT NULL,
	application_id INTEGER NOT NULL,
	created_at  TIMESTAMP NOT NULL,
	updated_at TIMESTAMP,
	data_base_name VARCHAR NOT NULL,
	disabled BOOLEAN NOT NULL default false,
	max_connections INTEGER NOT NULL,
	primary key(id),
	foreign key(client_id) references client(id),
	foreign key(application_id) references application(id)
);

create view module_view as 
SELECT id, name, "key" , disabled
FROM ms_client.module;

create view client_view as
select id,name,uuid from ms_client.client;   

create view application_view as 
SELECT id, name, disabled
FROM ms_client.application;
