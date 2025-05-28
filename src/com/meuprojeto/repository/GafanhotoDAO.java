package com.meuprojeto.repository;

import com.meuprojeto.model.Gafanhoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GafanhotoDAO {
	private Connection conn;

	public GafanhotoDAO(Connection conn) {
		this.conn = conn;
	}
	
	/*
	 * Recebe o object como parâmetro
	 * Salva no banco, caso dê erro, informa no console
	 */
	protected void salvarG(Gafanhoto g) {
		String slq = "INSERT INTO gafanhoto (nome, sexo, idade, login, totassistido, experiencia) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(slq)) {
			stmt.setString(1, g.getNome());
			stmt.setString(2, g.getSexo());
			stmt.setInt(3, g.getIdade());
			stmt.setString(4, g.getLogin());
			stmt.setInt(5, g.getTotAssistido());
			stmt.setInt(6, g.getExperiencia());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Serve para informar a quatidade de registros que tem no banco
	 * retorna o valor para onde onde foi chamada
	 */
	protected int contarG() {
		String sql = "SELECT COUNT(*) FROM gafanhoto";
		int totalGDB = 0;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				totalGDB = rs.getInt(1);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalGDB;
	}
	
	/*
	 * Recebe o ID do gafanhoto no qual o user deseja consultar
	 * Passa o parâmetro paro método 
	 * Vai executar a query e retornar a query
	 * Vai instaciar o objeto e retorna-lo
	 */
	
	protected Gafanhoto pegarG(int idGafan) {
		String sql = "SELECT * FROM gafanhoto WHERE id = ?";
		Gafanhoto gafanhotos = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idGafan);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("nome");
				String sexo = rs.getString("sexo");
				int idade = rs.getInt("idade");
				String login = rs.getString("login");
				int totAssistido = rs.getInt("totassistido");

				gafanhotos = new Gafanhoto(nome, sexo, idade, login);
				gafanhotos.updateTotAssistido(totAssistido);
			} else {
				System.out.println("Usuário não encontrado!");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gafanhotos;
	}
	
	/*
	 * Recebe um objeto como parâmetro
	 * Atualiza dois atributos do meu objeto
	 */
	protected void atualizarG(Gafanhoto g, int idG) {
		String sql = "UPDATE gafanhoto SET totassistido = ?, experiencia = ? WHERE id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, g.getTotAssistido());
			stmt.setInt(2, g.getExperiencia());
			stmt.setInt(3, idG);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
