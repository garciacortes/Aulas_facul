import java.io.File;
import java.io.FileNotFoundException;

import Analisador.AnalisadorSintatico;
import Analisador.Lexer;

public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		String CaminhoArquivo = "src/main/resources/codigo2.txt";
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
