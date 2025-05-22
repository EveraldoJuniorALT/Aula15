import java.util.Scanner;

public class ConsultarDados {
	private Repositorio repo = new Repositorio();
	private Assistir a = new Assistir();

	protected void consulta(int resp, Scanner scanner) {
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
					repo.requestGafan(respG);
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

	private int escolherGafan(Scanner scanner) {
		boolean entraValida = false;
		int respGafan = 0;
		int tamanho = repo.totalGDB();

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
		int resp = 0;

		while (true) {
			boolean entraValida = false;
			do {
				System.out.println("1. Mostrar dados.");
				System.out.println("2. Voltar.");

				if (scanner.hasNextInt()) { // Verifica se a entrada é Int
					resp = scanner.nextInt();
					scanner.nextLine(); // Consume a próxima linha deixa pelo enter
					entraValida = true; // Atribui o valor 'true' para sair do loop
				} else {
					System.out.println("Valor Inválido. Por favor, digite apenas números!");
					scanner.nextLine();
				}
			} while (!entraValida);

			if (resp == 2) {
				break;
			}

			switch (resp) {
			case 1:
				int respV = escolherVideo(scanner);
				if (respV != 0) {
					repo.requestVideo(respV);
				}
				break;
			default:
				System.out.println("Valor Inválido. Por favor, escolha uma das opções!");
				break;
			}
		}
	}

	private int escolherVideo(Scanner scanner) {
		boolean entraValida = false;
		int respVideo = 0;
		int tamanho = repo.totalVDB();

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
