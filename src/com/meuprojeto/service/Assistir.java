package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;
import com.meuprojeto.model.Visualizacao;
import com.meuprojeto.repository.InRepositorio;

import java.util.Scanner;

public class Assistir {
    private final InRepositorio repositorio;

    public Assistir(InRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void conectarObjeto(int idGafan, int idVideo, Scanner scanner) {
        Gafanhoto g = repositorio.getGafanhotos(idGafan);
        Video v = repositorio.getVideos(idVideo);

        conectarGafanhotoVideo(g, v, idGafan, idVideo, scanner);
    }

    private void conectarGafanhotoVideo(Gafanhoto g, Video v, int idGafan, int idVideo, Scanner scanner) {
        if (g == null || v == null) {
            System.out.println("Erro: Gafanhoto ou Video não encontrado");
            return;
        }
        Visualizacao visualizacao = new Visualizacao(g, v);
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
                    double nota = avaliarNota(scanner);
                    visualizacao.avaliar(nota);
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

    private double avaliarNota(Scanner scanner) {
        while (true) {
            System.out.print("Avalie o vídeo Nota[1 a 5]: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine();
                continue;
            }

            double nota = scanner.nextDouble();
            scanner.nextLine();

            if (nota < 1 || nota > 5) {
                System.out.println("A nota tem que maior que 0 e menor ou igual a 5!");
                continue;
            }
            return nota;
        }
    }
}
