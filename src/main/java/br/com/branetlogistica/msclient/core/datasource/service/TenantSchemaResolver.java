package br.com.branetlogistica.msclient.core.datasource.service;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.core.context.Context;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

	@Value("${default-tenant-id}")
	private String DEFAULT_TENANT_ID;

	@Override
	public String resolveCurrentTenantIdentifier() {
		if (Context.getContextData() == null)
			return DEFAULT_TENANT_ID;
		return Context.getContextData().getTenantId();

	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
