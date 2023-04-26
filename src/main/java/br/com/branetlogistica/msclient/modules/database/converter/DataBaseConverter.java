package br.com.branetlogistica.msclient.modules.database.converter;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.modules.database.dto.DataBaseRequest;
import br.com.branetlogistica.msclient.modules.database.enums.DataBaseDriver;
import br.com.branetlogistica.msclient.modules.database.model.DataBase;

@Component
public class DataBaseConverter {

	
	public DataBase toEntity(DataBaseRequest object) {
		return DataBase.builder()
		.login(object.getLogin())
		.password(object.getPassword())
		.driver(DataBaseDriver.valueOf(object.getDriver()))
		.url(object.getUrl())
		.defaultDataBase(object.getDefaultDataBase())
		.build();
	}
	
}
