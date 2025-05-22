import java.util.Random;
import java.util.Scanner;

public class ColetarDados {
	private Repositorio repo = new Repositorio();

	protected void cadastro(int escolha, Scanner scanner) {
		if (escolha == 1) {
			cadastrarGafan(scanner);
		} else {
			cadastrarVideo(scanner);
		}
	}

	private void cadastrarGafan(Scanner scanner) {
		boolean entraValida = false; // Variável de controle do do-while
		String nome;

		do {
			System.out.print("Digite seu nome: ");
			nome = scanner.nextLine().trim();

			if (nome.matches("[a-zA-ZÀ-ÿ' ]+")) {
				entraValida = true;
			} else {
				System.out.println("Valor Inválido. Por favor, digite apenas letras!");
			}

		} while (!entraValida);

		boolean entraValida1 = false;
		String sexo;

		do {
			System.out.print("Digite seu Sexo: [M/F] ");
			sexo = scanner.nextLine().trim();

			if (sexo.matches("[a-zA-ZÀ-ÿ' ]+")) {
				if (sexo.equalsIgnoreCase("masculino") || sexo.equalsIgnoreCase("m")) {
					entraValida1 = true;
				} else if (sexo.equalsIgnoreCase("feminino") || sexo.equalsIgnoreCase("f")) {
					entraValida1 = true;
				} else {
					System.out.println("Entrava Inválida. Digite [M] para masculino ou [F] para feminino");
				}
			} else {
				System.out.println("Valor Inválido. Por favor, digite apenas letras!");
			}
		} while (!entraValida1);

		boolean entraValida2 = false;
		int idade = 0; // Inicializa a variável

		do {
			System.out.print("Digite sua Idade: ");
			if (scanner.hasNextInt()) { // Verifica se a entrada é um Int e se o valor dentro da variável é maior que 0
				idade = scanner.nextInt(); // lê o número inteiro
				scanner.nextLine(); // Consome a próxima linha deixada pelo enter
				entraValida2 = true; // Atribui o valor 'true' para o laço do-while encerrar
			} else {
				System.out.println("Valor Inválido. Por favor, digite apenas números!");
				scanner.nextLine(); // Consome a entrada Inválida
			}
		} while (!entraValida2);

		String login = gerarLogin(); // Chama o método gear login e armazena nessa variável

		Gafanhoto gafan = new Gafanhoto(nome, sexo, idade, login);
		repo.saveGafanDB(gafan);
	}

	private String gerarLogin() {
		Random random = new Random();
		StringBuilder login = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			int numAleatorio = random.nextInt(10);
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
			
			if(titulo.matches("[a-zA-ZÀ-ÿ' ]+")) {
				entraValida = true;
			} else {
				System.out.println("Valor Inválido. Por favor, digite apenas letras!");
			}
		} while(!entraValida);
		Video video = new Video(titulo);
		repo.saveVidDB(video);
	}
}
