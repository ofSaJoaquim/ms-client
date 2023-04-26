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
public class ClientApplicationView {

	
	@Include
	private Long applicationId;
	private String applicationName;
	
	public ClientApplicationView(ClientApplication entity) {
		super();
		this.applicationId = entity.getApplication().getId();
		this.applicationName = entity.getApplication().getName();
	}
	
	
}
