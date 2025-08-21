import java.util.Scanner;

public class ACMETrade {
	private Scanner entrada;
	private Convencao convencao;
	private Federacao federacao;

	public ACMETrade() {
		entrada = new Scanner(System.in);
		convencao = new Convencao();
		federacao = new Federacao();
	}

	public void executar() {
		while (true) {
			mostrarMenu();

			System.out.print("\nDigite a opção: ");
			int opc = entrada.nextInt();
			entrada.nextLine();

			System.out.println();

			switch (opc) {
				case 0:
					System.out.println("[Sistema encerrado]");
					System.exit(0);
				case 1:
					cadastrarPais();
					break;
				case 2:

					break;
				default:
					break;
			}
		}
	}

	private void mostrarMenu() {
		System.out.println("=-=-=-=-= MENU =-=-=-=-=\n");

		System.out.println("[0] - Sair");
		System.out.println("[1] - Cadastrar país");
		System.out.println("[2] - Cadastrar acordo");
		System.out.println("[3] - Consultar país pela sigla");
		System.out.println("[4] - Consultar acordo pelo código");
		System.out.println("[5] - Consultar acordo pela sigla do comprador");
		System.out.println("[6] - Mudar nome de um país");
		System.out.println("[7] - Remover acordos de um determinado país comprador");
		System.out.println("[8] - Listar todos os acordos");
		System.out.println("[9] - Listar todos os países vendedores");
	}

	private void cadastrarPais() {
		System.out.print("Digite o nome do país: ");
		String nome = entrada.nextLine();

		System.out.println();

		System.out.print("Digite a sigla do país: ");
		String sigla = entrada.nextLine();

		System.out.println();

		Pais p = new Pais(sigla, nome);

		if (federacao.adicionarPais(p)) {
			System.out.println("1:" + sigla + ";" + nome);
		} else {
			System.out.println("1:erro sigla repetida");
		}

	}

	private void cadastrarAcordo(){
		System.out.print("Digite o nome do país: ");
		String nome = entrada.nextLine();

		System.out.println();

		System.out.print("Digite a sigla do país: ");
		String sigla = entrada.nextLine();

		System.out.println();

		Pais p = new Pais(sigla, nome);

		if (federacao.adicionarPais(p)) {
			System.out.println("1:" + sigla + ";" + nome);
		} else {
			System.out.println("1:erro sigla repetida");
		}
	}

}
