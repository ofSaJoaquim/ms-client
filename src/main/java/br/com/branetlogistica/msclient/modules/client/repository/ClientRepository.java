package br.com.branetlogistica.msclient.modules.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.msclient.modules.client.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	
	public boolean existsByName(String name);
	public boolean existsByUUID(String name);
	
	//public boolean existsByNameAndNotId(String name,Long id);
	//public boolean existsByUUIDAndNotId(String name, Long id);
	
}
