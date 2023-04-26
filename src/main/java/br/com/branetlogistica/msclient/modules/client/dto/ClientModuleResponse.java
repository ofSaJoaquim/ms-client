package br.com.branetlogistica.msclient.modules.client.dto;

import br.com.branetlogistica.msclient.modules.module.dto.ModuleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientModuleResponse {

	private ClientResponse client;
	private ModuleResponse module;
	
	
}
