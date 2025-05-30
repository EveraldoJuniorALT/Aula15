package com.meuprojeto.view;

import com.meuprojeto.service.ColetarDados;
import com.meuprojeto.service.ConsultarDados;

import java.util.Scanner;

public class MenuPrincipal {
    private final ColetarDados coletar;
    private final ConsultarDados consultar;

    public MenuPrincipal(ColetarDados coletar, ConsultarDados consultar) {
        this.coletar = coletar;
        this.consultar = consultar;
    }

    public void infoOpcoes() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                int resp = lerOpcaoInfoOpcoes(scanner); // Recebe obrigatoriamente um valor de 1 a 3
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
    }

    private int lerOpcaoInfoOpcoes(Scanner scanner) {
        while (true) {
            System.out.println("Escolha Uma Das Opções:");
            System.out.println("1. Adicionar Informações.");
            System.out.println("2. Consultar Informações.");
            System.out.println("3. Sair.");

            if (!scanner.hasNextInt()) { // Inverte o valor da expressão
                System.out.println("Valor inválido. Por favor digite apenas número");
                scanner.nextLine(); // Consome a entrada inválida
                continue; // Repete o 'loop' até que a entrada seja um inteiro
            }
            int resposta = scanner.nextInt();
            scanner.nextLine(); //Consome a próxima linha deixa pelo 'enter'

            if (!(resposta > 0 && resposta <= 3)) {
                System.out.println("Escolha uma opção de 1 a 3.");
                continue; // Repete o 'loop' até que a 'resposta' esteja de 1 a 3
            }
            return resposta;
        }
    }

    private void menuAddInfo(Scanner scanner) {
        while (true) {
            int resp = lerOpcaoMenuAddInfo(scanner); // Recebe obrigatoriamente um valor inteiro de 1 a 3

            if (resp == 3) {
                break;
            }

            /*
            Como o valor da variável 'resp' só pode ser um valor de 1 a 3
            Só preciso verificar quando 'resp' é igual a 3 para sair do 'loop'
            Chamo o meu método e o valor de 'resp' é quem vai definir qual método será executado
             */
            coletar.cadastro(resp, scanner);
        }
    }

    private int lerOpcaoMenuAddInfo(Scanner scanner) {
        while (true) {
            System.out.println("Escolha Uma Opção:");
            System.out.println("1. Adicionar Gafanhoto.");
            System.out.println("2. Adicionar Video.");
            System.out.println("3. Voltar.");

            if (!scanner.hasNextInt()) { // Inverte o valor da expressão
                System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
                scanner.nextLine(); // Consome a entrada Inválida
                continue; // Repete o 'loop' até que a entrada seja um inteiro
            }

            int resposta = scanner.nextInt();
            scanner.nextLine(); // Consome a próxima linha deixa pelo enter

            if (!(resposta > 0 && resposta <= 3)) {
                System.out.println("Escolha uma das opções de 1 a 3.");
                continue; // Caso a resposta não seja um valor de 1 a 3, repete o 'loop'
            }
            return resposta; // Saí do 'loop' retornando o valor
        }
    }

    private void menuConsultarInfo(Scanner scanner) {
        while (true) {
            int resp = lerOpcaoMenuConsultarInfo(scanner); // Recebe obrigatoriamente um valor de 1 a 3
            if (resp == 3) {
                break; // Encerra o loop e volta para o método anterior
            }

            /*
            A variável 'resp' recebe um valor de 1 a 3
            Não é necessário verificar qual valor será passado para chamar o método
            O método 'consulta' verifica qual método será executado a partir do valor que é 1 ou 2
             */
            consultar.consulta(resp, scanner);
        }
    }

    private int lerOpcaoMenuConsultarInfo(Scanner scanner) {
        while (true) {
            System.out.println("Escolha Uma Opção");
            System.out.println("1. Consultar Gafanhoto.");
            System.out.println("2. Consultar Video.");
            System.out.println("3. Voltar.");

            if (!scanner.hasNextInt()) { // Verifica se o dado é Int
                System.out.println("Valor Inválido. Por favor, digite um número de 1 a 3.!");
                scanner.nextLine(); // Consome o dado inválido
                continue; // Repete 'loop' até que a entrada seja um inteiro
            }

            int resposta = scanner.nextInt(); // Atribui o dado a variável
            scanner.nextLine(); // Consome a linha deixada pelo enter

            if (!(resposta > 0 && resposta <= 3)) {
                System.out.println("Escolha uma opção de 1 a 3.");
                continue; // Repete o 'loop' até que uma das opções seja escolhida
            }
            return resposta; // Saí do 'loop' retornando o valor
        }
    }
}
