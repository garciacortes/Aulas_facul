package Prova;

%%
%{
	private void imprimir(String token, String lexema) {
		System.out.println(lexema + " ==>> " + token);
	}
%}

%class Lexer

%type Token

nomeVariavel = [_a-zA-Z][_a-zA-z0-9]*
inteiro = [0-9]+
decimal = [0-9]+["."]+[0-9]+
estruturaRepeticao = "while" | "for"
PalavraChave = "void" | "(" | ")" | ";"
inicioFuncao = "{"
finalFuncao = "}"
estruturaDecisao = "if"
tipoVariavel = "int" | "float" | "char"
operadorLeitura = ">>"
operadorImprimir = "<<"
funcaoLeitura = "cin"
funcaoImprimir = "cout"
OperadorComparacao = ">" | "<"
RetornoFuncao = "return"
OperadorIncremento = "++"
branco = [\t\n\r\s ]+


%%

{nomeVariavel} 				{ imprimir("VARIAVEL : ", yytext ()); return Token.NOME_VARIAVEL; }
{inteiro}					{ imprimir("NUMERO INTEIRO : ", yytext()); return Token.INT; }
{decimal}					{ imprimir("NUMERO DECIMAL :", yytext()); return Token.DEC; }
{estruturaRepeticao} 		{ imprimir("ESTRUTURA DE REPETICAO :", yytext()); return Token.ESTRUTURA_REPETICAO; }
{PalavraChave} 				{ imprimir("PALAVRA CHAVE :", yytext()); return Token.PALAVRA_CHAVE; }
{inicioFuncao} 				{ imprimir("INICIO DE FUNCAO :", yytext()); return Token.INICIO_FUNCAO; }
{finalFuncao} 				{ imprimir("FINAL DE FUNCAO :", yytext()); return Token.FINAL_FUNCAO; }
{estruturaDecisao} 			{ imprimir("ESTRUTURA DE DECISAO :", yytext()); return Token.ESTRUTURA_DECISAO; }
{tipoVariavel} 				{ imprimir("TIPO DE VARIAVEL :", yytext()); return Token.TIPO_VARIAVEL; }
{operadorLeitura} 			{ imprimir("OPERADOR DE LEITURA DE DADOS :", yytext()); return Token.OPERADOR_LEITURA; }
{operadorImprimir} 			{ imprimir("OPERADOR DE IMPRESSAO DE DADOS :", yytext()); return Token.OPERADOR_IMPRIMIR; }
{funcaoLeitura} 			{ imprimir("FUNCAO PARA LEITURA DE DADOS :", yytext()); return Token.FUNCAO_LEITURA; }
{funcaoImprimir} 			{ imprimir("FUNCAO PARA IMPRIMIR DADOS :", yytext()); return Token.FUNCAO_IMPRIMIR; }
{OperadorComparacao} 		{ imprimir("OPERADOR DE COMPARACAO :", yytext()); return Token.OPERADOR_COMPARACAO; }
{RetornoFuncao} 			{ imprimir("RETORNO DE FUNCAO :", yytext()); return Token.RETORNO_FUNCAO; }
{OperadorIncremento}		{ imprimir("OPERADOR DE INCREMENTO :", yytext()); return Token.OPERADOR_INCREMENTO; }
{branco} 					{ return Token.BRANCO; }
<<EOF>>						{ return null; }