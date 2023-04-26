package br.com.branetlogistica.msclient.modules.client.dto;

import javax.validation.constraints.Max;
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
public class ClientApplicationRequest {


	@NotNull
	@Min(value = 1l)
	private Long applicationId;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 100)
	private Integer maxConnections;
	
}
