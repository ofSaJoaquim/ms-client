package br.com.branetlogistica.msclient.modules.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.branetlogistica.msclient.core.interfaces.impl.EntityCommun;
import br.com.branetlogistica.msclient.modules.database.enums.DataBaseDriver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "data_base_connector")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataBase extends EntityCommun {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "url", nullable = false)
	private String url;	
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "driver" , nullable = false)
	@Enumerated(EnumType.STRING)
	private DataBaseDriver driver;
	
	@Column(name = "default_data_base")
	private String defaultDataBase;
		
	public DataBase clone() {
		return new DataBase(id, url, password, login, driver, defaultDataBase);
	}
	
	
	
	
}
