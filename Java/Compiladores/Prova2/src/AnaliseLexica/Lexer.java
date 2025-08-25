package AnaliseLexica;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {
	
	public TabelaDeSimbolos objTabelaDeSimbolos = new TabelaDeSimbolos();
	public static ArrayList<ClassificacaoLexica> listAnaliseLexica = new ArrayList<ClassificacaoLexica>();
	
	boolean TokenVoid(String Lexema) {
		if(Lexema.equals("void")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenMain(String Lexema) {
		if(Lexema.equals("main")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenTipoVariavel(String Lexema) {
		if(Lexema.equals("int") || Lexema.equals("char") || Lexema.equals("float")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenTipoImprimir(String Lexema) {
		if(Lexema.equals("println") || Lexema.equals("cout")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenTipoLeitura(String Lexema) {
		if(Lexema.equals("sacnf") || Lexema.equals(">>")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenEstruturaDesicao(String Lexema) {
		if(Lexema.equals("if") || Lexema.equals("else")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenEstruturaRepeticao(String Lexema) {
		if(Lexema.equals("for")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenTipoAtribuicao(String Lexema) {
		if(Lexema.equals("=")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenOperadoresMatematico(String Lexema) {
		if(Lexema.equals("+") || Lexema.equals("*")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenOperadoresRelacionais(String Lexema) {
		if(Lexema.equals(">") || Lexema.equals("<")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenAbreChave(String Lexema) {
		if(Lexema.equals("{")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenFechaChave(String Lexema) {
		if(Lexema.equals("}")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenAbreParenteses(String Lexema) {
		if(Lexema.equals("(")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenFechaParenteses(String Lexema) {
		if(Lexema.equals(")")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenPontoVigula(String Lexema) {
		if(Lexema.equals(";")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenVirgula(String Lexema) {
		if(Lexema.equals(",")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenAspasDuplas(String Lexema) {
		if(Lexema.equals("\"")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenDoisPontos(String Lexema) {
		if(Lexema.equals(":")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenFormatacao(String Lexema) {
		if(Lexema.equals("%d") || Lexema.equals("%f")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenEnderecoDeMemoria(String Lexema) {
		if(Lexema.equals("&")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean EhDigito(char str) {
		if(Character.isDigit(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean EhLetra(char str) {
		if(Character.isLetter(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenNumeroInteiro(String Lexema) {
		if(Lexema.matches("-?[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenNumeroFloat(String Lexema) {
		if(Lexema.matches("[0-9]+[.][0-9]+")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenIncremento(String Lexema) {
		if(Lexema.equals("++")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenVariavel(String Lexema) {
		int tamanho = Lexema.length();
		
		for(int i = 0; i < tamanho; i++) {
			char pedacoLexema = Lexema.charAt(i);
			
			if(i == 0) {
				if(!((pedacoLexema == '_') || (EhLetra(pedacoLexema)))) {
					return false;
				}
			} else if(!(EhLetra(pedacoLexema) || EhDigito(pedacoLexema) || pedacoLexema == '_')) {
				return false;
			}
		}
		
		return true;
	}
	
	void ClassificaLexema(String Lexema, int linha) {
		if(TokenVoid(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.VOID, linha));
			return;
		}
		
		if(TokenMain(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.MAIN, linha));
			return;
		}
		
		if(TokenTipoVariavel(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.TIPOVARIAVEL, linha));
			return;
		}
		
		if(TokenTipoImprimir(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.IMPRIMIR, linha));
			return;
		}
		
		if(TokenTipoLeitura(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.LEITURADADOS, linha));
			return;
		}
		
		if(TokenEstruturaDesicao(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURADESICAO, linha));
			return;
		}
		
		if(TokenEstruturaRepeticao(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURAREPETICAO, linha));
			return;
		}
		
		if(TokenTipoAtribuicao(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ATRIBUICAO, linha));
			return;
		}
		
		if(TokenOperadoresMatematico(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADORESMATEMATICO, linha));
			return;
		}
		
		if(TokenOperadoresRelacionais(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADORESRELACIONAIS, linha));
			return;
		}
		
		if(TokenAbreChave(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ABRECHAVE, linha));
			return;
		}
		
		if(TokenFechaChave(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FECHACHAVE, linha));
			return;
		}
		
		if(TokenAbreParenteses(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ABREPARENTESE, linha));
			return;
		}
		
		if(TokenFechaParenteses(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FECHAPARENTESE, linha));
			return;
		}
		
		if(TokenPontoVigula(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.PONTOVIRGULA, linha));
			return;
		}
		
		if(TokenVirgula(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.VIRGULA, linha));
			return;
		}
		
		if(TokenAspasDuplas(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ASPASDUPLA, linha));
			return;
		}
		
		if(TokenDoisPontos(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DOISPONTOS, linha));
			return;
		}
		
		if(TokenFormatacao(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESPECIFICADORFORMATACAO, linha));
			return;
		}
		
		if(TokenEnderecoDeMemoria(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ENDERECOMEMORIA, linha));
			return;
		}
		
		if(TokenNumeroInteiro(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.NUMEROINTEIRO, linha));
			return;
		}
		
		if(TokenNumeroFloat(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.NUMEROFLOAT, linha));
			return;
		}
		
		if(TokenVariavel(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.VARIAVEL, linha));
			return;
		}
		
		if(TokenIncremento(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.INCREMENTO, linha));
			return;
		}
		
		listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ERRODESCONHECIDO, linha));
		return;
	}
	
	void GerarTabelaSimbolos() {
		objTabelaDeSimbolos.AdicionarSimbolo(Token.VOID, "COMANDO VOID");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.MAIN, "COMANDO MAIN");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.TIPOVARIAVEL, "TIPO DA VARIAVEL");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.LEITURADADOS, "LEITURA DE DADOS	");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.IMPRIMIR, "IMPRIMIR NO CONSOLE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURADESICAO, "ESTRUTURAS_DESICAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURAREPETICAO, "ESTRUTURAS_REPETICAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ATRIBUICAO, "ATRIBUICAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.OPERADORESMATEMATICO, "OPERADORES_MATEMATICO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.OPERADORESRELACIONAIS, "OPERADORES_RELACIONAIS");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ABRECHAVE, "ABRE_CHAVE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.FECHACHAVE, "FECHA_CHAVE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ABREPARENTESE, "ABRE_PARENTESES");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.FECHAPARENTESE, "FECHA_PARENTESES");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.PONTOVIRGULA, "PONTO_VIRGULA");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.VIRGULA, "VIRGULA");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ASPASDUPLA, "ASPAS_DUPLAS");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.VARIAVEL, "VARIAVEL");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.NUMEROINTEIRO, "NUMERO_INTEIRO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.NUMEROFLOAT, "NUMERO_FLOAT");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.DOISPONTOS, "DOIS_PONTOS");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESPECIFICADORFORMATACAO, "ESPECIFICADOR DE FORMATACAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ENDERECOMEMORIA, "ENDERECO DE MEMORIA");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.INCREMENTO, "INCREMENTO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ERRODESCONHECIDO, "ERRO_DESCONHECIDO");
	}
	
	boolean GerarAnaliseLexica() {
		boolean ResultadoAnaliseLexica = true;
		
		for(ClassificacaoLexica e: listAnaliseLexica) {
			String Lexema = e.lexema();
			int Linha = e.linha();
			Token CodToken = e.token();
			String Simbolo = objTabelaDeSimbolos.BuscarValor(CodToken);
			
			if(e.token() == Token.ERRODESCONHECIDO) {
				ResultadoAnaliseLexica = false;
			}
			
			System.out.println("Linha: " + Linha + " - Lexema: " + Lexema + " Simbolo: " + 
					Simbolo + " Token: " + CodToken);
		}
		
		return ResultadoAnaliseLexica;
	}
	
	public boolean AnalisadorLexico(File selectedFile) throws FileNotFoundException {
		GerarTabelaSimbolos();
		
		Scanner LeituraArquivo = new Scanner(selectedFile);
		String TextoArquivoAnalisado;
		
		System.out.println("\n\n ****** ANALISE LEXICA ****** \n\n");
		
		int linha = 1;
		while(LeituraArquivo.hasNextLine()) {
			TextoArquivoAnalisado = LeituraArquivo.nextLine();
			
			String[] ConjuntoLexemas = TextoArquivoAnalisado.split("[\\s]|(?=[\\\"(),;=<*:])|"
					                                             + "(?<=\\+\\+)|"
					                                             + "(?=\\+\\+)|"
					                                             + "(?<=[\"(&<=])|"
					                                             + "(?<=\\+)(?![+])|"
					                                             + "(?<![+])(?=\\+)");
			
			for(String lexema: ConjuntoLexemas) {
				if(!lexema.isEmpty()) {
					ClassificaLexema(lexema, linha);
				}
			}
			linha++;
		}
		
		LeituraArquivo.close();
		
		return GerarAnaliseLexica();
	}
}
