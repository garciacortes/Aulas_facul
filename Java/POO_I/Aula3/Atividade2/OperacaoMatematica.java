
public class OperacaoMatematica {
	
	private double Valor1, Valor2, Peso1, Peso2;
	
	public OperacaoMatematica() {
	}
	
	public double Somar(double Valor1, double Valor2) {
		double soma = Valor1 + Valor2;
		return soma;
	}
	public double Subtracao(double Valor1, double Valor2) {
		double subtracao = Valor1 - Valor2;
		return subtracao;
	}
	public double Divisao(double Valor1, double Valor2) {
		double Divisao = Valor1 / Valor2;
		return Divisao;
	}
	public double Multiplicacao(double Valor1, double Valor2) {
		double multiplicacao = Valor1 * Valor2;
		return multiplicacao;
	}
	public double Media(double Valor1, double Valor2) {
		double media = (Somar(Valor1, Valor2) / 2);
		return media;
	}
	public double MediaPonderada(double Valor1, double Valor2, double Peso1, double Peso2) {
		double mediaPonderada = Somar((Valor1 * Peso1), (Valor2 * Peso2)) / Somar(Peso1, Peso2);
		return mediaPonderada;
	}
}
