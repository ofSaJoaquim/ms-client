package br.com.branetlogistica.msclient.modules.module.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponse {

	private Long id;
	private String name;
	
	private String key;
}
