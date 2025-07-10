package com.meuprojeto.repository;

import com.meuprojeto.service.Assistir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizacaoDAO {
    Connection conn;

    public VisualizacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public Assistir getAssistido(int idGafan, int idVideo, InRepositorio repositorio) {
        String sql = "select * from interacao WHERE espectador_id = ? and video_id = ?";
        Assistir assistir = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGafan);
            stmt.setInt(2, idVideo);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Double nota = rs.getDouble("nota");
                Integer estadoLike = rs.getInt("estado_like");
                assistir = new Assistir(nota, estadoLike, repositorio);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("This viewer has not watched this video yet", e);
        }
        return assistir;
    }
}
