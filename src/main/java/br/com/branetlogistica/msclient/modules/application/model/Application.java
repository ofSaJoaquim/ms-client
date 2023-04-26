package br.com.branetlogistica.msclient.modules.application.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.branetlogistica.msclient.core.interfaces.impl.EntityCommun;
import br.com.branetlogistica.msclient.modules.database.model.DataBase;
import br.com.branetlogistica.msclient.modules.module.model.Module;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application extends EntityCommun {
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "data_base_id", referencedColumnName = "id" )
	private DataBase dataBase;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "module_id", referencedColumnName = "id" )
	private Module module;
	
	

}