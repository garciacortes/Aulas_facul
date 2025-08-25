
public class Principal2 {

	public static void main(String[] args) {
		
		int lado1, lado2;
		
		lado1 = 10;
		lado2 = 5;
		
		Retangulo objRetangulo;
		objRetangulo = new Retangulo(lado1, lado2);
		
		System.out.println("O valor da Area é: " + objRetangulo.CaclcularArea());
		System.out.println("O valor do Perimetro é: " + objRetangulo.CalcularPerimetro());
	}

}
