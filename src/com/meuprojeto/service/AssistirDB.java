package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;
import com.meuprojeto.repository.VisualizacaoDAO;
import com.meuprojeto.repository.ConexaoMySQL;
import com.meuprojeto.repository.InRepositorio;

import java.sql.Connection;
import java.sql.SQLException;

public class AssistirDB {
    private final InRepositorio repositorio;

    public AssistirDB(InRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void conectarObjetos(int idGafan, int idVideo) {
        pegarInteracao(idGafan, idVideo);
    }

    private void pegarInteracao(int idGafan, int idVideo) {
        try (Connection conn = ConexaoMySQL.getConnection()) {

            VisualizacaoDAO visualizacaoDAO = new VisualizacaoDAO(conn);
            Assistir assistir = visualizacaoDAO.getInteracao(idGafan, idVideo, repositorio);

            if (assistir != null) {
                assistir.validarNota();
                assistir.validarEstadoLike();
                assistir.conectarGafanhotoVideo(idGafan, idVideo, visualizacaoDAO);
            }

            if (assistir == null) {
                criarInteracao(idGafan, idVideo, visualizacaoDAO);
            }

        } catch (SQLException e) {
            System.out.println("Erro: unable connect to database");
            throw new RuntimeException(e);
        }

    }

    private void criarInteracao(int idGafan, int idVideo, VisualizacaoDAO visualizacaoDAO) {

        Gafanhoto gafan = this.repositorio.getGafanhotos(idGafan);
        Video video = this.repositorio.getVideos(idVideo);

        gafan.viuMaisUm();
        video.updateViews(video.getViews() + 1);

        this.repositorio.updateGafanhoto(idGafan);
        this.repositorio.updateVideo(idVideo);
        visualizacaoDAO.criarInteracao(idGafan, idVideo);

        Assistir assistir = new Assistir(repositorio);
        assistir.conectarGafanhotoVideo(idGafan, idVideo, visualizacaoDAO);
    }
}
