package br.com.branetlogistica.msclient.modules.client.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.modules.client.dto.ClientRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientResponse;
import br.com.branetlogistica.msclient.modules.client.model.Client;

@Component
public class ClienteConverter {

	@Autowired
	protected ModelMapper mapper;
	
	
	public Client toEntity(ClientRequest object) {
		return mapper.map(object,Client.class);
	}
	
	public ClientResponse toResponse(Client object) {
		return mapper.map(object,ClientResponse.class);
	}
	
	
	
}
