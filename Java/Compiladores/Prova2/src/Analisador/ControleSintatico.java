	package Analisador;
	
	import java.util.ArrayList;
	
	public class ControleSintatico {
		
		public int linha, linhaAtual, IndiceAtual;

		public ArrayList<ClassificacaoLexica> vetorAnalise;
		
		public ControleSintatico(int linha, ArrayList<ClassificacaoLexica> vetorAnalise) {
			this.linha = linha;
			this.vetorAnalise = vetorAnalise;
		}
		
		public ControleSintatico() {
		}

		public int getLinhaAtual() {
			return linhaAtual;
		}

		public void setLinhaAtual(int linhaAtual) {
			this.linhaAtual = linhaAtual;
		}
		
		public int getIndiceAtual() {
			return IndiceAtual;
		}
		
		public void setIndiceAtual(int indiceAtual) {
			IndiceAtual = indiceAtual;
		}
		
		
		
	}
