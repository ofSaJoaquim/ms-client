package br.com.branetlogistica.msclient.core.security.tenant;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.core.tenant.service.TenantService;

@Component
public class TenantJwtIssuerValidator implements OAuth2TokenValidator<Jwt> {
   
	@Autowired
	private TenantService tenantService;
    private final Map<String, JwtIssuerValidator> validators = new ConcurrentHashMap<>();

    
    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        return this.validators.computeIfAbsent(toTenant(token), this::fromTenant)
                .validate(token);
    }

    private String toTenant(Jwt jwt) {
    	 return jwt.getClaimAsString("x-tenant-id");       
    }

    private JwtIssuerValidator fromTenant(String tenant) {
               
        return new JwtIssuerValidator(tenantService.findTenant(tenant).getAttribute("issuer").toString());
    }
}
