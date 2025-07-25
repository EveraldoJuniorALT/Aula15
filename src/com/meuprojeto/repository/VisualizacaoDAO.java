package com.meuprojeto.repository;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.service.Assistir;

import java.sql.*;

public class VisualizacaoDAO {
    Connection conn;

    public VisualizacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public Assistir getInteracao(int idGafan, int idVideo, InRepositorio repositorio) {
        String sql = "SELECT * FROM interacao WHERE espectador_id = ? and video_id = ?";
        Assistir assistir = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGafan);
            stmt.setInt(2, idVideo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Double nota = rs.getDouble("nota");
                int estadoLike = rs.getInt("estado_like");
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

    public void salvarAvaliacao(int idGafan, int idVideo, Gafanhoto gafan) {
        String sql = "UPDATE interacao SET nota = ?, estado_like = ? WHERE espectador_id = ? AND video_id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, gafan.getNotaDaAvalicao());
            stmt.setInt(2, gafan.getEstadoLike());
            stmt.setInt(3, idGafan);
            stmt.setInt(4, idVideo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error while salving interacao", e);
        }
    }
}
