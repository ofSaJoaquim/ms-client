package br.com.branetlogistica.msclient.core.datasource.service;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.core.datasource.migration.FlywayMigrationInitializer;

@Component
public class DataSourceService implements Serializable {

   
	private static final long serialVersionUID = 1L;	
	
	@Value("${spring.application.name}")
	private String nomeAplicativo;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.driverClassName}")
	private String driver;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Autowired
	private FlywayMigrationInitializer context;
   
  
    
    private String getUrlWithoutPort() {
    	int pos = url.lastIndexOf("/");
    	return url.substring(0, pos+1);
    }

    public DataSource createDataSource(String dataBaseName)  {   
    	try {
    		
        	String url = getUrlWithoutPort()+dataBaseName;
        	
            @SuppressWarnings("rawtypes")
    		DataSourceBuilder factory = DataSourceBuilder
                    .create().driverClassName(driver)
                    .username(username)
                    .password(password)
                    .url(url)
                    
                   ;
            DataSource ds = factory.build();
            context.migrate(ds);
            return ds;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
	
    
    

}
