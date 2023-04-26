package br.com.branetlogistica.msclient.modules.client.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.branetlogistica.msclient.core.interfaces.impl.EntityCommun;
import br.com.branetlogistica.msclient.modules.module.model.Module;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "client_module", uniqueConstraints = { @UniqueConstraint(columnNames = { "client_id", "module_id" }) })
public class ClientModule extends EntityCommun {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="client_id" )	
	private Client client;	
	
	@ManyToOne
	@JoinColumn(name="module_id" )	
	private Module module;	
		

	
	
	
}
