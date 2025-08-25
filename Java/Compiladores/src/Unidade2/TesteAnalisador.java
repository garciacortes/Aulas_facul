package Unidade2;

import java.io.*;

public class TesteAnalisador {

	public static void main(String[] args) throws IOException {
		
		String arquivo = "C:\\Users\\Aluno\\Desktop\\Aula01\\Unidade2\\src\\Unidade2/Mimic.txt";
		
		BufferedReader texto = new BufferedReader(new FileReader(arquivo));
		
		Lexer analise = new Lexer(texto);
		
		while(true) {
			Token objetotoken = analise.yylex();
			if(objetotoken == null) {
				break;
			}
		}
	}

}
