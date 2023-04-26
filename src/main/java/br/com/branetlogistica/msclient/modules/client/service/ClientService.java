package br.com.branetlogistica.msclient.modules.client.service;



import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.branetlogistica.msclient.core.exceptions.ApiException;
import br.com.branetlogistica.msclient.core.exceptions.NotFoundException;
import br.com.branetlogistica.msclient.modules.client.converter.ClienteConverter;
import br.com.branetlogistica.msclient.modules.client.dto.ClientRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientResponse;
import br.com.branetlogistica.msclient.modules.client.model.Client;
import br.com.branetlogistica.msclient.modules.client.model.ClientView;
import br.com.branetlogistica.msclient.modules.client.repository.ClientRepository;
import br.com.branetlogistica.msclient.modules.client.repository.ClientRepositoryView;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ClientRepositoryView viewRepository;	
		
	@Autowired
	private ClienteConverter converter;
	
	
	
	public ClientResponse insert(ClientRequest request) {		
		UUID uuid = UUID.randomUUID();
		
		Client entity = converter.toEntity(request);
		entity.setUUID(uuid.toString());
		
		valid(entity);
		
		entity = repository.save(entity);
		return converter.toResponse(entity);		
	}
	
	public ClientResponse update(Long id,ClientRequest request) {			
		Client old = this.findById(id);
		
		Client entity = converter.toEntity(request);
		entity.setId(old.getId());
		entity.setDisabled(old.isDisabled());
		entity.setUUID(old.getUUID());
		
		//validUpdate(entity);
		
		entity = repository.save(entity);
		return converter.toResponse(entity);		
	}
	
	
	public ClientResponse disable(Long id) {		
		Client entity = this.findById(id);
		entity.setDisabled(true);
		entity = repository.save(entity);
		return converter.toResponse(entity);		
	}
	
	
			
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
	}
	
	public ClientResponse findByIdResponse(Long id) {
		return converter.toResponse(this.findById(id));
	}
	
	
	public Page<ClientView> page(Predicate predicate, Pageable pageable){		
		return viewRepository.findAll(predicate, pageable);
		
	}
		
	private void valid(Client cliente) {
		Map<String, String> errors = new HashMap<>();
		if(repository.existsByName(cliente.getName()))
			errors.put("name", "Já existe um cliente com esse nome: "+cliente.getName());
		
		if(repository.existsByName(cliente.getUUID()))
			errors.put("name", "Já existe um cliente com esse uuid: "+cliente.getUUID());
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação do cliente", errors);
	}
	
	/*private void validUpdate(Client cliente) {
		Map<String, String> errors = new HashMap<>();
		if(repository.existsByNameAndNotId(cliente.getName(),cliente.getId()))
			errors.put("name", "Já existe um cliente com esse nome: "+cliente.getName());
		
		if(repository.existsByNameAndNotId(cliente.getUUID(),cliente.getId()))
			errors.put("name", "Já existe um cliente com esse uuid: "+cliente.getUUID());
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação do cliente", errors);
	}*/
	
	
}
