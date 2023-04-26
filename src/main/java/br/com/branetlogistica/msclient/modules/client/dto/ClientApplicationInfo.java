package br.com.branetlogistica.msclient.modules.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientApplicationInfo {

	private String dataBaseName;
	private Integer maxConnections;
	
}
