package br.com.branetlogistica.msclient.modules.client.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientRequest {
	
	@NotNull	
	@NotEmpty
	@Size(min = 5, max = 100)
	private String name;
	
		
}
