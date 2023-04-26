package br.com.branetlogistica.msclient.modules.client.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientModuleView {

	
	@Include
	private Long moduleId;
	private String moduleName;
	
	public ClientModuleView(ClientModule entity) {
		super();
		this.moduleId = entity.getModule().getId();
		this.moduleName = entity.getModule().getName();
	}
	
	
}
