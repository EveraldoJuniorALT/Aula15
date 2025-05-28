package com.meuprojeto.service;

import com.meuprojeto.repository.InRepositorio;
import com.meuprojeto.repository.Repositorio;

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
		int resp = 0; // Inicializa a variável

		while (true) {
			boolean entraValida = false;
			do {
				System.out.println("1. Mostrar dados.");
				System.out.println("2. Assistir Video.");
				System.out.println("3. Voltar. ");

				if (scanner.hasNextInt()) { // Verifica se a entrada é Int
					resp = scanner.nextInt();
					scanner.nextLine(); // Consume a próxima linha deixa pelo enter
					entraValida = true; // Atribui o valor 'true' para sair do loop
				} else {
					System.out.println("Valor Inválido. Por favor, digite apenas números!");
					scanner.nextLine();
				}
			} while (!entraValida);

			if (resp == 3) {
				break;
			}

			switch (resp) {
			case 1:
				int respG = escolherGafan(scanner); // Chama o método e armazena a escolha do gafanhoto na variável
				if (respG != 0) {
					repositorio.requestGafanhoto(respG);
				}
				break;
			case 2:
				int gafan = escolherGafan(scanner);
				int video = escolherVideo(scanner);
				if (gafan != 0 && video != 0) {
					a.conectarGafanVideo(gafan, video, scanner);
				}
				break;
			default:
				System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
				break;
			}
		}
	}

	// private int lerOpcaoMenuGafanhoto(Scanner scanner) {}

	private int escolherGafan(Scanner scanner) {
		boolean entraValida = false;
		int respGafan = 0;
		int tamanho = repositorio.getTotalGafanhotos();

		do {
			if (tamanho == 0) {
				System.out.println("Não existe gafanhotos registrados!");
				break;
			}

			int j = 1;
			for (int i = 1; i <= tamanho; i++) {
				System.out.println(i + " Para o " + j + "º Gafanhoto");
				j++;
			}

			if (scanner.hasNextInt()) { // Verifica se a entrada é Int
				respGafan = scanner.nextInt();
				scanner.nextLine(); // Consome a próxima linha deixada pelo enter
				if (respGafan > 0 && respGafan <= tamanho) {
					entraValida = true; // Atribui o valor 'true' para sair do loop
				} else {
					System.out.println("O números não pode ser negativo e nem maior que a quatidade de registros!");
				}
			} else {
				System.out.println("Entrada Inválida. Por favor, digite apenas números!");
				scanner.nextLine(); // Consome a entrada inválida
			}
		} while (!entraValida);
		return respGafan;
	}
	
	private void consultarVideo(Scanner scanner) {
		while (true) {

			int resp = lerOpcaoMenuVideo(scanner); // Recebe obrigatoriamente um inteiro do método
			if (resp == 2) {
				break; // Sai do loop e volta ao menu anterior
			}

			switch (resp) {
			case 1:
				int respV = escolherVideo(scanner);
				if (respV != 0) {
					repositorio.requestVideo(respV);
				}
				break;
			default:
				System.out.println("Opção Inválida. Por favor, escolha uma das opções!");
				break;
			}
		}
	}

	/*
	 * Mostra o menu ao usuário
	 * Verifica o tipo de dado da entrada
	 * Caso seja 'inteiro' retorna o valor
	 */
	private int lerOpcaoMenuVideo(Scanner scanner) {
		while (true) { // Loop que só pode ser quebrado pelo 'return'

			System.out.println("1. Mostrar dados.");
			System.out.println("2. Voltar.");

			if (!scanner.hasNextInt()) { // Verifica se a entrada não é um Int
				System.out.println("Valor Inválido. Por favor, digite apenas números!");
				scanner.nextLine(); // Consome a entrada inválida
				continue; // Repete a entrada até que o seja o tipo esperado
			}

			int resposta = scanner.nextInt();
			scanner.nextLine(); // Consume a próxima linha deixa pelo enter
			return resposta;
		}
	}

	private int escolherVideo(Scanner scanner) {
		boolean entraValida = false;
		int respVideo = 0;
		int tamanho = repositorio.getTotalVideos();

		do {
			if (tamanho == 0) {
				System.out.println("Não existe vídeos registrados!");
				break;
			} else {
				int j = 1;
				for (int i = 1; i <= tamanho; i++) {
					System.out.println(i + " Para o " + j + "º Video");
					j++;
				}

				if (scanner.hasNextInt()) {
					respVideo = scanner.nextInt();
					scanner.nextLine();
					if (respVideo > 0 && respVideo <= tamanho) {
						entraValida = true;
					} else {
						System.out.println("O número não pode ser negativo e nem maior que a quatidade de registros!");
					}
				} else {
					System.out.println("Entrada Inválida. Por favor, digite apenas números!");
					scanner.nextLine(); // Consome a entrada inválida
				}
			}

		} while (!entraValida);
		return respVideo;
	}
}
