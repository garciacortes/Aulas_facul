import static unidade2.Token;
%%
%{
private void imprimir(String token, String lexema) {
System.out.println("lexema + " ==>> " + token);
}
%}

%class Lexer

%type Token

nomeVariavel = [_a-zA-Z][_a-zA-z0-9]*
inteiro = [10-9]+
decimal = [0-9]+["."]+[0-9]+
blocoComentario = "/*" ~"*/"
branco = [\t\n\r ]+
linhaComentario = {branco}* "//" .* 
palavraChave =  "if" | "class" | "int" | "While" | "do"

%%

{palavraChave}				{ imprimir("PALAVRA-CHAVE : ", yytext() ); return PALAVRA-CHAVE; }

{nomeVariavel} 				{ imprimir("VARIAVEL : ", yytext ()); return NOME_VARIAVEL; }

{inteiro}					{ Imprimir("NUMERO INTEIRO : ", yytext()); return INT; }

{decimal} 					{ imprimir("NUMERO DECIMAL :", yytext()) return DEC; }

{blocoComentario} 			{ imprimit("COMENTARIO : ", yytext()); return COMENTARIO; }

{linhaComentario} 			{ imprimir("COMENTARIO : ", yytext()); return COMENTARIO; }

{branco} 					{ return BRANCO; }

.							{ imprimir("<<< CARACTER NÃO VALIDO!!! >>> ", yytext()); return ERROR; }

<<EOF>>						{ return null; }