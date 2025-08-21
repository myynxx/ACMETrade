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

	public Acordo consultarAcordo(int codigo) {
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (codigo == test.getCodigo()) {
				return test;
			}
		}

		return null;
	}

	public Acordo consultarAcordo(String sigla) {
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (sigla.equals(test.getComprador().getSigla())) {
				return test;
			}
		}

		return null;
	}

	public boolean removerAcordosComprador(String sigla) {
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (sigla.equals(test.getComprador().getSigla())) {
				remover(sigla);
				return true;
			}
		}

		return false;
	}

	public void remover(String sigla) {
		for (int i = 0; i < acordos.size(); i++) {
			Acordo test = acordos.get(i);
			if (sigla.equals(test.getComprador().getSigla())) {
				acordos.remove(test);
			}
		}
	}

	public ArrayList<Acordo> listarTodosAcordos() {
		if (acordos.size() == 0) {
			return null;
		} else {
			return acordos;
		}
	}
}
