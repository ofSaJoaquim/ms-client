package br.com.branetlogistica.msclient.modules.application.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.modules.application.dto.ApplicationRequest;
import br.com.branetlogistica.msclient.modules.application.dto.ApplicationResponse;
import br.com.branetlogistica.msclient.modules.application.model.Application;
import br.com.branetlogistica.msclient.modules.database.converter.DataBaseConverter;
import br.com.branetlogistica.msclient.modules.module.model.Module;

@Component
public class ApplicationConverter {

	@Autowired
	protected ModelMapper mapper;
	
	@Autowired
	private DataBaseConverter dataBaseConverter;
	
	
	public Application toEntity(ApplicationRequest object) {
		return Application.builder()				
				.key(object.getKey())
				.module(new Module(object.getModuleId()))
				.name(object.getName())
				.dataBase(dataBaseConverter.toEntity(object.getDataBase()))
				.build();
				
				
		
		
	}
	
	public ApplicationResponse toResponse(Application object) {
		return mapper.map(object,ApplicationResponse.class);
	}
	
	
}
