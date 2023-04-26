package br.com.branetlogistica.msclient.modules.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseResponse {
	
	private Long id;
	private String login;	
	private String password;	
	private String defaultDataBase;
	private String driver;	
	private String url;	
	
}
