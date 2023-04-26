package br.com.branetlogistica.msclient.modules.application.dto;

import br.com.branetlogistica.msclient.modules.database.dto.DataBaseResponse;
import br.com.branetlogistica.msclient.modules.module.dto.ModuleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {

	private Long id;
	private String name;
	private DataBaseResponse dataBase;
	private ModuleResponse module;
}
