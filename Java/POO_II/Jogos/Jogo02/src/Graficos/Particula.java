package Graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Particula extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public Color cor;
	public int velocidade = 8;
	public int rotacao = 0;
	public double dx, dy;
	public double xAtual, yAtual;
	public int tempoParticula = 0;
	
	public Particula(int x, int y, int largura, int altura, Color color) {
		super(x,y,largura,altura);
		this.cor = color;
		xAtual = x;
		yAtual = y;
		double angle = new Random().nextInt((120 - 45) + 45 + 1);
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		xAtual += dx * velocidade;
		yAtual += dy * velocidade;
		tempoParticula++;
	}
	
	public void render(Graphics g) {
		g.setColor(cor);
		g.fillRect((int)xAtual, (int)yAtual, width, height);
	}
}