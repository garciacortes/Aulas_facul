
public class OperacoesMatematicas {
	
	private float num1;
	private float num2;
	private float num3;
	
	public OperacoesMatematicas(float n1, float n2, float n3) {
		num1 = n1;
		num2 = n2;
		num3 = n3;
	}
	
	public float Somar() {
		float soma = num1 + num2 + num3;
		return soma;
	}
	
	public float Subtrair() {
		float subtracao = num1 - num2 - num3;
		return subtracao;
	}
	
	public float CalcularMedia() {
		float media = Somar() / 3;
		return media;
	}
	
	public float Multiplcar() {
		float multiplicacao = num1 * num2 * num3;
		return multiplicacao;
	}
	
	public float MaiorNumero() {
		if (num1 > num2 && num1 > num3)
			return num1;
		else if(num2 > num1 && num2 > num3)
			return num2;
		else
			return num3;
	}
}
