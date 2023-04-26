package br.com.branetlogistica.msclient.modules.application.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.branetlogistica.msclient.core.exceptions.ApiException;
import br.com.branetlogistica.msclient.core.exceptions.NotFoundException;
import br.com.branetlogistica.msclient.modules.application.model.Application;
import br.com.branetlogistica.msclient.modules.application.model.ApplicationView;
import br.com.branetlogistica.msclient.modules.application.repository.ApplicationRepository;
import br.com.branetlogistica.msclient.modules.application.repository.ApplicationRepositoryView;
import br.com.branetlogistica.msclient.modules.module.service.ModuleService;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository repository;
	
	@Autowired
	private ApplicationRepositoryView viewRepository;
	
	@Autowired
	private ModuleService moduleService;
	

	public Application findById(Long id) {
		Optional<Application> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
	}
		

	public Application insert(Application entity) {				
		validInsert(entity);		
		entity.setModule(this.moduleService.findById(entity.getModule().getId()));
		return repository.save(entity);
				
	}
	
	public Application update(Application newEntity) {
		Application oldEntity = this.findById(newEntity.getId());		
		newEntity.setDisabled(oldEntity.isDisabled());		
		validUpdate(oldEntity,newEntity);			
		return repository.save(newEntity);			
	}
	
	public void disabled(Long id) {
		Application entity = this.findById(id);		
		entity.setDisabled(true);			
		repository.save(entity);			
	}
	
	public Page<ApplicationView> page(Predicate predicate, Pageable pageable){
		return viewRepository.findAll(predicate, pageable);
	}
	
	
						
	private void validName(Application entity, Map<String,String> errors) {
		if(repository.existsByName(entity.getName()))
			errors.put("name", "Já existe um cliente com esse nome: "+entity.getName());
		
	}
	
	private void validKey(Application entity, Map<String,String> errors) {
		if(repository.existsByKey(entity.getKey()))
			errors.put("name", "Já existe um cliente com essa chave: "+entity.getKey());
		
	}
	
	private void validModule(Application entity, Map<String,String> errors) {				
		if(!moduleService.exist(entity.getModule()) )
			errors.put("name", "Não existe o modulo com o id informado "+entity.getModule().getId());	
	}
	
	
	
	private void validUpdate(Application oldEntity, Application newEntity) {
		Map<String, String> errors = new HashMap<>();
		this.validModule(newEntity, errors);
		if(!oldEntity.getName().equals(newEntity.getName()))
			this.validName(newEntity, errors);		
		
		if(!oldEntity.getKey().equals(newEntity.getKey()))
			this.validKey(newEntity, errors);	
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação do cliente", errors);
	}
	
	private void validInsert(Application entity) {
		Map<String, String> errors = new HashMap<>();
		this.validName(entity, errors);		
		this.validModule(entity, errors);
		this.validKey(entity, errors);	
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação do cliente", errors);
	}
	

	
}