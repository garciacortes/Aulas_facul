package Unidade2;
import java.io.*;

public class GeraLexer {

	public static void main(String[] args) {
		
		String CaminhoArquivo = "C:/Users/Aluno/Desktop/Aula01/Unidade2/src/Unidade2/especificacao.flex";
		
		File arquivo = new File(CaminhoArquivo);
		
		jflex.Main.generate(arquivo);
	}

}
