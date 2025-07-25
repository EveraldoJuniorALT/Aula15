package com.meuprojeto.service;

import com.meuprojeto.repository.InRepositorio;
import com.meuprojeto.util.GerenciadorDeEntrada;

import java.util.Scanner;

public class ConsultarDados {
    private final InRepositorio repositorio;
    private final AssistirDB assistirDB;

    public ConsultarDados(InRepositorio repositorio) {
        this.repositorio = repositorio;
        this.assistirDB = new AssistirDB(this.repositorio);
    }

    public void consulta(int resposta) {
        Scanner scanner = GerenciadorDeEntrada.getScanner();
        if (resposta == 1) {
            consultarGafanhoto(scanner);
        }
        if (resposta == 2) {
            consultarVideo(scanner);
        }
    }

    private void consultarGafanhoto(Scanner scanner) {
        while (true) {
            int resposta = lerOpcaoMenuGafanhoto(scanner);
            if (resposta == 3) {
                break;
            }

            switch (resposta) {
                case 1:
                    int respGafanhoto = escolherGafanhoto(scanner);
                    if (respGafanhoto == 0) {
                        System.out.println("Voce precisa adicionar um gafanhoto");
                    }
                    repositorio.requestGafanhoto(respGafanhoto);
                    break;
                case 2:
                    int idGafanhoto = escolherGafanhoto(scanner);
                    int idVideo = escolherVideo(scanner);

                    assistirDB.conectarObjetos(idGafanhoto, idVideo);
                    break;
                default:
                    System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
                    break;
            }
        }
    }

    private int lerOpcaoMenuGafanhoto(Scanner scanner) {
        while (true) {
            System.out.println("1. Mostrar dados.");
            System.out.println("2. Assistir Video.");
            System.out.println("3. Voltar. ");

            if (!scanner.hasNextInt()) {
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine();
                continue;
            }

            int resposta = scanner.nextInt();
            scanner.nextLine();
            if (resposta < 1 || resposta > 3) {
                System.out.println("Escolha uma das opções");
                continue;
            }
            return resposta;
        }
    }

    private int escolherGafanhoto(Scanner scanner) {
        int respGafan = 0;
        boolean entraValida = false;
        do {
            int tamanho = repositorio.getTotalGafanhotos();
            if (tamanho == 0) {
                System.out.println("Não existe gafanhotos registrados!");
                break;
            }

            int j = 1;
            for (int i = 1; i <= tamanho; i++) {
                System.out.printf("%d Para o %dº Gafanhoto%n", i, j);
                j++;
            }

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada Inválida. Por favor, digite apenas números!");
                scanner.nextLine();
                continue;
            }

            respGafan = scanner.nextInt();
            scanner.nextLine();

            if (respGafan < 1 || respGafan > tamanho) {
                System.out.println("O números não pode ser negativo e nem maior que ass quatidade de registros!");
                continue;
            }

            entraValida = true;
        } while (!entraValida);
        return respGafan;
    }

    private void consultarVideo(Scanner scanner) {
        while (true) {

            int resposta = lerOpcaoMenuVideo(scanner);
            if (resposta == 2) {
                break;
            }

            int respVideo = escolherVideo(scanner);
            repositorio.requestVideo(respVideo);
        }
    }

    private int lerOpcaoMenuVideo(Scanner scanner) {
        while (true) {

            System.out.println("1. Mostrar dados.");
            System.out.println("2. Voltar.");

            if (!scanner.hasNextInt()) {
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine();
                continue;
            }

            int resposta = scanner.nextInt();
            scanner.nextLine();
            if (resposta < 1 || resposta > 2) {
                System.out.println("Escolha uma das opções");
                continue;
            }
            return resposta;
        }
    }

    private int escolherVideo(Scanner scanner) {
        int respostaVideo = 0;
        boolean entradaValida = false;
        do {
            int tamanho = repositorio.getTotalVideos();
            if (tamanho == 0) {
                System.out.println("Não existe vídeos registrados!");
                break;
            }

            int j = 1;
            for (int i = 1; i <= tamanho; i++) {
                System.out.printf("%d Para o %dº Video%n", i, j);
                j++;
            }

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada Inválida. Por favor, digite apenas números!");
                scanner.nextLine();
                continue;
            }
            respostaVideo = scanner.nextInt();
            scanner.nextLine();

            if (respostaVideo < 1 || respostaVideo > tamanho) {
                System.out.println("O número não pode ser negativo e nem maior que ass quatidade de registros!");
                continue;
            }

            entradaValida = true;
        } while (!entradaValida);
        return respostaVideo;
    }
}
