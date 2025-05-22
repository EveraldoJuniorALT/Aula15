import java.util.Scanner;

public class Assistir {
	private Repositorio repo = new Repositorio();
	private Visualizacao visu;

	public void conectarGafanVideo(int Gafan, int Video, Scanner scanner) {
		Gafanhoto g = repo.getGafanhotos(Gafan);
		Video v = repo.getVideos(Video);
		
		if(g == null || v == null) {
			System.out.println("Erro: Gafanhoto ou Video não encontrado");
			return;
		}
		visu = new Visualizacao(g, v);

		int resp = 0;
		while (true) {
			boolean entraValida = false;
			do {
				System.out.println("1. Avaliar Video.");
				System.out.println("2. Avaliar Com Sua Nota.");
				System.out.println("3. Marcar Com Gostei.");
				System.out.println("4. Marcar Com Não Gostei.");
				System.out.println("5. Voltar.");

				if (scanner.hasNextInt()) {
					resp = scanner.nextInt();
					scanner.nextLine();
					entraValida = true;
				} else {
					System.out.println("Valor Inválido. Por favor, Digite apenas números!");
					scanner.nextLine();
				}
			} while (!entraValida);

			if (resp == 5) {
				repo.updateGafan(Gafan);
				repo.updateVideo(Video);
				break;
			}

			switch (resp) {
			case 1:
				visu.avaliar();
				break;
			case 2:
				int nota = avaliarNota(scanner);
				visu.avaliar(nota);
				break;
			case 3:
				visu.darLike();
				break;
			case 4:
				visu.darDisLike();
				break;
			default:
				System.out.println("Opção Inválida. Por favor, escolha uma das opções");
				break;
			}
		}
	}

	private int avaliarNota(Scanner scanner) {
		int nota = 0;
		boolean entraValida = false;
		do {
			System.out.print("Avalie o vídeo Nota[1 a 5]: ");
			if (scanner.hasNextInt()) {
				nota = scanner.nextInt();
				scanner.nextLine();
				if(nota > 0 && nota <= 5) {
					entraValida = true;
				} else {
					System.out.println("A nota tem que maior que 0 e menor ou igual a 5!");
				}
			} else {
				System.out.println("Valor Inválido. Por favor, digite apenas números!");
				scanner.nextLine();
			}
		} while (!entraValida);
		return nota;
	}
}
