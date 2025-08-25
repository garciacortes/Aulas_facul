package questao2;

import java.math.BigInteger;

public class TorreHanoi {
	
	public BigInteger Recursivo(int n) {
		//return (Math.pow(2, n) - 1);
		return BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE);
    }
}
