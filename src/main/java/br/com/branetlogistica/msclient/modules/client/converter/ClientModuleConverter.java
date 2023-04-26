package br.com.branetlogistica.msclient.modules.client.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.modules.client.dto.ClientModuleRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientModuleResponse;
import br.com.branetlogistica.msclient.modules.client.model.ClientModule;

@Component
public class ClientModuleConverter {

	@Autowired
	protected ModelMapper mapper;

	public ClientModule toEntity(ClientModuleRequest object) {
		return mapper.map(object, ClientModule.class);
	}

	
	public ClientModuleResponse toResponse(ClientModule object) {
		return mapper.map(object, ClientModuleResponse.class);
	}

}
