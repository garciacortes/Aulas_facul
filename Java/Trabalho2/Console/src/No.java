
public class No {
	
	public int valor;
    public int profundidade;
    public No no_esquerda;
    public No no_direita;
  
    public No(int valor) {
        this.valor  = valor;
        this.no_direita = null;
        this.no_esquerda = null;
    }
}
