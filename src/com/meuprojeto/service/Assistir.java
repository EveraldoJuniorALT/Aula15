package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;
import com.meuprojeto.repository.InRepositorio;

import java.util.Scanner;

public class Assistir {
    private InRepositorio repositorio;
    private Gafanhoto gafanhoto;
    private Video video;
    private double nota;
    private Integer estadoLike;

    public Assistir(InRepositorio repositorio, Gafanhoto gafanhoto, Video video) {
        this.repositorio = repositorio;
        this.gafanhoto = gafanhoto;
        this.video = video;
        this.nota = 0;
        this.estadoLike = null;
    }
    public Assistir(Double nota, Integer estadoLike){
        this.nota = nota;
        this.estadoLike = estadoLike;
    }

    @Override
    public String toString() {
        return "Your review in this video /n" +
                "Nota: " + nota + "/n" +
                "EstadoLike=" + estadoLike +
                '}';
    }

    public void criarConexaoObjeto(int idGafan, int idVideo, Scanner scanner) {
        gafanhoto = repositorio.getGafanhotos(idGafan);
        video = repositorio.getVideos(idVideo);

        conectarGafanhotoVideo(gafanhoto, video, idGafan, idVideo, scanner);
    }

    private void conectarGafanhotoVideo(Gafanhoto g, Video v, int idGafan, int idVideo, Scanner scanner) {
        if (g == null || v == null) {
            System.out.println("Erro: Gafanhoto ou Video não encontrado");
            return;
        }
        Visualizacao visualizacao = new Visualizacao(g, v, idGafan, idVideo);
        while (true) {

            int resp = lerMenuConectarObjeto(scanner);
            if (resp == 5) {
                repositorio.updateGafanhoto(idGafan);
                repositorio.updateVideo(idVideo);
                break;
            }

            switch (resp) {
                case 1:
                    visualizacao.avaliar();
                    break;
                case 2:
                    visualizacao.avaliar(scanner);
                    break;
                case 3:
                    visualizacao.darLike();
                    break;
                case 4:
                    visualizacao.darDisLike();
                    break;
                default:
                    System.out.println("Opção Inválida. Por favor, escolha uma das opções");
                    break;
            }
        }
    }

    private int lerMenuConectarObjeto(Scanner scanner) {
        while (true) {
            System.out.println("1. Avaliar Video.");
            System.out.println("2. Avaliar Com Sua Nota.");
            System.out.println("3. Marcar Com Gostei.");
            System.out.println("4. Marcar Com Não Gostei.");
            System.out.println("5. Voltar.");

            if (!scanner.hasNextInt()) {
                System.out.println("Valor Inválido. Por favor, Digite apenas números!");
                scanner.nextLine();
                continue;
            }
            int resp = scanner.nextInt();
            scanner.nextLine();

            if (resp < 1 || resp > 5) {
                System.out.println("Opção inválida. Por favor escolha uma das opções");
                continue;
            }
            return resp;
        }
    }

    public double getNota(){
        return nota;
    }

    public void updateNota(double nota){
        this.nota = nota;
    }

    public Integer getEstadoLike() {
        return estadoLike;
    }

    public void updateEstadoLike(Integer estadoLike) {
        this.estadoLike = estadoLike;
    }
}
