package com.meuprojeto.repository;

import com.meuprojeto.model.Gafanhoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GafanhotoDAO {
	private final Connection conn;

	public GafanhotoDAO(Connection conn) {
		this.conn = conn;
	}

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
			throw new IllegalStateException("Erro ao tentar salvar o Gafanhoto!", e);
		}
	}

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
			throw new IllegalStateException("Erro ao tentar contar Gafanhoto!", e);
		}
		return totalGDB;
	}
	
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
				int experiencia = rs.getInt("experiencia");
				String login = rs.getString("login");
				int totAssistido = rs.getInt("totassistido");

				gafanhotos = new Gafanhoto(nome, sexo, idade, experiencia ,login, totAssistido);
			} else {
				System.out.println("Usuário não encontrado!");
			}

			rs.close();
		} catch (SQLException e) {
			throw new IllegalStateException("Erro ao tentar pegar o Gafanhoto!", e);
		}
		return gafanhotos;
	}

	protected void atualizarG(Gafanhoto g, int idG) {
		String sql = "UPDATE gafanhoto SET totassistido = ?, experiencia = ? WHERE id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, g.getTotAssistido());
			stmt.setInt(2, g.getExperiencia());
			stmt.setInt(3, idG);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("Erro ao atualizar o Gafanhoto!", e);
		}
	}
}
