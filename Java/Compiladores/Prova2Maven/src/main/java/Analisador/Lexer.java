package Analisador;
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
	
	boolean TokenSwitch(String Lexema) {
		if(Lexema.equals("switch")) {
			return true;
		} else {
			return false;
		}
	}
	boolean TokenCase(String Lexema) {
		if(Lexema.equals("case")) {
			return true;
		} else {
			return false;
		}
	}
	boolean TokenBreak(String Lexema) {
		if(Lexema.equals("break")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenReturn(String Lexema) {
		if(Lexema.equals("return")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenSystem(String Lexema) {
		if(Lexema.equals("system")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenDefault(String Lexema) {
		if(Lexema.equals("default")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenEstruturaWhile(String Lexema) {
		if(Lexema.equals("while")) {
			return true;
		} else {
			return false;
		}
	}

	
	boolean TokenTipoVariavel(String Lexema) {
		if(Lexema.matches("int|char|float")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenTipoImprimir(String Lexema) {
		if(Lexema.matches("println|printf")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenTipoLeitura(String Lexema) {
		if(Lexema.equals("scanf")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenEstruturaDesicao(String Lexema, int linha) {
		if(Lexema.matches("if")) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURAIF, linha));
			return true;
		} else if(Lexema.matches("else")){
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURAELSE, linha));
			return true;
		} else if(Lexema.matches("else if|elseif")){
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURAELSEIF, linha));
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenEstruturaRepeticao(String Lexema, int linha) {
		if(Lexema.matches("for")) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURAFOR, linha));
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
		if(Lexema.equals("+") || Lexema.matches("\\*")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenOperadoresRelacionais(String Lexema) {
		if(Lexema.equals(">") || Lexema.equals("<") || Lexema.equals("==") || Lexema.equals("<=")) {
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
	
	boolean TokenAbreColchete(String Lexema) {
		if(Lexema.equals("[")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenFechaColchete(String Lexema) {
		if(Lexema.equals("]")) {
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
	
	boolean TokenDoisPontos(String Lexema) {
		if(Lexema.equals(":")) {
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
	
	boolean TokenOperadorLogico(String Lexema) {
		if(Lexema.equals("&&") || Lexema.equals("||")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenPalavraReservadaDo(String Lexema) {
		if(Lexema.matches("do")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenComentario(String Lexema) {
		if(Lexema.matches("//.*")) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean TokenStrings(String Lexema, int linha) {
		if(Lexema.matches("\"[^\"%]*(?!%[dDfFxX])\"")) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.STRING, linha));
			return true;
		} else if(Lexema.matches("\"(%[DdFfXx])\"")) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESPECIFICADORFORMATACAO, linha));
			return true;
		} else if(Lexema.matches("\"[^\"]*((%[dDfFxX].*)|(%.[0-9]+f).*)\"")) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.STRINGFORMATACAO, linha));
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
		
		if(TokenPalavraReservadaDo(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.PALAVRARESERVADADO, linha));
			return;
		}
		
		if(TokenEstruturaWhile(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ESTRUTURAWHILE, linha));
			return;
		}
		
		if(TokenReturn(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.RETURN, linha));
			return;
		}
		
		if(TokenSwitch(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.SWITCH, linha));
			return;
		}
		
		if(TokenCase(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.CASE, linha));
			return;
		}
		
		if(TokenBreak(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.BREAK, linha));
			return;
		}
		
		if(TokenSystem(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.SYSTEM, linha));
			return;
		}
		
		if(TokenDefault(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DEFAULT, linha));
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
		
		if(TokenComentario(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.COMENTARIO, linha));
			return;
		}
		
		if(TokenEstruturaDesicao(Lexema, linha)) {
			return;
		}
		
		if(TokenEstruturaRepeticao(Lexema, linha)) {
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
		
		if(TokenAbreColchete(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ABRECOLCHETE, linha));
			return;
		}
		
		if(TokenFechaColchete(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.FECHACOLCHETE, linha));
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
		
		if(TokenDoisPontos(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.DOISPONTOS, linha));
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
		
		if(TokenOperadorLogico(Lexema)) {
			listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.OPERADORESLOGICOS, linha));
			return;
		}
		
		
		if(TokenStrings(Lexema, linha)) {
			return;
		}
		
		listAnaliseLexica.add(new ClassificacaoLexica(Lexema, Token.ERRODESCONHECIDO, linha));
		return;
	}
	
	void GerarTabelaSimbolos() {
		objTabelaDeSimbolos.AdicionarSimbolo(Token.VOID, "COMANDO VOID");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.MAIN, "COMANDO MAIN");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.RETURN, "COMANDO RETURN");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.SWITCH, "ESTRUTURA SWITCH");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.BREAK, "OPERADOR DE FLUXO BREAK");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.CASE, "ESTRUTURA CASE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.DEFAULT, "ESTRUTURA DEFAULT");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.TIPOVARIAVEL, "TIPO DA VARIAVEL");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.LEITURADADOS, "LEITURA DE DADOS");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.COMENTARIO, "COMENTARIO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.IMPRIMIR, "IMPRIMIR NO CONSOLE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURAIF, "ESTRUTURA IF");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURAELSEIF, "ESTRUTURA ELSEIF");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURAELSE, "ESTRUTURAELSE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURAFOR, "ESTRUTURAFOR");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ATRIBUICAO, "ATRIBUICAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.OPERADORESMATEMATICO, "OPERADOR MATEMATICO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.OPERADORESRELACIONAIS, "OPERADOR RELACIONAL");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ABRECHAVE, "ABRE_CHAVE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.FECHACHAVE, "FECHA_CHAVE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ABREPARENTESE, "ABRE_PARENTESES");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.FECHAPARENTESE, "FECHA_PARENTESES");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ABRECOLCHETE, "ABRE_COLCHETE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.FECHACOLCHETE, "FECHA_COLCHETE");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.PONTOVIRGULA, "PONTO_VIRGULA");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.VIRGULA, "VIRGULA");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.VARIAVEL, "VARIAVEL");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.NUMEROINTEIRO, "NUMERO_INTEIRO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.NUMEROFLOAT, "NUMERO_FLOAT");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.DOISPONTOS, "DOIS_PONTOS");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESPECIFICADORFORMATACAO, "ESPECIFICADOR DE FORMATACAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ENDERECOMEMORIA, "ENDERECO DE MEMORIA");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.INCREMENTO, "INCREMENTO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.STRING, "STRING");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.STRINGFORMATACAO, "STRING COM FORMATACAO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.OPERADORESLOGICOS, "OPERADOR LOGICO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.PALAVRARESERVADADO, "PALAVRA RESERVADA DO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.PALAVRARESERVADADO, "PALAVRA RESERVADA DO");
		objTabelaDeSimbolos.AdicionarSimbolo(Token.ESTRUTURAWHILE, "ESTRUTURA WHILE");
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
			String[] ConjuntoLexemas = TextoArquivoAnalisado.split("(?<!else)\\s+(?<![\"//].*)|"
					                                             + "(?<=else)\\s+(?!if)|"
					                                             + "(?=[\\();<*\\],\\[])|"
					                                             + "(?=\")(?=[,();=&*])|"
					                                             + "(?<!\".*)(?=[:])|"
					                                             + "(?<=[()>*\\]\\[;,])|"
					                                             + "(?<=[&]{1,2}(?=[^&]))|"
					                                             + "(?<=[=]{1,2}(?=[^=]))|"
					                                             + "(?=[=]{2,2}(?=[^=]))|"
					                                             + "(?!=)(?<=[<])|"
					                                             + "(?=&)(?<![&])|"
					                                             + "(?<![=<>])(?=[=])|"
					                                             + "(?<![+])(?=[+])|"
					                                             + "(?![+])(?<=[+])");
			
			for(String lexema: ConjuntoLexemas) {
				if(!lexema.isBlank()) {
					ClassificaLexema(lexema, linha);
				}
			}	
			linha++;
		}
		
		LeituraArquivo.close();
		
		return GerarAnaliseLexica();
	}
}
