public class App {

	public static void main(String[] args) {
		TorreHanoi th = new TorreHanoi();
		System.out.println("-----ITERATIVO-----");
		th.Iterativo(3, 'A', 'C', 'B');
		System.out.println("-----RECURSIVO-----");
		th.Recursivo(3, 'A', 'C', 'B');		
	}

}
