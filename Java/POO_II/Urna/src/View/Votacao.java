package View;

public class Votacao {
	
	private int Cand1, Cand2, Branco, Total;
	
	public Votacao() {
		Cand1 = 0;
		Cand2 = 0;
		Branco = 0;
		Total = 0;
	}
	
	public int Votar(int Voto) {
		switch(Voto) {
			case 1:
				Cand1++;
				break;
			case 2:
				Cand2++;
				break;
			case 3:
				Branco++;
				break;
			default: 
				return -1;
		}
		
		Total++;
		
		return 1;
	}

	public int getCand1() {
		return Cand1;
	}

	public int getCand2() {
		return Cand2;
	}

	public int getBranco() {
		return Branco;
	}

	public int getTotal() {
		return Total;
	}

}
