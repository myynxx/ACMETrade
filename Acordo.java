/**
 * easterEgg();
 * ArrayList<Pais> paises = federacao.listarPaises();
 * for(Pais p : paises){
 * System.out.println("Sigla: " + p.getSigla());
 * System.out.println("Nome: " + p.getNome());
 * }
 * System.out.println("========================");
 * ArrayList<Acordo> acordos;
 * acordos = convencao.listarAcordos();
 * for (Acordo a : acordos) {
 * System.out.println(a.getCodigo());
 * System.out.println(a.getAnoInicio());
 * System.out.println(a.getProduto());
 * System.out.println(a.getTaxa());
 * System.out.println(a.getComprador().getSigla());
 * System.out.println(a.getComprador().getNome());
 * System.out.println(a.getVendedor().getSigla());
 * System.out.println(a.getVendedor().getNome());
 * }
 * System.out.println("========================");
 * if(convencao.removerAcordo(333))
 * System.out.println("Removeu 333");
 * else
 * System.out.println("Nao removeu 333");
 * acordos = convencao.listarAcordos();
 * for (Acordo a : acordos) {
 * System.out.println(a.getCodigo());
 * System.out.println(a.getAnoInicio());
 * System.out.println(a.getProduto());
 * System.out.println(a.getTaxa());
 * System.out.println(a.getComprador().getSigla());
 * System.out.println(a.getComprador().getNome());
 * System.out.println(a.getVendedor().getSigla());
 * System.out.println(a.getVendedor().getNome());
 * }
 * System.out.println("========================");
 * convencao.removerAcordo("PT");
 * acordos = convencao.listarAcordos();
 * for (Acordo a : acordos) {
 * System.out.println(a.getCodigo());
 * System.out.println(a.getAnoInicio());
 * System.out.println(a.getProduto());
 * System.out.println(a.getTaxa());
 * System.out.println(a.getComprador().getSigla());
 * System.out.println(a.getComprador().getNome());
 * System.out.println(a.getVendedor().getSigla());
 * System.out.println(a.getVendedor().getNome());
 * }
 * System.out.println("========================");
 */
public class Acordo {
	private int codigo;
	private String produto;
	private double taxa;
	private Pais comprador;
	private Pais vendedor;

	public Acordo(int codigo, String produto, double taxa, Pais comprador, Pais vendedor) {
		this.codigo = codigo;
		this.produto = produto;
		this.taxa = taxa;
		this.vendedor = vendedor;
		this.comprador = comprador;
	}

	public int getCodigo() {
		return codigo;
	}

	public Pais getComprador() {
		return comprador;
	}

	public Pais getVendedor() {
		return vendedor;
	}

	@Override
	public String toString() {
		return codigo + ";" + produto + ";" + taxa + ";" + comprador.getSigla() + ";" + vendedor.getSigla();
	}

}
