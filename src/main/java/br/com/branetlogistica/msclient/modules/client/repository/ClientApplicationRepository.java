package br.com.branetlogistica.msclient.modules.client.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.branetlogistica.msclient.modules.client.model.ClientApplication;
import br.com.branetlogistica.msclient.modules.client.model.ClientApplicationView;

public interface ClientApplicationRepository extends JpaRepository<ClientApplication, Long> {	
	
	public Optional<ClientApplication> findByClientIdAndApplicationId(Long clientId, Long applicationId);

	public Page<ClientApplicationView> findAllByClientIdAndDisabled(Long clientId, Boolean disabled,Pageable pageable );
	
	public Boolean existsByClientIdAndApplicationIdAndDisabled(Long clientId, Long applicationId, Boolean disabled);
	
	@Query(value =" SELECT ca FROM ClientApplication ca WHERE ca.client.UUID = :uuid AND  ca.application.key = :key ")
	public Optional<ClientApplication> findByClientUUIDAndApplicationKey(@Param("uuid")String clientUUID,@Param("key")String applicationKey);
	
	
	
}
