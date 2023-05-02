package br.com.branetlogistica.msclient.core.datasource.service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.branetlogistica.msclient.core.tenant.service.TenantService;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private DataSource defaultDS;

	@Autowired
	private TenantService tenantService;

	@PostConstruct
	public void load() {
		tenantService.createDefaultTenant(defaultDS);
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return tenantService.findDefaultTenant().getDataSource();
	}

	@Override
	protected DataSource selectDataSource(String uuid) {
		return tenantService.findTenant(uuid).getDataSource();

	}
}