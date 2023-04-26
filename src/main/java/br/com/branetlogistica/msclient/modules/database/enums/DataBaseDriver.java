package br.com.branetlogistica.msclient.modules.database.enums;

import lombok.Getter;

@Getter
public enum DataBaseDriver {

	POSTGRES("org.postgresql.Driver","Postgres");
	
	
	private String driver;
	private String name;
	
	
	private DataBaseDriver(String driver, String name) {
		this.driver = driver;
		this.name = name;
	}
	
	
	
	
	
}
