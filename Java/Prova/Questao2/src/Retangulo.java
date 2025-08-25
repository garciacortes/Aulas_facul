
public class Retangulo {
	
	private double lado1;
	private double lado2;
	
	public Retangulo() {
	}
	
	public Retangulo(double l1, double l2) {
		lado1 = l1;
		lado2 = l2;
	}
	
	public double CaclcularArea() {
		double area = lado1 * lado2;
		return area;
	}
	
	public double CalcularPerimetro() {
		double perimetro = (2 * lado1) + (2 * lado2);
		return perimetro;
	}
}
