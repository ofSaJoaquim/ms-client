package br.com.branetlogistica.msclient.modules.client.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.branetlogistica.msclient.core.interfaces.impl.EntityCommun;
import br.com.branetlogistica.msclient.modules.application.model.Application;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "client_application", uniqueConstraints = { @UniqueConstraint(columnNames = { "client_id", "application_id" }) })
public class ClientApplication extends EntityCommun {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="client_id" )	
	private Client client;	
	
	@ManyToOne
	@JoinColumn(name="application_id" )	
	private Application application;	
		
	@Column(name = "max_connections", nullable = false)
	private Integer maxConnections;
	
	
	
}
