package br.com.branetlogistica.msclient.modules.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Immutable
@Subselect("select * from application_view")
public class ApplicationView {	
	@Id
	@Include
	private Long id;	
	private String name;		
	private boolean useDataBase;	
	private boolean disabled;
	private String moduleNome;
	private Long moduleId;

	
}
