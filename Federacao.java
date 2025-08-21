import java.util.ArrayList;
import java.util.Collection;

public class Federacao {
	private ArrayList<Pais> paises;

	public Federacao() {
		paises = new ArrayList<>(100);
	}

	public boolean adicionarPais(Pais p) {
		if (!verificarSiglaRepetida(p)) {
			paises.add(p);
			return true;
		}

		return false;
	}

	public boolean verificarSiglaRepetida(Pais p) {
		for (int i = 0; i < paises.size(); i++) {
			Pais test = paises.get(i);
			if (p.getSigla().equals(test.getSigla())) {
				return true;
			}
		}

		return false;
	}

	public Pais verificarSigla(String sigla) {
		for (int i = 0; i < paises.size(); i++) {
			Pais test = paises.get(i);
			if (sigla.equals(test.getSigla())) {
				return test;
			}
		}

		return null;
	}
}
