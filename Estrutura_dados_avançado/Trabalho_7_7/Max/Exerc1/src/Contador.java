
public class Contador {
	
	private int count, num;
	
	public Contador(int count) {
		this.count = count;
	}
	
	public void incrementar() {
		for(num = 0; num < count; num++) {
			setNum(num);
		}
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}