package br.com.branetlogistica.msclient.modules.client.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationInfo;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationResponse;
import br.com.branetlogistica.msclient.modules.client.model.ClientApplication;

@Component
public class ClientApplicationConverter {

	@Autowired
	protected ModelMapper mapper;

	public ClientApplication toEntity(ClientApplicationRequest object) {
		return mapper.map(object, ClientApplication.class);
	}

	public ClientApplicationInfo toInfo(ClientApplication clientModule) {
		ClientApplicationInfo info = new ClientApplicationInfo();
		info.setMaxConnections(clientModule.getMaxConnections());
		if (clientModule.getApplication().getDataBase() != null) {
			String dataBaseName = "DB_"+clientModule.getClient().getUUID();		
			dataBaseName = dataBaseName.toLowerCase().replace("-","_");
			info.setDataBaseName(dataBaseName);
		}
		return info;
	}

	public ClientApplicationResponse toResponse(ClientApplication object) {
		return mapper.map(object, ClientApplicationResponse.class);
	}

}
