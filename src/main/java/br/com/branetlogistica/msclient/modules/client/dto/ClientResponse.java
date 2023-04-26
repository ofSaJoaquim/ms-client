package br.com.branetlogistica.msclient.modules.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
	
	private Long id;
	private String name;
	private String UUID;
}
