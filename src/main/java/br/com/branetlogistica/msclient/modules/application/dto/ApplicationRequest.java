package br.com.branetlogistica.msclient.modules.application.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.branetlogistica.msclient.modules.database.dto.DataBaseRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationRequest {
	
	@NotNull	
	@NotEmpty
	@Size(min = 5, max = 100)
	private String name;
	
	@NotNull	
	@NotEmpty
	@Size(min = 5, max = 100)
	private String key;
	
	@NotNull	
	private Long moduleId;
	
	@Valid
	private DataBaseRequest dataBase;

}
