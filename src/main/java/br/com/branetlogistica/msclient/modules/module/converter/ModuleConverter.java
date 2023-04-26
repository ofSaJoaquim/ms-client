package br.com.branetlogistica.msclient.modules.module.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.modules.module.dto.ModuleRequest;
import br.com.branetlogistica.msclient.modules.module.dto.ModuleResponse;
import br.com.branetlogistica.msclient.modules.module.model.Module;


@Component
public class ModuleConverter {

	
	@Autowired
	protected ModelMapper mapper;
	
	
	public Module toEntity(ModuleRequest object) {
		return Module.builder()
				.name(object.getName())
				.key(object.getKey())
				.build();
		
		
	}
	
	public ModuleResponse toResponse(Module object) {
		return new ModuleResponse(object.getId(), object.getName(), object.getKey());
	}
	
	
}
