
public class Livro {
	
	private String titulo;
	private int qtdPaginas, paginasLidas;
	private double porcentLido;
	
	public Livro() {
		titulo = "";
		qtdPaginas = 0;
		paginasLidas = 0;
	}
	
	private double verificarProgresso(double Calculo) {
		return Calculo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQtdPaginas() {
		return qtdPaginas;
	}

	public void setQtdPaginas(int qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}

	public int getPaginasLidas() {
		return paginasLidas;
	}

	public void setPaginasLidas(int paginasLidas) {
		this.paginasLidas = paginasLidas;
	}

	public double getPorcentLido() {
		return porcentLido;
	}

	public void setPorcentLido() {
		this.porcentLido = verificarProgresso(paginasLidas * 100 / qtdPaginas);
	}
	
}
