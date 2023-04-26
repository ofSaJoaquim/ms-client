package br.com.branetlogistica.msclient.modules.client.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.branetlogistica.msclient.core.exceptions.ApiException;
import br.com.branetlogistica.msclient.core.exceptions.NotFoundException;
import br.com.branetlogistica.msclient.modules.application.model.Application;
import br.com.branetlogistica.msclient.modules.application.service.ApplicationService;
import br.com.branetlogistica.msclient.modules.client.converter.ClientApplicationConverter;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationInfo;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationResponse;
import br.com.branetlogistica.msclient.modules.client.model.Client;
import br.com.branetlogistica.msclient.modules.client.model.ClientApplication;
import br.com.branetlogistica.msclient.modules.client.model.ClientApplicationView;
import br.com.branetlogistica.msclient.modules.client.repository.ClientApplicationRepository;
import br.com.branetlogistica.msclient.modules.client.repository.ClientModuleRepository;
import br.com.branetlogistica.msclient.modules.database.service.DataBaseService;

@Service
public class ClientApplicationService {
	
	@Autowired
	private ClientApplicationRepository repository;	
	
	@Autowired
	private ApplicationService applicationService;	
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientApplicationConverter converter;
	
	@Autowired
	private DataBaseService dataBaseService;
	
	@Autowired
	private ClientModuleRepository clientModuleRepository;
	
	public ClientApplicationResponse insert(Long clientId, ClientApplicationRequest request) {		
		ClientApplication entity = create(clientId, request.getApplicationId());	
		entity.setMaxConnections(request.getMaxConnections());
		validInsert(entity);
		
			
		if(repository.existsByClientIdAndApplicationIdAndDisabled(entity.getClient().getId(), entity.getApplication().getId(), true)) {
			entity = this.findByClientIdAndApplicationId(entity.getClient().getId(), entity.getApplication().getId());
			entity.setDisabled(false);			
		}
				
		entity = repository.save(entity);	
		
		dataBaseService.initDataBase(entity);
		
		return converter.toResponse(entity);
			
	}	
	
	
	public ClientApplicationResponse disable(Long clientId, Long applicationId) {			
		ClientApplication entity = create(clientId, applicationId);		
		validDisabled(entity);
		
		if(repository.existsByClientIdAndApplicationIdAndDisabled(entity.getClient().getId(), entity.getApplication().getId(), true)) {
			entity = this.findByClientIdAndApplicationId(entity.getClient().getId(), entity.getApplication().getId());
			entity.setDisabled(false);
		}
				
		entity = repository.save(entity);		
		return converter.toResponse(entity);
			
	}
	
	
	public Page<ClientApplicationView> page(Long clientId,Pageable pageable){		
		return repository.findAllByClientIdAndDisabled(clientId, false, pageable);
	}
	
	public ClientApplication findByClientIdAndApplicationId(Long clientId, Long applicationId) {
		Optional<ClientApplication> obj = repository.findByClientIdAndApplicationId(clientId, applicationId);
		return obj.orElseThrow(() -> new NotFoundException("Aplicação cliente não encontrado"));
	}	
	
	public ClientApplicationResponse findByClientIdAndApplicationIdResponse(Long clientId, Long applicationId) {
		return converter.toResponse(this.findByClientIdAndApplicationId(clientId, applicationId));
	}
	
	public ClientApplication findByClientUUIDAndApplicationKey(String UUID, String applicationKey) {
		Optional<ClientApplication> obj = repository.findByClientUUIDAndApplicationKey(UUID, applicationKey);
		return obj.orElseThrow(() -> new NotFoundException("Aplicação cliente não encontrado"));
	}
	
	public ClientApplicationResponse  findByClientUUIDAndApplicationNameResponse(String UUID, String applicationKey) {
		return converter.toResponse(this.findByClientUUIDAndApplicationKey(UUID, applicationKey));
	}
	
	public ClientApplicationInfo  findByClientUUIDAInfo(String UUID, String applicationKey) {
		return converter.toInfo(this.findByClientUUIDAndApplicationKey(UUID, applicationKey));
	}
	
	
	private ClientApplication create(Long clientId, Long applicationId) {
		Client client = clientService.findById(clientId);
		Application application = applicationService.findById(applicationId);
		
		ClientApplication entity = new ClientApplication();
		entity.setApplication(application);
		entity.setClient(client);	
		
		
		
		return entity;
	}
	
	
	
	private void validInsert(ClientApplication entity) {
		Map<String, String> errors = new HashMap<>();
		
		if(!clientModuleRepository.existsByClientIdAndModuleIdAndDisabled(entity.getClient().getId(), entity.getApplication().getModule().getId(), false))
			errors.put("Modulo", "Modulo não adicionado a esse cliente");
		
		if(repository.existsByClientIdAndApplicationIdAndDisabled(entity.getClient().getId(), entity.getApplication().getId(), false)) {
			errors.put("idApplication", "Aplicação já adicionado a esse cliente");
		}
		
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação", errors);
	}
	
	private void validDisabled(ClientApplication entity) {
		Map<String, String> errors = new HashMap<>();
		if(repository.existsByClientIdAndApplicationIdAndDisabled(entity.getClient().getId(), entity.getApplication().getId(), false)) {
			errors.put("idApplication", "Aplicação já desativado ou não cadastrado nesse cliente");
		}			
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação", errors);
	}

}
