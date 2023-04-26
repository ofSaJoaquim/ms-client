package br.com.branetlogistica.msclient.modules.module.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModuleRequest {

	@NotNull	
	@NotEmpty
	@Size(min = 5, max = 100)
	private String name;
	
	@NotNull	
	@NotEmpty
	@Size(min = 5, max = 100)
	private String key;
	
}
