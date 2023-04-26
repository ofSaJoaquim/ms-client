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
import br.com.branetlogistica.msclient.modules.client.converter.ClientModuleConverter;
import br.com.branetlogistica.msclient.modules.client.dto.ClientModuleRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientModuleResponse;
import br.com.branetlogistica.msclient.modules.client.model.Client;
import br.com.branetlogistica.msclient.modules.client.model.ClientModule;
import br.com.branetlogistica.msclient.modules.client.model.ClientModuleView;
import br.com.branetlogistica.msclient.modules.client.repository.ClientModuleRepository;
import br.com.branetlogistica.msclient.modules.module.service.ModuleService;
import br.com.branetlogistica.msclient.modules.module.model.Module;

@Service
public class ClientModuleService {
	
	@Autowired
	private ClientModuleRepository repository;	
	
	@Autowired
	private ModuleService moduleService;	
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientModuleConverter converter;
	
	
	
	public ClientModuleResponse insert(Long clientId, ClientModuleRequest request) {		
		ClientModule entity = create(clientId, request.getModuleId());			
		validInsert(entity);
	
		
		if(repository.existsByClientIdAndModuleIdAndDisabled(entity.getClient().getId(), entity.getModule().getId(), true)) {
			entity = this.findByClientIdAndModuleId(entity.getClient().getId(), entity.getModule().getId());
			entity.setDisabled(false);			
		}
				
		entity = repository.save(entity);	
		
	
		
		return converter.toResponse(entity);
			
	}	
	
	
	public ClientModuleResponse disable(Long clientId, Long moduleId) {			
		ClientModule entity = create(clientId, moduleId);		
		validDisabled(entity);
		
		if(repository.existsByClientIdAndModuleIdAndDisabled(entity.getClient().getId(), entity.getModule().getId(), true)) {
			entity = this.findByClientIdAndModuleId(entity.getClient().getId(), entity.getModule().getId());
			entity.setDisabled(false);
		}
				
		entity = repository.save(entity);		
		return converter.toResponse(entity);
			
	}
	
	
	public Page<ClientModuleView> page(Long clientId,Pageable pageable){		
		return repository.findAllByClientIdAndDisabled(clientId, false, pageable);
	}
	
	public ClientModule findByClientIdAndModuleId(Long clientId, Long applicationId) {
		Optional<ClientModule> obj = repository.findByClientIdAndModuleId(clientId, applicationId);
		return obj.orElseThrow(() -> new NotFoundException("Aplicação cliente não encontrado"));
	}	
	
	public ClientModuleResponse findByClientIdAndModuleIdResponse(Long clientId, Long moduleId) {
		return converter.toResponse(this.findByClientIdAndModuleId(clientId, moduleId));
	}
	
	public ClientModule findByClientUUIDAndModuleName(String UUID, String applicationName) {
		Optional<ClientModule> obj = repository.findByClientUUIDAndModuleName(UUID, applicationName);
		return obj.orElseThrow(() -> new NotFoundException("Aplicação cliente não encontrado"));
	}
	
	
	
	
	
	
	private ClientModule create(Long clientId, Long moduleId) {
		Client client = clientService.findById(clientId);
		Module module = moduleService.findById(moduleId);
		
		ClientModule entity = new ClientModule();
		entity.setModule(module);
		entity.setClient(client);	
		
		
		
		return entity;
	}
	
	
	
	private void validInsert(ClientModule entity) {
		Map<String, String> errors = new HashMap<>();
		if(repository.existsByClientIdAndModuleIdAndDisabled(entity.getClient().getId(), entity.getModule().getId(), false)) {
			errors.put("idModule", "Aplicação já adicionado a esse cliente");
		}			
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação", errors);
	}
	
	private void validDisabled(ClientModule entity) {
		Map<String, String> errors = new HashMap<>();
		if(repository.existsByClientIdAndModuleIdAndDisabled(entity.getClient().getId(), entity.getModule().getId(), false)) {
			errors.put("idModule", "Aplicação já desativado ou não cadastrado nesse cliente");
		}			
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação", errors);
	}

}
