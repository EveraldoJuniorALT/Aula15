package com.meuprojeto.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
	private static final String URL = "jdbc:mysql://localhost:3306/aula15";
	private static final String USER = "root";
	private static final String PASS = "";
	
	protected static Connection getConection() {
		try {
			Connection conexao = DriverManager.getConnection(URL, USER, PASS); // tenta conectar-se ao DB passando o caminho, usuário e senha
			return conexao;
			
		} catch(SQLException e) { // Caso dê erro na conexão, caso o banco não esteja no ar ou algo do tipo
			System.out.println("Erro na conexão " + e.getMessage());
			return null;
		}
	}
}
