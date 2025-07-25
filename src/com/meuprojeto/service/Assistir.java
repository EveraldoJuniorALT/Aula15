package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;
import com.meuprojeto.repository.InRepositorio;
import com.meuprojeto.repository.VisualizacaoDAO;
import com.meuprojeto.util.GerenciadorDeEntrada;

import java.util.Scanner;

public class Assistir {
    private final InRepositorio repositorio;
    private double nota;
    private int estadoLike;

    public Assistir(InRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Assistir(Double nota, int estadoLike, InRepositorio repositorio) {
        this.nota = nota;
        this.estadoLike = estadoLike;
        this.repositorio = repositorio;
    }

    @Override
    public String toString() {
        return "Nota: " + nota + "/n"
                + "EstadoLike: " + estadoLike;
    }

    public void conectarGafanhotoVideo(int idGafan, int idVideo, VisualizacaoDAO visualizacaoDAO) {
        Gafanhoto gafan = this.repositorio.getGafanhotos(idGafan);
        Video video = this.repositorio.getVideos(idVideo);

        /*
        * These values need to be saved, so that when line 93 is executed,
        * the values in the database are not 0.0 for the "nota" and 0 for the "estadoLike"
        * and I don't know how to do it any other way
        */
        gafan.guardarNotaDaAvalicao(this.nota);
        gafan.guardarEstadoLike(this.estadoLike);

        Visualizacao visualizacao = new Visualizacao(video, gafan);
        AvaliarVideo(idGafan, idVideo, visualizacao, visualizacaoDAO);
    }

    private void AvaliarVideo(int idGafan, int idVideo, Visualizacao visualizacao, VisualizacaoDAO visualizacaoDAO) {
        Scanner scanner = GerenciadorDeEntrada.getScanner();
        while (true) {
            int resposta = lerMenu(scanner);
            if (resposta == 5) {
                this.repositorio.updateGafanhoto(idGafan);
                this.repositorio.updateVideo(idVideo);
                break;
            }

            boolean jaAvaliou = this.nota > 0;
            switch (resposta) {
                case 1:
                    if (!jaAvaliou) {
                        visualizacao.avaliarPelaPrimeiraVez();
                        this.nota = visualizacao.getGafanhoto().getNotaDaAvalicao();
                    }

                    if (jaAvaliou) {
                        visualizacao.atualizarAvaliacaoExistente(this.nota);
                        this.nota = visualizacao.getGafanhoto().getNotaDaAvalicao();
                    }
                    break;
                case 2:
                    if (!jaAvaliou) {
                        visualizacao.avaliarPelaPrimeiraVez(scanner);
                        this.nota = visualizacao.getGafanhoto().getNotaDaAvalicao();
                    }

                    if (jaAvaliou) {
                        visualizacao.atualizarAvaliacaoExistente(this.nota, scanner);
                        this.nota = visualizacao.getGafanhoto().getNotaDaAvalicao();
                    }
                    break;
                case 3:
                    visualizacao.darLike(this.estadoLike);
                    this.estadoLike = visualizacao.getGafanhoto().getEstadoLike();
                    break;
                case 4:
                    visualizacao.darDisLike(this.estadoLike);
                    this.estadoLike = visualizacao.getGafanhoto().getEstadoLike();
                    break;
                default:
                    System.out.println("Opção Inválida. Por favor, escolha uma das opções");
                    break;
            }
        }
        visualizacaoDAO.salvarAvaliacao(idGafan, idVideo, visualizacao.getGafanhoto());
    }

    private int lerMenu(Scanner scanner) {
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
            int resposta = scanner.nextInt();
            scanner.nextLine();

            if (resposta < 1 || resposta > 5) {
                System.out.println("Opção inválida. Por favor escolha uma das opções");
                continue;
            }
            return resposta;
        }
    }

    public void validarNota() {
        if (nota > 0) {
            System.out.printf("%.2f Foi a nota que o Gafanhoto deu ao Vídeo%n", nota);
            System.out.println("Avaliar o Vídeo novamente, substituirá a nota anterior!");
            return;
        }
        System.out.println("O Gafanhoto ainda não avaliou o Vídeo");
    }

    public void validarEstadoLike() {
        if (estadoLike == 1) {
            System.out.println("O Gafanhoto deu like no Video");
            System.out.println("Dar dislike irá remover o like do Video");
            return;
        }

        if (estadoLike == 2) {
            System.out.println("O Gafanhoto deu dislike no Video");
            System.out.println("Dar like irá remover o dislike do Video");
            return;
        }
        System.out.println("O Gafanhoto ainda não deu like ou dislike no Video");

    }
}