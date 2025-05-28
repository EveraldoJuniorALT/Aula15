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

    // Métodos relacionados a classe 'Gafanhoto'
    @Override
    public Gafanhoto getGafanhotos(int index) {
        /*
         *O IF abaixo faz a verificação primeiro no cache
         * Se não houver o objeto com o ID passado no "index" em cache
         * Faz request no banco, retornando o objeto e também guardando em cache
         */

        if (gafanhotos.containsKey(index)) { // Verifica se existe um gafanhoto com ID passado no "index"
            return gafanhotos.get(index); // Se a codição for verdadeira, pega o gafanhoto pelo ID usando o "index"
        }
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco");
        }

        GafanhotoDAO daoG = new GafanhotoDAO(conn);
        Gafanhoto g = daoG.pegarG(index);

        if (g != null) {
            gafanhotos.put(index, g);
        }
        return g;
    }

    @Override
    public void saveGafanhotoDB(Gafanhoto g) {
        Connection conn = ConexaoMySQL.getConection(); // Recebe a conexão com o DB
        if (conn == null) { //
            throw new IllegalStateException("Não foi possível se conectar ao banco!");
        }

        GafanhotoDAO daoG = new GafanhotoDAO(conn); // Instancia a classe DAO e passa a conexão
        daoG.salvarG(g);
        System.out.println(g.getNome() + " Foi salvo com sucesso!");

        try {
            conn.close(); // Tenta fechar o acesso ao banco
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalGafanhotos() {
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco ");
        }

        GafanhotoDAO daoG = new GafanhotoDAO(conn);

        int totalDB = daoG.contarG();
        return totalDB;
    }

    @Override
    public void requestGafanhoto(int idGafanhoto) {
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco!");
        }

        GafanhotoDAO daoG = new GafanhotoDAO(conn);
        Gafanhoto g = daoG.pegarG(idGafanhoto); // Passa o ID do gafanhato e recebe os dados do mesmo

        if (g != null) {
            gafanhotos.put(idGafanhoto, g);
            System.out.println(gafanhotos.get(idGafanhoto));

        }
    }

    @Override
    public void updateGafanhoto(int idGafanhoto) {
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco");
        }

        GafanhotoDAO daoG = new GafanhotoDAO(conn);
        daoG.atualizarG(gafanhotos.get(idGafanhoto), idGafanhoto);
    }

    // Métodos relacionados a Class 'Video'
    @Override
    public Video getVideos(int index) {
        /*
         * O IF abaixo faz a verificação primeiro no cache
         * Se não hover o objeto com o ID passado no "index" em cache
         * Faz request no banco, retornando o objeto e também armazenando em cache
         */
        if (videos.containsKey(index)) { // Verifica se existe um video com ID passado no "index"
            return videos.get(index); // Se a codição for verdadeira, pega o video pelo ID usando o "index"
        }
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco!");
        }

        VideoDAO daoV = new VideoDAO(conn);
        Video v = daoV.pegarV(index);

        if (v != null) {
            videos.put(index, v);
        }
        return v;
    }

    @Override
    public void saveVideoDB(Video v) {
        Connection conn = ConexaoMySQL.getConection(); // Recebe a conexão com o DB
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco!");
        }

        VideoDAO daoV = new VideoDAO(conn);
        daoV.salvarV(v); // Chama o método e passa o 'com.meuprojeto.model.Video' com parâmentro onde vai salvar os dados
        System.out.println(v.getTitulo() + " Foi salvo com sucesso!");
    }

    @Override
    public int getTotalVideos() {
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco");
        }

        VideoDAO daoV = new VideoDAO(conn);

        int totalDB = daoV.contarV(); //Chama o método e armazena o total de Gafanhotos do DB na variável totalDB
        return totalDB;
    }

    @Override
    public void requestVideo(int idVideo) {
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco!");
        }

        VideoDAO daoV = new VideoDAO(conn);
        Video v = daoV.pegarV(idVideo);

        if (v != null) {
            videos.put(idVideo, v);
            System.out.println(videos.get(idVideo));
        }
    }

    @Override
    public void updateVideo(int idVideo) {
        Connection conn = ConexaoMySQL.getConection();
        if (conn == null) {
            throw new IllegalStateException("Não foi possível conectar ao banco!");
        }

        VideoDAO daoV = new VideoDAO(conn);
        daoV.atualizarV(videos.get(idVideo), idVideo);
    }
}
