package teste;

public class sefuder {
	
	public static void main(String[] args) {
		long x = 111000;
		long y = 0;
		
		for(int i = 2; i < 426; i++) {
			y += x;
			x += 37000;
		}		System.out.println(y);
	}
}
