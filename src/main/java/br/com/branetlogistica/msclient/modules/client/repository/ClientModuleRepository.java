package br.com.branetlogistica.msclient.modules.client.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.msclient.modules.client.model.ClientModule;
import br.com.branetlogistica.msclient.modules.client.model.ClientModuleView;

public interface ClientModuleRepository extends JpaRepository<ClientModule, Long> {	
	
	public Optional<ClientModule> findByClientIdAndModuleId(Long clientId, Long moduleId);

	public Page<ClientModuleView> findAllByClientIdAndDisabled(Long clientId, Boolean disabled,Pageable pageable );
	
	public Boolean existsByClientIdAndModuleIdAndDisabled(Long clientId, Long moduleId, Boolean disabled);
	
	public Optional<ClientModule> findByClientUUIDAndModuleName(String clientUUID, String moduleName);
	
}
