package br.com.branetlogistica.msclient.core.tenant.model;

import java.util.Map;

import javax.sql.DataSource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Tenant {

	private final String uuid;
	private final DataSource dataSource;
	private final Map<String, Object> authServerAttributes;
	private final Integer maxConnections;
	
	public Object getAttribute(String attribute) {
        return authServerAttributes.get(attribute);
    }
}
