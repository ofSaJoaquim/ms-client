package br.com.branetlogistica.msclient.modules.module.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.branetlogistica.msclient.core.interfaces.impl.EntityCommun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "module")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Module extends EntityCommun  {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "name", nullable = false, unique = true )
	private String name;
	
	@Column(name = "description")
	private String description;	
	
	@Column(name = "key", nullable = false, unique = true )
	private String key;
	
	public Module(Long id) {
		super();
		this.id = id;
	}
	
	
	
}
