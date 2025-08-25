package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class Inicio implements RegrasSintaticas{
	
    public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
    	int Tamanho = VetorAnaliseLexica.size();
    	
		if((VetorAnaliseLexica.get(0).vetorAnalise.get(0).token() == Token.TIPOVARIAVEL) && (VetorAnaliseLexica.get(0).vetorAnalise.get(1).token() == Token.MAIN) &&
		   (VetorAnaliseLexica.get(0).vetorAnalise.get(2).token() == Token.ABREPARENTESE) && (VetorAnaliseLexica.get(0).vetorAnalise.get(3).token() == Token.FECHAPARENTESE) &&
		   (VetorAnaliseLexica.get(1).vetorAnalise.get(0).token() == Token.ABRECHAVE) && (VetorAnaliseLexica.get(Tamanho-2).vetorAnalise.get(0).token() != Token.RETURN) &&
		   (VetorAnaliseLexica.get(Tamanho - 1).vetorAnalise.get(0).token() == Token.FECHACHAVE)) {
			objControle.setLinhaAtual(2);
			return true;
		} else if((VetorAnaliseLexica.get(0).vetorAnalise.get(0).token() == Token.TIPOVARIAVEL) &&
				  (VetorAnaliseLexica.get(0).vetorAnalise.get(1).token() == Token.MAIN) &&
				  (VetorAnaliseLexica.get(0).vetorAnalise.get(2).token() == Token.ABREPARENTESE) &&
				  (VetorAnaliseLexica.get(0).vetorAnalise.get(3).token() == Token.FECHAPARENTESE) &&
				  (VetorAnaliseLexica.get(1).vetorAnalise.get(0).token() == Token.ABRECHAVE) &&
				  (VetorAnaliseLexica.get(Tamanho-2).vetorAnalise.get(0).token() == Token.RETURN) &&
				  (VetorAnaliseLexica.get(Tamanho-2).vetorAnalise.get(1).token() == Token.NUMEROINTEIRO) &&
				  (VetorAnaliseLexica.get(Tamanho-2).vetorAnalise.get(2).token() == Token.PONTOVIRGULA) &&
				  (VetorAnaliseLexica.get(Tamanho - 1).vetorAnalise.get(0).token() == Token.FECHACHAVE)) { 
			objControle.setLinhaAtual(2);
			return true;
		}
		return false;
    }
}