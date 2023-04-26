package br.com.branetlogistica.msclient.modules.client.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientModuleRequest {


	@NotNull
	@Min(value = 1l)
	private Long moduleId;
	
		
}
