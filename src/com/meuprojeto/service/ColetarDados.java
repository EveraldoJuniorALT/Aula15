package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;
import com.meuprojeto.repository.InRepositorio;
import com.meuprojeto.util.GerenciadorDeEntrada;

import java.util.Random;
import java.util.Scanner;

public class ColetarDados {
    private final InRepositorio repositorio;
    private final Random random = new Random();

    public ColetarDados(InRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastro(int resposta) {
        Scanner scanner = GerenciadorDeEntrada.getScanner();
        if (resposta == 1) {
            cadastrarGafanhoto(scanner);
        }
        if (resposta == 2) {
            cadastrarVideo(scanner);
        }
    }

    private void cadastrarGafanhoto(Scanner scanner) {
        String nome = pegarNome(scanner);

        String sexo = pegarSexo(scanner);

        int idade = pegarIdade(scanner);

        String login = gerarLogin(); // Chama o método gear login e armazena nessa variável

        Gafanhoto gafan = new Gafanhoto(nome, sexo, idade, login);
        repositorio.saveGafanhotoDB(gafan);
    }

    private int pegarIdade(Scanner scanner) {
        /*
        Sim, eu sei que não devo usar a idade em um projeto
        Mas esse aqui é apenas meu primeiro projeto com Banco de Dados
        Sei que o correto é usar a data de nascimento
         */
        while (true) {
            System.out.print("Digite sua Idade: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine();
                continue;
            }
            int idade = scanner.nextInt();
            scanner.nextLine();

            if (idade < 18 || idade > 110) {
                System.out.println("Idade inválida. Tente novamente!");
                scanner.nextLine();
                continue;
            }
            return idade;
        }
    }

    private String pegarSexo(Scanner scanner) {
        while (true) {
            System.out.print("Digite seu Sexo: [M/F] ");
            String sexo = scanner.nextLine().trim();

            if (!sexo.matches("[a-zA-ZÀ-ÿ' ]+")) {
                System.out.println("Valor Inválido. Por favor, digite apenas letras!");
                continue;
            }
            if (!(sexo.equalsIgnoreCase("m") || sexo.equalsIgnoreCase("f"))) {
                System.out.println("Entrava Inválida. Digite [M] para masculino ou [F] para feminino");
                continue;
            }
            return sexo;
        }
    }

    /*
    Coleta o nome e verifica se atende aos parâmetros necessários
    Caso contrário, repte o 'loop' até que os parâmetros sejam atendidos
     */
    private String pegarNome(Scanner scanner) {
        while (true) {
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine().trim();

            if (!nome.matches("[a-zA-ZÀ-ÿ' ]+")) {
                System.out.println("Valor Inválido. Por favor, digite apenas letras!");
                continue;
            }

            if (nome.length() < 5) {
                System.out.println("Digite seu nome e sobrenome!");
                continue;
            }
            return nome;
        }
    }

    /*
    Usa a classe 'random' para gerar os números aleatórios da matrícula
    Usa a classe 'StringBuilder' para construir uma 'String' que é o 'login'
    Retorna o 'login' um conjunto de 6 números aleatórios
     */
    private String gerarLogin() {
        StringBuilder login = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int numAleatorio = this.random.nextInt(10);
            login.append(numAleatorio);
        }
        return login.toString();
    }

    private void cadastrarVideo(Scanner scanner) {
        boolean entraValida = false;
        String titulo;
        do {
            System.out.print("Digite o nome do Video: ");
            titulo = scanner.nextLine();

            if (!titulo.matches("[a-zA-ZÀ-ÿ' ]+")) {
                System.out.println("Valor Inválido. Por favor, digite apenas letras!");
                continue;
            }

            if (titulo.length() < 4) {
                System.out.println("Nome muito curto");
                continue;
            }
            entraValida = true;
        } while (!entraValida);
        Video video = new Video(titulo);
        repositorio.saveVideoDB(video);
    }
}
