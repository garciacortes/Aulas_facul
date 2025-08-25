
public class Estados {
	
	private String simboloLido, SimboloEscrito, movimento, estadoProx, EstadoIni;
	
	public Estados(String eI, String l, String e, String m, String eP) {
		this.EstadoIni = eI;
		this.simboloLido = l;
		this.SimboloEscrito = e;
		this.movimento = m;
		this.estadoProx = eP;
	}

	public String getSimboloLido() {
		return simboloLido;
	}

	public String getSimboloEscrito() {
		return SimboloEscrito;
	}

	public String getMovimento() {
		return movimento;
	}

	public String getEstadoProx() {
		return estadoProx;
	}

	public String getEstadoIni() {
		return EstadoIni;
	}

}
