import java.io.File;
import java.io.FileNotFoundException;

import AnaliseLexica.AnalisadorSintatico;
import AnaliseLexica.Lexer;

public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		String CaminhoArquivo = "res/codigo.txt";
		Lexer objLexer = new Lexer();
		
		File arquivo = new File(CaminhoArquivo);
		
		if(!objLexer.AnalisadorLexico(arquivo)) {
			System.out.println("\n #### Erro na ANALISE LEXICA ######");
		} else {
			System.out.println("\n\n ********** ANALISE SINTATICA ********** \n\n");
			AnalisadorSintatico objSintatico = new AnalisadorSintatico();
			objSintatico.AnaliseSintatica();
		}
	}
}
