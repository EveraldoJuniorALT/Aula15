package com.meuprojeto.repository;

import com.meuprojeto.model.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoDAO {
    private final Connection conn;

    public VideoDAO(Connection conn) {
        this.conn = conn;
    }

    protected void salvarV(Video v) {
        String sql = "INSERT INTO video (titulo, avaliacao, views, gostei, naogostei) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getTitulo());
            stmt.setDouble(2, v.getAvaliacao());
            stmt.setInt(3, v.getViews());
            stmt.setInt(4, v.getGostei());
            stmt.setInt(5, v.getNaoGostei());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e + "erro na linha 28");
        }
    }

    protected int contarV() {
        String sql = "SELECT COUNT(*) FROM video";
        int totalVDB = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalVDB = rs.getInt(1);
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + "erro na linha 45");
        }
        return totalVDB;
    }

    protected Video pegarV(int idVideo) {
        String sql = "SELECT * FROM video WHERE id = ?";
        Video videos = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVideo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                double avaliacao = rs.getDouble("avaliacao");
                int views = rs.getInt("views");
                int gostei = rs.getInt("gostei");
                int naogostei = rs.getInt("naogostei");
                int totAvaliacao = rs.getInt("total_avaliacao");
                videos = new Video(titulo, avaliacao, views, gostei, naogostei, totAvaliacao);
            } else {
                System.out.println("Vídeo não encontrado!");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + "erro na linha 71");
        }
        return videos;
    }

    protected void atualizarV(Video v, int idV) {
        String sql = "UPDATE video SET avaliacao = ?, views = ?, gostei = ?, naogostei = ?, total_avaliacao = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, v.getAvaliacao());
            stmt.setInt(2, v.getViews());
            stmt.setInt(3, v.getGostei());
            stmt.setInt(4, v.getNaoGostei());
            stmt.setInt(5, v.getTotAvaliacao());
            stmt.setInt(6, idV);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e + "erro na linha 89");
        }
    }

    protected void deletarVideo(int idVideo) {
        String sql = "DELETE FROM video WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVideo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e + "erro na linha 99");
        }
    }
}
