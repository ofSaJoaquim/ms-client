create view module_view as 
SELECT id, name, "key" , disabled
FROM base_client.module;

create view client_view as
select id,name,uuid from base_client.client;   


