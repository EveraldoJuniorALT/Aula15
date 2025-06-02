package com.meuprojeto.service;

import com.meuprojeto.repository.InRepositorio;

import java.util.Scanner;

public class ConsultarDados {
    private final InRepositorio repositorio;
    private final Assistir a;

    public ConsultarDados(InRepositorio repositorio) {
        this.repositorio = repositorio;
        this.a = new Assistir(this.repositorio);
    }

    public void consulta(int resp, Scanner scanner) {
        if (resp == 1) {
            consultarGafanhoto(scanner);
        } else {
            consultarVideo(scanner);
        }
    }

    private void consultarGafanhoto(Scanner scanner) {
        while (true) {
            int resp = lerOpcaoMenuGafanhoto(scanner); // Recebe obrigatoriamente um inteiro
            if (resp == 3) {
                break;
            }

            switch (resp) {
                case 1:
                    int respGafanhoto = escolherGafanhoto(scanner); // Chama o método e armazena a escolha do gafanhoto na variável
                    if (respGafanhoto == 0) {
                        System.out.println("Voce precisa adicionar um gafanhoto");
                    }
                    repositorio.requestGafanhoto(respGafanhoto);
                    break;
                case 2:
                    int gafanhoto = escolherGafanhoto(scanner);
                    int video = escolherVideo(scanner);
                    if (gafanhoto == 0 && video == 0) {
                        System.out.println("Você precisa adicionar um gafanhoto ou um video");
                    }
                    a.conectarObjeto(gafanhoto, video, scanner);
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

            if (!scanner.hasNextInt()) { // Inverte o valor da expressão
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine(); // Consume a próxima linha deixa pelo enter
                continue; // Repete o 'loop' até que seja o tipo esperado
            }

            int resposta = scanner.nextInt();
            scanner.nextLine(); // Consome a próxima linha deixada pelo enter
            if (!(resposta >= 1 && resposta <= 3)) {
                System.out.println("Escolha uma das opções");
                continue; //Repete o 'loop' até que uma opção seja escolhida
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

            if (!scanner.hasNextInt()) { // Verifica se a entrada é Int
                System.out.println("Entrada Inválida. Por favor, digite apenas números!");
                scanner.nextLine(); // Consome a entrada inválida
            }

            respGafan = scanner.nextInt();
            scanner.nextLine(); // Consome a próxima linha deixada pelo enter

            if (!(respGafan > 0 && respGafan <= tamanho)) {
                System.out.println("O números não pode ser negativo e nem maior que a quatidade de registros!");
                continue; // Repete o 'loop' até que a seja escolhida a opção correta
            }

            entraValida = true; // Atribui o valor 'true' para sair do loop
        } while (!entraValida); // Se a opção correta for escolhida o 'loop' do-while se encerra
        return respGafan;
    }

    private void consultarVideo(Scanner scanner) {
        while (true) {

            int resp = lerOpcaoMenuVideo(scanner); // Recebe obrigatoriamente um inteiro
            if (resp == 2) {
                break; // Sai do loop e volta ao menu anterior
            }

            int respV = escolherVideo(scanner);
            repositorio.requestVideo(respV);

        }
    }

    /*
     * Mostra o menu ao 'user'
     * Verifica o tipo de dado da entrada
     * Caso seja 'inteiro' retorna o valor
     */
    private int lerOpcaoMenuVideo(Scanner scanner) {
        while (true) { // 'Loop' que só pode ser quebrado pelo 'return'

            System.out.println("1. Mostrar dados.");
            System.out.println("2. Voltar.");

            if (!scanner.hasNextInt()) { // Invente o valor da expressão
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine(); // Consome a entrada inválida
                continue; // Repete o 'loop' até que o seja o tipo esperado
            }

            int resposta = scanner.nextInt();
            scanner.nextLine(); // Consume a próxima linha deixada pelo enter
            if (!(resposta >= 1 && resposta <= 2)) {
                System.out.println("Escolha uma das opções");
                continue; // Repete o 'loop' até que uma das opções sejam escolhidas
            }
            return resposta;
        }
    }

    private int escolherVideo(Scanner scanner) {
        int respostaVideo = 0;
        boolean entradaValida = false; // Variável de controle do loop do-while
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
                scanner.nextLine(); // Consome a entrada inválida
                continue; // Repete o 'loop' até que seja a entrada esperada
            }
            respostaVideo = scanner.nextInt();
            scanner.nextLine();

            if (!(respostaVideo > 0 && respostaVideo <= tamanho)) {
                System.out.println("O número não pode ser negativo e nem maior que a quatidade de registros!");
                continue; // Repete o 'loop' até que seja a entrada esperada
            }

            entradaValida = true;
        } while (!entradaValida); // Se uma opção corretar for escolhida, o 'loop' do-while se encerra
        return respostaVideo;
    }
}
