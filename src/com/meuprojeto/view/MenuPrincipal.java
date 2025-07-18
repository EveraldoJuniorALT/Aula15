package com.meuprojeto.view;

import com.meuprojeto.service.ColetarDados;
import com.meuprojeto.service.ConsultarDados;
import com.meuprojeto.util.GerenciadorDeEntrada;

import java.util.Scanner;

public class MenuPrincipal {
    private final ColetarDados coletar;
    private final ConsultarDados consultar;

    public MenuPrincipal(ColetarDados coletar, ConsultarDados consultar) {
        this.coletar = coletar;
        this.consultar = consultar;
    }

    public void infoOpcoes() {
        Scanner scanner = GerenciadorDeEntrada.getScanner();
        while (true) {

            int resp = lerOpcaoInfoOpcoes(scanner);
            if (resp == 3) {
                System.out.println("Encerrando...");
                break;
            }

            switch (resp) {
                case 1:
                    menuAddInfo(scanner);
                    break;
                case 2:
                    menuConsultarInfo(scanner);
                    break;
                default:
                    System.out.println("Número inválido. Por favor, escolha uma das opções.");
                    break;
            }
        }
    }

    private int lerOpcaoInfoOpcoes(Scanner scanner) {
        while (true) {
            System.out.println("Escolha Uma Das Opções:");
            System.out.println("1. Adicionar Informações.");
            System.out.println("2. Consultar Informações.");
            System.out.println("3. Sair.");

            if (!scanner.hasNextInt()) {
                System.out.println("Valor inválido. Por favor digite apenas número");
                scanner.nextLine(); // Consome a entrada inválida
                continue;
            }
            int resposta = scanner.nextInt();
            scanner.nextLine();

            if (!(resposta > 0 && resposta <= 3)) {
                System.out.println("Escolha uma opção de 1 a 3.");
                continue;
            }
            return resposta;
        }
    }

    private void menuAddInfo(Scanner scanner) {
        while (true) {
            int resposta = lerOpcaoMenuAddInfo(scanner);

            if (resposta == 3) {
                break;
            }
            coletar.cadastro(resposta);
        }
    }

    private int lerOpcaoMenuAddInfo(Scanner scanner) {
        while (true) {
            System.out.println("Escolha Uma Opção:");
            System.out.println("1. Adicionar Gafanhoto.");
            System.out.println("2. Adicionar Video.");
            System.out.println("3. Voltar.");

            if (!scanner.hasNextInt()) {
                System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
                scanner.nextLine();
                continue;
            }

            int resposta = scanner.nextInt();
            scanner.nextLine();

            if (!(resposta > 0 && resposta <= 3)) {
                System.out.println("Escolha uma das opções de 1 a 3.");
                continue;
            }
            return resposta;
        }
    }

    private void menuConsultarInfo(Scanner scanner) {
        while (true) {
            int resposta = lerOpcaoMenuConsultarInfo(scanner);
            if (resposta == 3) {
                break;
            }
            consultar.consulta(resposta);
        }
    }

    private int lerOpcaoMenuConsultarInfo(Scanner scanner) {
        while (true) {
            System.out.println("Escolha Uma Opção");
            System.out.println("1. Consultar Gafanhoto.");
            System.out.println("2. Consultar Video.");
            System.out.println("3. Voltar.");

            if (!scanner.hasNextInt()) {
                System.out.println("Valor Inválido. Por favor, digite um número de 1 a 3.!");
                scanner.nextLine(); // Consome o dado inválido
                continue;
            }

            int resposta = scanner.nextInt();
            scanner.nextLine();

            if (!(resposta > 0 && resposta <= 3)) {
                System.out.println("Escolha uma opção de 1 a 3.");
                continue;
            }
            return resposta;
        }
    }
}
