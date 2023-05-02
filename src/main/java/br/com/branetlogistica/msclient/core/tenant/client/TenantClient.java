package br.com.branetlogistica.msclient.core.tenant.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.branetlogistica.msclient.core.tenant.dto.ClientApplicationInfo;



@Component
//@FeignClient(name ="base-client", path = "${clientserver.path}", url = "${clientserver.url}")
public interface TenantClient {

	@GetMapping(path = "findByUUID/{UUID}/apps/{moduleName}")
    public ResponseEntity<ClientApplicationInfo> 
	findClientModule(@PathVariable(name = "UUID" )String UUID,@PathVariable(name = "moduleName")String moduleName);
  
		
		

	
}