import java.util.ArrayList;
import java.util.Collection;

public class Convencao {
	private ArrayList<Acordo> acordos;

	public Convencao() {
		acordos = new ArrayList<>(100);
	}

	public boolean cadastrarAcordo(Acordo a) {
		acordos.add(a);

		return true;
	}

	public boolean verificarCodigoRepetido(int codigo) {
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (codigo == test.getCodigo()) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<Acordo> consultarAcordo(int codigo) {
		ArrayList<Acordo> aux = new ArrayList<>();
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (codigo == test.getCodigo()) {
				aux.add(test);
			}
		}

		return aux;
	}

	public ArrayList<Acordo> consultarAcordo(String sigla) {
		ArrayList<Acordo> aux = new ArrayList<>();
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (sigla.equalsIgnoreCase(test.getComprador().getSigla())) {
				aux.add(test);
			}
		}

		return aux;
	}

	public boolean removerAcordosComprador(String sigla) {
		if (remover(sigla)) {
			return true;
		}

		return false;
	}

	public boolean remover(String sigla) {
		boolean removeu = false;

		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (sigla.equalsIgnoreCase(test.getComprador().getSigla())) {
				acordos.remove(test);
				i--;
				removeu = true;
			}
		}

		return removeu;
	}

	public ArrayList<Acordo> listarTodosAcordos() {
		return new ArrayList<>(acordos);
	}
}
