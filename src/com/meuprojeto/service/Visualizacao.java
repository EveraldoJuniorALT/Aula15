package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;

import java.util.Scanner;

public class Visualizacao {
    private final Gafanhoto gafanhoto;
    private final Video video;

    public Visualizacao(Video video, Gafanhoto gafan) {
        this.gafanhoto = gafan;
        this.video = video;
    }

    public void avaliarPelaPrimeiraVez() {
        this.video.receberNovaAvaliacao(5.0);
        this.gafanhoto.guardarNotaDaAvalicao(5.0);
    }

    public void avaliarPelaPrimeiraVez(Scanner scanner) {
        double notaPessoal = avaliarNota(scanner);
        this.video.receberNovaAvaliacao(notaPessoal);
        this.gafanhoto.guardarNotaDaAvalicao(notaPessoal);
    }

    public void atualizarAvaliacaoExistente(Double notaAntiga) {
        this.video.atualizarAvaliacao(notaAntiga, 5.0);
        this.gafanhoto.guardarNotaDaAvalicao(5.0);
    }

    public void atualizarAvaliacaoExistente(Double notaAntiga, Scanner scanner) {
        double notaPessoal = avaliarNota(scanner);
        this.video.atualizarAvaliacao(notaAntiga, notaPessoal);
        this.gafanhoto.guardarNotaDaAvalicao(notaPessoal);
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

    public void darLike(int estadoLike) {
        switch (estadoLike) {
            case 1:
                System.out.println("You already liked ");
                break;
            case 2:
                this.video.removerDislike();
                this.video.like();
                this.gafanhoto.guardarEstadoLike(1);
                break;
            default:
                this.video.like();
                this.gafanhoto.guardarEstadoLike(1);
                break;
        }
    }

    public void darDisLike(int estadoLike) {
        switch (estadoLike) {
            case 1:
                this.video.removerLike();
                this.video.dislike();
                this.gafanhoto.guardarEstadoLike(2);
                break;
            case 2:
                System.out.println("You already disliked ");
                break;
            default:
                this.video.dislike();
                this.gafanhoto.guardarEstadoLike(2);
                break;
        }
    }

    public Video getVideo() {
        return this.video;
    }

    public Gafanhoto getGafanhoto() {
        return this.gafanhoto;
    }
}
