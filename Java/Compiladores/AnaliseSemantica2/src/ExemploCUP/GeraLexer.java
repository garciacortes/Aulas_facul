package ExemploCUP;

import java.io.File;

public class GeraLexer {
	
	public static void main(String[] args) {
		
		String CaminhoArquivo = "C:\\Users\\Lucas\\Documents\\Programação\\Facul\\Java\\Compiladores\\AnaliseSemantica2\\src\\ExemploCUP\\lexico.flex";
		File arquivo = new File(CaminhoArquivo);
		
		jflex.Main.generate(arquivo);
	}
}
