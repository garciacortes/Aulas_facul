import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TorreHanoiMeu {
	
	List<Integer> pos = new ArrayList<Integer>();
	List<String> origem = new ArrayList<String>();
	List<String> destino = new ArrayList<String>();
	List<String> auxiliar = new ArrayList<String>();

	public TorreHanoiMeu() {
	}
	
	public void Interativo(int n, String orig, String dest, String aux) {
		int o = 0;
		pos.add(n);
		origem.add(orig);
		destino.add(dest);
		auxiliar.add(aux);
		while(o < 5) {
			if(pos.size() == 1) {
				for(int i = pos.getFirst(); i > 1; i--) {
					pos.add(i - 1);
					origem.add(origem.getLast());
					destino.add(auxiliar.getLast());
					auxiliar.add(destino.get(destino.size() - 2));
				}
			} 
			System.out.println(pos.getLast() + " | " + origem.getLast() + " | " + destino.getLast() + " | " + auxiliar.getLast());
			if(pos.getLast() == 2) {
				System.out.println(origem.getLast() + " -> " + destino.getLast());
				pos.add(1);
				origem.add(auxiliar.getLast());
				destino.add(destino.getLast());
				auxiliar.add(origem.get(origem.size() - 2));
				Remover(pos, origem, destino, auxiliar, pos.size() - 2);
			} 
			if(pos.getLast() == 1) {
				System.out.println(origem.getLast() + " -> " + destino.getLast());
				Remover(pos, origem, destino, auxiliar, pos.size() - 1);
			} 
			if(pos.getLast() == 3) {
				System.out.println(origem.getLast() + " -> " + destino.getLast());
				pos.add(2);
				origem.add(auxiliar.getLast());
				destino.add(destino.getLast());
				auxiliar.add(origem.get(origem.size() - 2));
				Remover(pos, origem, destino, auxiliar, pos.size() - 2);
			}
			o++;
		}
	}
	
	public void Remover(List<Integer> pos, List<String> origem, List<String> destino, List<String> auxiliar, int removePos) {
		pos.remove(removePos);
		origem.remove(removePos);
		destino.remove(removePos);
		auxiliar.remove(removePos);
	}
	
	public static void main(String[] args) {
		TorreHanoiMeu th = new TorreHanoiMeu();
		th.Interativo(3, "A", "C", "B");
	}
}
