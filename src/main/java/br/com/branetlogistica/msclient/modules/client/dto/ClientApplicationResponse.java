package br.com.branetlogistica.msclient.modules.client.dto;

import br.com.branetlogistica.msclient.modules.application.dto.ApplicationResponse;
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
public class ClientApplicationResponse {

	private ClientResponse client;
	private ApplicationResponse application;
	private Integer maxConnections;
	
}
