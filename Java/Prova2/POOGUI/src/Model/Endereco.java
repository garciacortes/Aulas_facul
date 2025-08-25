package Model;

public class Endereco extends Estado{
	
	private String rua, numero;
	
	public Endereco(String rua, String numero, String cidade, String estado) {
		super(cidade, estado);
		this.rua = rua;
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}
	
	public String getEstado() {
		return super.getEstado();
	}
	
	public String getCidade() {
		return super.getCidade();
	}
}
