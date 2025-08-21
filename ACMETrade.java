import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;

public class ACMETrade {
	private Scanner entrada = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
	private Convencao convencao;
	private Federacao federacao;

	// Atributos para redirecionamento de E/S
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "entrada.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "saida.txt";  // Nome do arquivo de saida de dados

	public ACMETrade() {
		redirecionaEntrada();    // Redireciona Entrada para arquivos
        //redirecionaSaida();    // Redireciona Saida para arquivos
		//entrada = new Scanner(System.in);
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

		while (true) {
			boolean adicionado = false;
			System.out.print("Digite a sigla do país: ");
			String sigla = entrada.nextLine();

			if (sigla.equals("-1")) {
				System.out.println();
				break;
			}

			System.out.print("Digite o nome do país: ");
			String nome = entrada.nextLine();

			System.out.println();

			System.out.println();

			Pais p = new Pais(sigla, nome);

			if (federacao.adicionarPais(p)) {
				System.out.println("[1:" + p + "]\n");
				adicionado = true;
			} else {
				System.out.println("[1:erro sigla repetida]\n");
			}
		}
	}

	private void cadastrarAcordo() {
		System.out.println("=-=-=-=-= CADASTRO DE ACORDO =-=-=-=-=\n");

		boolean codigoValido = false;
		int codigo = 0;
		String siglaC;
		String siglaV;
		Pais comprador, vendedor;
		while (true) {
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

			if(codigo == -1){
				break;	
			}

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
			System.out.print("Digite o novo nome do país: ");
			String nome = entrada.nextLine();

			System.out.println();

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

	// Redireciona Entrada de dados para arquivos em vez de teclado
    // Chame este metodo para redirecionar a leitura de dados para arquivos
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

    // Redireciona Saida de dados para arquivos em vez da tela (terminal)
    // Chame este metodo para redirecionar a escrita de dados para arquivos
    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
    }

    // Restaura Entrada padrao para o teclado
    // Chame este metodo para retornar a leitura de dados para o teclado
    private void restauraEntrada() {
        entrada = new Scanner(System.in);
    }

    // Restaura Saida padrao para a tela (terminal)
    // Chame este metodo para retornar a escrita de dados para a tela
    private void restauraSaida() {
        System.setOut(saidaPadrao);
    }

}
