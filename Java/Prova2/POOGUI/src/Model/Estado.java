package Model;

public class Estado extends Cidade{

	private String estado;
	

	public Estado(String cidade, String estado) {
		super(cidade);
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getCidade() {
		return super.getCidade();
	}
}
