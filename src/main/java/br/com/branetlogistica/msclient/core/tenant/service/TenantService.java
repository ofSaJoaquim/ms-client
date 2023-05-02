package br.com.branetlogistica.msclient.core.tenant.service;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.branetlogistica.msclient.core.datasource.service.DataSourceService;
import br.com.branetlogistica.msclient.core.exceptions.ApiException;
import br.com.branetlogistica.msclient.core.exceptions.ClientNotExistException;
import br.com.branetlogistica.msclient.core.security.util.OidcProviderConfigurationUtils;
import br.com.branetlogistica.msclient.core.tenant.dto.ClientApplicationInfo;
import br.com.branetlogistica.msclient.core.tenant.model.Tenant;

@Service
public class TenantService {
		
//	@Autowired
//	private TenantClient client;
	
	@Autowired
	private DataSourceService dataSourceService;
	
	@Value("${default-tenant-id}")
	private  String DEFAULT_TENANT_ID;
	
	@Value("${use-single-tenant}")
	private  boolean USE_SINGLE_TENANT;
	
	@Value("${default-database-name}")
	private String DEFAULT_DATABASE_NAME;
	
	@Value("${default-authorization-server}")
	private String DEFAULT_AUTHORIZATION_SERVER;
	
	private final HashMap<String, Tenant>tenantList = new HashMap<>();
	
	
	
	public Tenant findTenant(String uuid) {
		if(USE_SINGLE_TENANT && !uuid.equals(DEFAULT_TENANT_ID))
			 throw new ApiException(HttpStatus.BAD_REQUEST,String.format("Not exist client with x-tenant-id %1$s", uuid),null);
			
		if(tenantList.containsKey(uuid))
			return tenantList.get(uuid);
		
		return createTenant(uuid);
	}
	
	public Tenant findDefaultTenant() {
		if(tenantList.containsKey(DEFAULT_TENANT_ID))
			return tenantList.get(DEFAULT_TENANT_ID);
		throw new ApiException(HttpStatus.BAD_REQUEST,String.format("Not exist client with x-tenant-id %1$s", DEFAULT_TENANT_ID),null);
		
		
	}
	
	public synchronized Tenant createDefaultTenant(DataSource dataSource) {
		return createTenant(DEFAULT_TENANT_ID,dataSource);
	}
				
	public synchronized Tenant createTenant(String uuid) {
		return createTenant(uuid,null);
	}
	
	private synchronized Tenant createTenant(String uuid, DataSource dataSource) {
		if(tenantList.containsKey(uuid))
			return tenantList.get(uuid);
		
		
		ClientApplicationInfo clientInfo =  null;
		if(uuid.equals(DEFAULT_TENANT_ID)) 
			clientInfo = new ClientApplicationInfo(DEFAULT_DATABASE_NAME, 100);
		
		//else		
		//client.findClientModule(uuid, uuid).getBody();
		if(clientInfo==null) 
			 throw new ApiException(HttpStatus.BAD_REQUEST,"x-tenant-id not informed",null);
		if(dataSource==null)
			dataSource =  dataSourceService.createDataSource(clientInfo.getDataBaseName());
		final Map<String, Object> authServerAttributes = 
				OidcProviderConfigurationUtils.getConfigurationForOidcIssuerLocation(DEFAULT_AUTHORIZATION_SERVER+uuid);
		
		Tenant tenant = Tenant.builder()
		.uuid(uuid)
		.dataSource(dataSource)
		.authServerAttributes(authServerAttributes)
		.maxConnections(clientInfo.getMaxConnections()).build();
		
		tenantList.put(uuid, tenant);
		
		return tenant;
		
	}
	

}
