package com.meuprojeto.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoMySQL {
	private static final HikariDataSource dataSource;

	static {
		HikariConfig config = new HikariConfig();

		config.setJdbcUrl("jdbc:mysql://localhost:3306/aula15");
		config.setUsername("root");
		config.setPassword("");

		config.setMaximumPoolSize(10);
		config.setMinimumIdle(10);
		config.setConnectionTimeout(30000);
		config.setMaxLifetime(1800000);

		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
