package br.com.branetlogistica.msclient.modules.module.service;


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
import br.com.branetlogistica.msclient.modules.module.model.Module;
import br.com.branetlogistica.msclient.modules.module.model.ModuleView;
import br.com.branetlogistica.msclient.modules.module.repository.ModuleRepository;
import br.com.branetlogistica.msclient.modules.module.repository.ModuleRepositoryView;


@Service
public class ModuleService {

	@Autowired
	private ModuleRepository repository;
	
	@Autowired
	private ModuleRepositoryView viewRepository;
	
	
	public  Module findById(Long id) {
		Optional< Module> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
	}
		

	public  Module insert( Module entity) {				
		validInsert(entity);				
		return repository.save(entity);
				
	}
	
	
	public Module update(Module newEntity) {
		Module oldEntity = this.findById(newEntity.getId());		
		newEntity.setDisabled(oldEntity.isDisabled());				
		validUpdate(oldEntity,newEntity);			
		return repository.save(newEntity);			
	}
	
	
	
	public void disabled(Long id) {
		 Module entity = this.findById(id);		
		entity.setDisabled(true);			
		repository.save(entity);			
	}
	
	public Page<ModuleView> page(Predicate predicate, Pageable pageable){
		return viewRepository.findAll(predicate, pageable);
	}
	
	
						
	private void validName( Module entity, Map<String,String> errors) {
		if(repository.existsByName(entity.getName()))
			errors.put("name", "Já existe um modulo com esse nome: "+entity.getName());
		
	}
	
	private void validUpdate( Module oldEntity,  Module newEntity) {
		Map<String, String> errors = new HashMap<>();
		if(!oldEntity.getName().equals(newEntity.getName()))
			this.validName(newEntity, errors);		
				
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação do cliente", errors);
	}
	
	private void validInsert( Module entity) {
		Map<String, String> errors = new HashMap<>();
		this.validName(entity, errors);			
		
		if(!errors.isEmpty())
			throw new ApiException(HttpStatus.BAD_REQUEST,"Erro validação do cliente", errors);
	}
	

	public boolean exist( Module entidade) {
		return repository.existsById(entidade.getId());
	}
	
	
	
}

