import java.util.ArrayList;
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
					cadastrarAcordo();
					break;
				case 3:
					consultarPaisPelaSigla();
					break;
				case 4:
					consultarAcordoPeloCodigo();
					break;
				case 5:
					consultarAcordoPelaSiglaComprador();
					break;
				case 6:
					mudarNomePais();
					break;
				case 7:
					removerAcordosComprador();
					break;
				case 8:
					listarTodosAcordos();
					break;
				case 9:
					listarPaisesVendedores();
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
		System.out.println("=-=-=-=-= CADASTRO DE PAIS =-=-=-=-=\n");

		System.out.print("Digite o nome do país: ");
		String nome = entrada.nextLine();

		System.out.println();

		boolean adicionado = false;

		do {
			System.out.print("Digite a sigla do país: ");
			String sigla = entrada.nextLine();

			System.out.println();

			Pais p = new Pais(sigla, nome);

			if (federacao.adicionarPais(p)) {
				System.out.println("[1:" + p + "]\n");
				adicionado = true;
			} else {
				System.out.println("[1:erro sigla repetida]\n");
			}
		} while (!adicionado);

	}

	private void cadastrarAcordo() {
		System.out.println("=-=-=-=-= CADASTRO DE ACORDO =-=-=-=-=\n");

		boolean codigoValido = false;
		int codigo = 0;
		String siglaC;
		String siglaV;
		Pais comprador, vendedor;

		do {
			System.out.print("Digite o código do acordo: ");
			codigo = entrada.nextInt();
			entrada.nextLine();

			System.out.println();

			if (!convencao.verificarCodigoRepetido(codigo)) {
				codigoValido = true;
			} else {
				System.out.println("[2:erro-codigo repetido]\n");
			}

		} while (!codigoValido);

		System.out.print("Digite o nome do produto: ");
		String produto = entrada.nextLine();

		System.out.println();

		System.out.print("Digite a taxa: ");
		double taxa = entrada.nextDouble();
		entrada.nextLine();

		System.out.println();

		boolean paisValido = false;

		do {
			System.out.print("Digite a sigla do país comprador: ");
			siglaC = entrada.nextLine();

			System.out.println();

			comprador = federacao.verificarSigla(siglaC);

			if (comprador != null) {
				paisValido = true;
			} else {
				System.out.println("[2:erro-comprador inexistente]\n");
			}

		} while (!paisValido);

		paisValido = false;

		do {
			System.out.print("Digite a sigla do país vendedor: ");
			siglaV = entrada.nextLine();

			System.out.println();

			vendedor = federacao.verificarSigla(siglaV);

			if (vendedor != null) {
				paisValido = true;
			} else {
				System.out.println("[2:erro-vendedor inexistente]\n");
			}

		} while (!paisValido);

		System.out.println();

		Acordo acordo = new Acordo(codigo, produto, taxa, comprador, vendedor);

		convencao.cadastrarAcordo(acordo);

		System.out.println("[2:" + acordo + "]\n");
	}

	private void consultarPaisPelaSigla() {
		System.out.println("=-=-=-=-= CONSULTAR PAIS PELA SIGLA =-=-=-=-=\n");

		System.out.print("Digite a sigla do país: ");
		String sigla = entrada.nextLine();

		System.out.println();

		Pais p = federacao.verificarSigla(sigla);

		if (p != null) {
			System.out.println("3:" + p);
		} else {
			System.out.println("[3:erro-sigla inexistente]\n");
		}
	}

	private void consultarAcordoPeloCodigo() {
		System.out.println("=-=-=-=-= CONSULTAR ACORDO PELO CÓDIGO =-=-=-=-=\n");

		System.out.print("Digite acordo pelo código: ");
		int codigo = entrada.nextInt();

		System.out.println();

		Acordo a = convencao.consultarAcordo(codigo);

		if (a != null) {
			System.out.println("4:" + a);
		} else {
			System.out.println("[4:erro-codigo inexistente]\n");
		}
	}

	private void consultarAcordoPelaSiglaComprador() {
		System.out.println("=-=-=-=-= CONSULTAR ACORDO PELA SIGLA DO PAIS COMPRADOR =-=-=-=-=\n");

		System.out.print("Digite a sigla do país: ");
		String sigla = entrada.nextLine();

		System.out.println();

		Acordo a = convencao.consultarAcordo(sigla);

		if (a != null) {
			System.out.println("5:" + a);
		} else {
			System.out.println("[5:erro-sigla inexistente]\n");
		}
	}

	private void mudarNomePais() {
		System.out.println("=-=-=-=-= MUDAR NOME DE UM PAIS =-=-=-=-=\n");

		System.out.print("Digite a sigla do país: ");
		String sigla = entrada.nextLine();

		System.out.println();

		Pais p = federacao.verificarSigla(sigla);

		if (p != null) {
			System.out.print("Digite o novo nome do país: \n");
			String nome = entrada.nextLine();

			p.setNome(nome);

			System.out.println("6:" + p);
		} else {
			System.out.println("[6:erro-sigla inexistente]\n");
		}
	}

	private void removerAcordosComprador() {
		System.out.println("=-=-=-=-= REMOVER ACORDOS DE UM PAIS COMPRADOR =-=-=-=-=\n");

		System.out.print("Digite a sigla do país: ");
		String sigla = entrada.nextLine();

		System.out.println();

		Pais p = federacao.verificarSigla(sigla);

		if (p != null) {
			if (convencao.removerAcordosComprador(sigla)) {
				System.out.println("7:acordos removidos");
			} else {
				System.out.println("[7:erro-nenhum acordo encontrado]\n");
			}
		} else {
			System.out.println("[7:erro-sigla inexistente]\n");
		}
	}

	private void listarTodosAcordos() {
		System.out.println("=-=-=-=-= LISTAR TODOS OS ACORDOS =-=-=-=-=\n");

		ArrayList<Acordo> lista = convencao.listarTodosAcordos();

		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				System.out.println("8:" + lista.get(i));
			}
		} else {
			System.out.println("8:erro-nenhum acordo cadastrado");
		}
	}

	private void listarPaisesVendedores() {
		System.out.println("=-=-=-=-= LISTAR TODOS OS PAISES VENDEDORES =-=-=-=-=\n");

		ArrayList<Acordo> lista = convencao.listarTodosAcordos();

		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				System.out.println("9:" + lista.get(i).getVendedor());
			}
		} else {
			System.out.println("9:erro-nenhum país encontrado");
		}
	}

}
