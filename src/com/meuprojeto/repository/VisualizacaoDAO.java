package com.meuprojeto.repository;

import com.meuprojeto.service.Assistir;

import java.sql.*;

public class VisualizacaoDAO {
    Connection conn;

    public VisualizacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public Assistir getAssistido(int idGafan, int idVideo, InRepositorio repositorio) {
        String sql = "SELECT * FROM interacao WHERE espectador_id = ? and video_id = ?";
        Assistir assistir = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGafan);
            stmt.setInt(2, idVideo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Double nota = rs.getDouble("nota");
                Integer estadoLike = rs.getInt("estado_like");
                assistir = new Assistir(nota, estadoLike, repositorio);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("This viewer has not watched this video yet", e);
        }
        return assistir;
    }

    public void criarInteracao(int idGafan, int idVideo) {
        String sql = "INSERT INTO interacao (espectador_id, video_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGafan);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error while crating interacao", e);
        }
    }
}
