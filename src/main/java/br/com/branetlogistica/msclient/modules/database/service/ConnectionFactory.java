package br.com.branetlogistica.msclient.modules.database.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.branetlogistica.msclient.modules.database.model.DataBase;



public class ConnectionFactory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//private static HashMap<String, Connection> connections = new HashMap<>();
	private static Connection conn;
	public static Connection createDataSource(DataBase dataBase, String base, String indentificator) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(dataBase.getUrl());
		sql.append("/");
		sql.append(base);

		sql.append("?user=");
		sql.append(dataBase.getLogin());
		sql.append("&password=");
		sql.append(dataBase.getPassword());
		try {
			 conn = DriverManager.getConnection(sql.toString());
			 conn.setAutoCommit(true);
			//connections.put(indentificator, conn);
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError(e.getMessage());
		}

		
	}
	
	private  static void closeConnetion(String indetificator) {
		try {
			/*if(connections.containsKey(indetificator)) {
				Connection con = connections.get(indetificator);				
				con.close();	
				connections.remove(indetificator);*/
			if(conn!=null)
				conn.close();
			
				
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	public static void criarBaseDados(DataBase connector, String dataBaseName) {
		StringBuffer sql = new StringBuffer(" CREATE DATABASE ");
		sql.append(dataBaseName);	
		sql.append("; ");		
		try (Connection con = createDataSource(connector, 
				connector.getDefaultDataBase(),
				dataBaseName);
				PreparedStatement stmt = con.prepareStatement(sql.toString());) {
			stmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError(e.getMessage());
		}finally {
			closeConnetion(dataBaseName);
		}
	
	}
	
	public static boolean checaBaseDadosExiste(DataBase connector, String dataBaseName) {
		StringBuffer sql = new StringBuffer(" SELECT 1 FROM pg_database WHERE datname= '");		
		sql.append(dataBaseName);	
		sql.append("'; ");		
		try (Connection con = createDataSource(connector, 
				connector.getDefaultDataBase(),
				dataBaseName);				
				PreparedStatement stmt = con.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				if (rs.getObject(1) != null)
					return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError(e.getMessage());
		}finally {
			closeConnetion(dataBaseName);
		}
		
		return false;
	}
	
	public static boolean checaSchemaExiste(DataBase connector, String schemaName, String dataBaseName) {
		StringBuffer sql = new StringBuffer(" SELECT 1 FROM information_schema.schemata WHERE schema_name = '");		
		sql.append(schemaName);	
		sql.append("'; ");		
		try (Connection con = createDataSource(connector, 
				dataBaseName,
				dataBaseName);	
				PreparedStatement stmt = con.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				if (rs.getObject(1) != null) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError(e.getMessage());
		}finally {
			closeConnetion(dataBaseName);
		}
		
		
	}
	
	
	public static void iniciarBaseDados( DataBase connector, String schemaName, String dataBaseName) {
		StringBuffer sql = new StringBuffer(" CREATE SCHEMA ");
		sql.append(schemaName);
		sql.append(" ;");
				
		try (Connection con = createDataSource(connector, 
				dataBaseName,dataBaseName);				
				PreparedStatement stmt = con.prepareStatement(sql.toString());) {
			stmt.execute();
		

		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError(e.getMessage());
		} finally {
			closeConnetion(dataBaseName);
		}
	
		

	}
	
	

	
	
	
	/*private static HikariConfig teste(DataBaseConnection dataBase, String base) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(dataBase.getDriver().getDriver());
		hikariConfig.setJdbcUrl(dataBase.getUrl()+"/"+base);
		hikariConfig.setUsername(dataBase.getUser());
		hikariConfig.setPassword(dataBase.getPassword());
		hikariConfig.setAutoCommit(Boolean.TRUE);	
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setMaximumPoolSize(1);
		hikariConfig.setMaxLifetime(60000);
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", Boolean.TRUE);
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", 250);
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", 2048);
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", Boolean.TRUE);
		hikariConfig.addDataSourceProperty("dataSource.useLocalSessionState", Boolean.TRUE);
		hikariConfig.addDataSourceProperty("dataSource.rewriteBatchedStatements", Boolean.TRUE);
		hikariConfig.addDataSourceProperty("dataSource.cacheResultSetMetadata", Boolean.TRUE);
		hikariConfig.addDataSourceProperty("dataSource.cacheServerConfiguration", Boolean.TRUE);
		hikariConfig.addDataSourceProperty("dataSource.maintainTimeStats", Boolean.FALSE);
		return hikariConfig;
}*/

	
	
	








	
	
	
}