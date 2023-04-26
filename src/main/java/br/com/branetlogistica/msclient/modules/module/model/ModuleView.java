package br.com.branetlogistica.msclient.modules.module.model;

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
@Subselect("SELECT * FROM module_view")
public class ModuleView {

	@Id
	@Include
	private Long id;
	private String name;
	private String key;
	private boolean disabled;

}