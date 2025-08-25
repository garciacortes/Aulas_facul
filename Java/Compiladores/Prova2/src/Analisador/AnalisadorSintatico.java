package Analisador;
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
			int QuantidadeTotalLinhas = VetorAnaliseSintatica.getLast().linha;
			int LinhaAtual = VetorAnaliseSintatica.get(objControle.getLinhaAtual()).linha;
			boolean ProgramaSemErros = true;
			int QuantidadeErros = 0;
			
			while(LinhaAtual <= QuantidadeTotalLinhas - 1) {
				
				if(!VetorAnaliseSintatica.get(objControle.getLinhaAtual()).vetorAnalise.isEmpty()) {
					if(!objRegrasSintaticas.Programa(VetorAnaliseSintatica)) {
						ProgramaSemErros = false;
						QuantidadeErros++;
						System.out.println("**ERRO SINTATICO");
						System.out.println("Linha: " + LinhaAtual + " ");
						VetorAnaliseSintatica.get(LinhaAtual-1).vetorAnalise.forEach(c -> System.out.println(c.lexema()));
					}
				}
				
				System.out.println(" ");
				int t = objControle.getLinhaAtual();
				objControle.setLinhaAtual(++t);
				LinhaAtual = VetorAnaliseSintatica.get(objControle.getLinhaAtual()).linha;
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
