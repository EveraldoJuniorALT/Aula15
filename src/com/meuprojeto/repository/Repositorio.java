package com.meuprojeto.repository;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Repositorio implements InRepositorio {

    private final Map<Integer, Video> videos = new HashMap<>();
    private final Map<Integer, Gafanhoto> gafanhotos = new HashMap<>();

    @FunctionalInterface
    private interface DbOperation<T> {
        T execute(Connection conn) throws SQLException;
    }

    @FunctionalInterface
    private interface VoidDbOperation {
        void execute(Connection conn) throws SQLException;
    }

    private <T> T executarComConexao(DbOperation<T> operation) {
        try (Connection conn = ConexaoMySQL.getConnection()) {
            return operation.execute(conn);
        } catch (SQLException e) {
            System.out.println("Erro: Not was possible get connection and execute! Line 30");
            throw new RuntimeException(e);
        }
    }

    private void executarComConexao(VoidDbOperation operation) {
        try (Connection conn = ConexaoMySQL.getConnection()) {
            operation.execute(conn);
        } catch (SQLException e) {
            System.out.println("Erro: Not was possible get connection and execute! Line 39");
            throw new RuntimeException(e);
        }
    }

    // Métodos relacionados a classe 'Gafanhoto'
    @Override
    public Gafanhoto getGafanhotos(int index) {
        if (gafanhotos.containsKey(index)) { // Verifica se existe um gafanhoto com ID passado no "index"
            return gafanhotos.get(index);
        }

        Gafanhoto gafanhoto = executarComConexao(conn -> {
            GafanhotoDAO daoG = new GafanhotoDAO(conn);
            return daoG.pegarG(index);
        });

        if (gafanhoto != null) {
            gafanhotos.put(index, gafanhoto);
        }
        return gafanhoto;
    }

    @Override
    public void saveGafanhotoDB(Gafanhoto g) {
        executarComConexao(conn -> {
            GafanhotoDAO daoG = new GafanhotoDAO(conn);
            daoG.salvarG(g);
            System.out.printf("%s Foi salvo com sucesso!%n", g.getNome());
        });
    }

    @Override
    public int getTotalGafanhotos() {
        return executarComConexao(conn -> {
            GafanhotoDAO daoG = new GafanhotoDAO(conn);
            return daoG.contarG();
        });
    }

    @Override
    public void requestGafanhoto(int idGafanhoto) {
        Gafanhoto gafanhoto = executarComConexao(conn -> {
            GafanhotoDAO daoG = new GafanhotoDAO(conn);
            return daoG.pegarG(idGafanhoto); // Passa o ID do gafanhato e recebe os dados do mesmo
        });

        if (gafanhoto != null) {
            gafanhotos.put(idGafanhoto, gafanhoto);
            System.out.println(gafanhotos);
        }
    }

    @Override
    public void updateGafanhoto(int idGafanhoto) {
        executarComConexao(conn -> {
            GafanhotoDAO daoG = new GafanhotoDAO(conn);
            daoG.atualizarG(gafanhotos.get(idGafanhoto), idGafanhoto);
        });
    }

    // Métodos relacionados a Class 'Video'
    @Override
    public Video getVideos(int index) {
        if (videos.containsKey(index)) { // Verifica se existe um video com ID passado no "index"
            return videos.get(index);
        }
        Video v = executarComConexao(conn -> {
            VideoDAO daoV = new VideoDAO(conn);
            return daoV.pegarV(index);
        });

        if (v != null) {
            videos.put(index, v);
        }
        return v;
    }

    @Override
    public void saveVideoDB(Video v) {
        executarComConexao(conn -> {
            VideoDAO daoV = new VideoDAO(conn);
            daoV.salvarV(v);
            System.out.println(v.getTitulo() + " Foi salvo com sucesso!");
        });
    }

    @Override
    public int getTotalVideos() {
        return executarComConexao(conn -> {
            VideoDAO daoV = new VideoDAO(conn);
            return daoV.contarV();
        });
    }

    @Override
    public void requestVideo(int idVideo) {
        Video video = executarComConexao(conn -> {
            VideoDAO daoV = new VideoDAO(conn);
            return daoV.pegarV(idVideo);
        });

        if (video != null) {
            videos.put(idVideo, video);
            System.out.println(video);
        }
    }

    @Override
    public void updateVideo(int idVideo) {
        executarComConexao(conn -> {
            VideoDAO daoV = new VideoDAO(conn);
            daoV.atualizarV(videos.get(idVideo), idVideo);
        });
    }
}
