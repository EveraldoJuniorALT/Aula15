package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;

import java.util.Scanner;

public class Visualizacao {
    private final Gafanhoto espectador;
    private final Video videos;

    public Visualizacao(Gafanhoto espectador, Video videos) {
        this.espectador = espectador;
        this.videos = videos;
    }

    public void avaliar() {
        this.videos.receberAvaliacao(5);
    }

    public void avaliar(Scanner scanner) {
        this.videos.receberAvaliacao(avaliarNota(scanner));
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

    public void darLike() {
        this.videos.like();
    }

    public void darDisLike() {
        this.videos.dislike();
    }
}
