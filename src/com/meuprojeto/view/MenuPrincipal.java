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
		try(Scanner scanner = new Scanner(System.in)){
			int resp = 0;
			
			while (true) {
				boolean entraValida = false;
				
				do {
					System.out.println("Escolha Uma Das Opções:");
					System.out.println("1. Adicionar Informações.");
					System.out.println("2. Consultar Informações.");
					System.out.println("3. Sair.");
					
					if(scanner.hasNextInt()) { //Verifica se o número é int
						resp = scanner.nextInt();
						scanner.nextLine(); //Consome a próxima linha deixa pelo 'enter'
						entraValida = true;
					} else {
						System.out.println("Valor inválido. Por favor digite apenas número");
						scanner.nextLine();
					}
				} while (!entraValida);
				
				if(resp == 3) {
					System.out.println("Encerrando...");
					break;
				}
				
				switch(resp) {
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
	
	private void menuAddInfo(Scanner scanner) {
		int resp = 0;
		
		while(true) {
			boolean entraValida = false;
			do {
				System.out.println("Escolha Uma Opção:");
				System.out.println("1. Adicionar Gafanhoto.");
				System.out.println("2. Adicionar Video.");
				System.out.println("3. Voltar.");
				
				if(scanner.hasNextInt()) { // Verifica se o número é int
					resp = scanner.nextInt();
					scanner.nextLine(); // Consome a próxima linha deixa pelo enter
					entraValida = true; // Recebe o valor 'true' para sair do loop
				} else {
					System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
					scanner.nextLine(); // Consome a entrada Inválida
				}
			} while(!entraValida);
			
			if(resp == 3) {
				break;
			}
			
			switch(resp) {
			case 1:
				coletar.cadastro(resp, scanner);
				break;
			case 2:
				coletar.cadastro(resp, scanner);
				break;
			default:
				System.out.println("Opção Inválida. Tente novamente!");
				break;
			}
		}
	}
	
	private void menuConsultarInfo(Scanner scanner) {
		int resp = 0;
		
		while(true) {
			boolean entraValida = false;
			do {
				System.out.println("Escolha Uma Opção");
				System.out.println("1. Consultar Gafanhoto.");
				System.out.println("2. Consultar Video.");
				System.out.println("3. Voltar.");
				
				if(scanner.hasNextInt()) { // Verifica se o dado é Int
					resp = scanner.nextInt(); // Atribui o dado a variável
					scanner.nextLine(); // Consome a linha deixada pelo enter
					entraValida = true; // Atribui o valor 'true' para sair do loop
				} else {
					System.out.println("Valor Inválido. Por favor, Digite escolha uma das opções!");
					scanner.nextLine(); // Consome o dado inválido
				}
			} while(!entraValida);
			
			if(resp == 3) {
				break; // Encerra o loop e volta para o método anterior
			}
			
			switch(resp) {
			case 1:
				consultar.consulta(resp, scanner);
				break;
			case 2:
				consultar.consulta(resp, scanner);
				break;
			default:
				System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
				break;
			}
		}
	}
 }
