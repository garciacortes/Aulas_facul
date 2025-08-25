package Prova;

import java.io.File;

public class GerarLexer {
	
	public static void main(String[] args) {
		
		String CaminhoDoArquivo = "C:\\Users\\Lucas\\Documents\\Programacao\\Facul\\Java\\Compiladores\\Prova1\\src\\Prova\\especificacao.flex";
		
		File arquivo = new File(CaminhoDoArquivo);
		
		jflex.Main.generate(arquivo);
	}
}
