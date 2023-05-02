package br.com.branetlogistica.msclient.core.datasource.migration;



import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlywayMigrationInitializer {

	@Value("${spring.datasource.hikari.schema}")
	private String schemaName;

	public void migrate() {
		this.migrate((DataSource) null);
	}

	public void migrate(DataSource dataSource) {
		String scriptLocation = "db/migration";
		Flyway flyway = Flyway.configure().locations(scriptLocation).baselineOnMigrate(Boolean.TRUE)
				.dataSource(dataSource).schemas(schemaName).load();

		flyway.migrate();
	}

}

