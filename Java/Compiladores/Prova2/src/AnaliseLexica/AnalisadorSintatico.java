package AnaliseLexica;
import java.util.ArrayList;
import java.util.stream.Collectors;

import RegrasSintaticas.Inicio;
import RegrasSintaticas.RegrasSintaticas;

public class AnalisadorSintatico implements RegrasSintaticas{
	
	RegrasSintatica objRegrasSintaticas;
	ArrayList<ClassificacaoLexica> VetorAnaliseLexica;
	
	public AnalisadorSintatico() {
		objRegrasSintaticas = new RegrasSintatica();
		VetorAnaliseLexica = Lexer.listAnaliseLexica;
	}
	
	public void AnaliseSintatica() {
		
		ArrayList<ControleSintatico> VetorAnaliseSintatica = VetorAnaliseLexica.stream()
				.collect(Collectors.groupingBy(ClassificacaoLexica::linha))
			    .values().stream()
			    .map(linha -> new ControleSintatico(linha.get(0).linha(), new ArrayList<>(linha)))
			    .collect(Collectors.toCollection(ArrayList::new));
		
		if(new Inicio().verificar(VetorAnaliseSintatica)) {
			int Tamanho = VetorAnaliseLexica.size();
			int QuantidadeTotalLinhas = VetorAnaliseLexica.get(Tamanho - 1).linha();
			int LinhaAtual = objControle.getLinhaAtual();
			boolean ProgramaSemErros = true;
			int QuantidadeErros = 0;
			
			
			while(VetorAnaliseSintatica.get(LinhaAtual).linha <= QuantidadeTotalLinhas - 1) {
				
				if(!VetorAnaliseSintatica.get(LinhaAtual).vetorAnalise.isEmpty()) {
					if(!objRegrasSintaticas.Programa(VetorAnaliseSintatica)) {
						ProgramaSemErros = false;
						QuantidadeErros++;
						System.out.println("**ERRO SINTATICO");
						System.out.println("Linha: " + LinhaAtual + " ");
						VetorAnaliseSintatica.get(LinhaAtual).vetorAnalise.forEach(c -> System.out.println(c.lexema()));
					}
				}
				
				System.out.println(" ");
				int t = objControle.getLinhaAtual();
				objControle.setLinhaAtual(++t);
				LinhaAtual = objControle.getLinhaAtual();
				objControle.setIndiceAtual(0);
			}
			
			if(ProgramaSemErros) {
				System.out.println(" \n Analise Sintatica concluida com sucesso! ZERO erros");
			} else {
				System.out.println("\n Quantidade de Erros: " + QuantidadeErros);
			}
		}
		else {
			System.out.println("Erro na linha 1 - Inicialização do Programa");
		}
	}
	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		return false;
	}
}
