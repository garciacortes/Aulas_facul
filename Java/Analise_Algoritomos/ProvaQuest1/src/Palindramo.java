import java.util.ArrayList;
import java.util.Arrays;

public class Palindramo {
		
	public Palindramo() {
	}
	
	public boolean MeuPalindramo(String palavra) {
		if(palavra.length() <= 1) {
			return true;
		}
		
		if(!palavra.substring(0, 1).equals(palavra.substring(palavra.length() - 1)) || palavra == null) {
			return false;
		} else {
			return MeuPalindramo(palavra.substring(1, palavra.length() - 1));
		}
	}
	
	public boolean ExercPalindromo(String palavra) {
		ArrayList<Estados> al = new ArrayList<Estados>();
		
		al.add(new Estados("q0", "a", "A", "D", "q1"));
		al.add(new Estados("q0", "b", "B", "D", "q4"));
		al.add(new Estados("q0", "A", "A", "D", "q8"));
		al.add(new Estados("q0", "B", "B", "D", "q8"));
		
		al.add(new Estados("q1", "a", "a", "D", "q1"));
		al.add(new Estados("q1", "b", "b", "D", "q1"));
		al.add(new Estados("q1", "#", "#", "E", "q2"));
		al.add(new Estados("q1", "A", "A", "E", "q2"));
		al.add(new Estados("q1", "B", "B", "E", "q2"));
		
		al.add(new Estados("q2", "a", "A", "E", "q7"));
		al.add(new Estados("q2", "A", "A", "D", "q3"));
		
		al.add(new Estados("q4", "a", "a", "D", "q4"));
		al.add(new Estados("q4", "b", "b", "D", "q4"));
		al.add(new Estados("q4", "#", "#", "E", "q5"));
		al.add(new Estados("q4", "A", "A", "E", "q5"));
		al.add(new Estados("q4", "B", "B", "E", "q5"));
		               
		al.add(new Estados("q5", "b", "B", "E", "q7"));
		al.add(new Estados("q5", "B", "B", "D", "q6"));
		
		al.add(new Estados("q7", "a", "a", "E", "q7"));
		al.add(new Estados("q7", "b", "b", "E", "q7"));
		al.add(new Estados("q7", "A", "A", "D", "q0"));
		al.add(new Estados("q7", "B", "B", "D", "q0"));
		
		al.add(new Estados("a8", "A", "A", "D", "q8"));
		al.add(new Estados("a8", "B", "B", "D", "q8"));
		al.add(new Estados("a8", "#", "#", "E", "q9"));
		
		ArrayList<String> str = new ArrayList<String>();
		str.add("#");
		for(int i = 0; i < palavra.length(); i++) {
			str.add(String.valueOf(palavra.charAt(i)));
		}
		str.add("#");
		
		
		return Turing(al, str, 1, "q0");
	}
	
	public boolean Turing(ArrayList<Estados> al, ArrayList<String> palavra, int m, String inicial) {
		ArrayList<String> EstadoFinal = new ArrayList<String>(Arrays.asList("q3", "q6", "q9"));
		
		palavra.forEach(System.out::print);
		System.out.println("\n"+ inicial + " -> " + palavra.get(m) + " Estado Inicial -> " + inicial);
		return al.stream()
	             .anyMatch(estado -> {
	                 if (estado.getEstadoIni().equals(inicial) && estado.getSimboloLido().equals(palavra.get(m))) {
	                     palavra.set(m, estado.getSimboloEscrito());
	                     return Turing(al, palavra, m + (estado.getMovimento().equals("D") ? 1 : -1), estado.getEstadoProx());
	                 } else {
	                	 return false;
	                 }
	             }) || EstadoFinal.contains(inicial);
		
	}

}
