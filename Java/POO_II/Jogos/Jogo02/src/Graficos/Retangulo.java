package Graficos;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class Retangulo extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public Color cor;
	public int velocidade;
	public int rotation;
	
	public Retangulo(int x, int y, int largura, int altura) {
		super(x,y,largura,altura);
		cor = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
		velocidade = new Random().nextInt(4) + 2;
	}
	
	public void tick() {
		x += velocidade;
		rotation++;
		if(rotation >= 360) {
			rotation = 0;
		}
	}
}