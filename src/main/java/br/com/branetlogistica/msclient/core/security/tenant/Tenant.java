package br.com.branetlogistica.msclient.core.security.tenant;

import java.util.Map;

import br.com.branetlogistica.msclient.core.security.util.OidcProviderConfigurationUtils;

public class Tenant {

    private final Map<String, Object> attributes;

    public Tenant(String issuerUri) {
        attributes = OidcProviderConfigurationUtils.getConfigurationForOidcIssuerLocation(issuerUri);
    }

    public Object getAttribute(String attribute) {
        return attributes.get(attribute);
    }
}