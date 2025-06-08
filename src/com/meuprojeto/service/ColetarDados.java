package com.meuprojeto.service;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;
import com.meuprojeto.repository.InRepositorio;

import java.util.Random;
import java.util.Scanner;

public class ColetarDados {
    private final InRepositorio repositorio;
    private final Random random = new Random();

    public ColetarDados(InRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastro(int escolha, Scanner scanner) {
        if (escolha == 1) {
            cadastrarGafanhoto(scanner);
        } else {
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
            if (!scanner.hasNextInt()) { // Inverte o valor da expressão, caso não seja um inteiro, entra no IF
                System.out.println("Valor Inválido. Por favor, digite apenas números!");
                scanner.nextLine(); // Consome a entrada Inválida
                continue; // Repete o 'loop' até que a entrada seja um inteiro
            }
            int idade = scanner.nextInt(); // lê o número inteiro
            scanner.nextLine(); // Consome a próxima linha deixada pelo enter

            if (idade < 18 || idade > 110) {
                System.out.println("Idade inválida. Tente novamente!");
                scanner.nextLine(); // Consome a entrada Inválida
                continue; // Repete o 'loop'
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
                continue; // Repete o 'loop' até que a entrada seja uma 'string'
            }
            if (!(sexo.equalsIgnoreCase("m") || sexo.equalsIgnoreCase("f"))) {
                System.out.println("Entrava Inválida. Digite [M] para masculino ou [F] para feminino");
                continue; // Repete o 'loop' até que a entrada seja um sexo valido
            }
            return sexo; // Saí do 'loop' retornando o valor da entrada
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
                continue; // Repete o 'loop' até que a entrada seja um nome valido
            }

            if (nome.length() < 5) {
                System.out.println("Digite seu nome e sobrenome!");
                continue; // Repete o 'loop' até que o nome digitado seja maior que 5 carateres
            }
            return nome; // Saí do 'loop' retornando o valor da entrada
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

            if(titulo.length() < 4) {
                System.out.println("Nome muito curto");
                continue;
            }
            entraValida = true;
        } while (!entraValida);
        Video video = new Video(titulo);
        repositorio.saveVideoDB(video);
    }
}
