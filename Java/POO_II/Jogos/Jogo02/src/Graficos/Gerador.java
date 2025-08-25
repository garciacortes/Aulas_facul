package Graficos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Gerador {
	
	public int tempoGeradorRetangulos = 0;
	public ArrayList<Retangulo> vetorRetangulos = new ArrayList<Retangulo>();
	public ArrayList<Particula> vetorParticulas = new ArrayList<Particula>();
	
	public Retangulo objRetangulo;
	public Particula objParticula;
	
	public void tick() {
		tempoGeradorRetangulos++;
		
		if(tempoGeradorRetangulos % 60 == 0) {
			objRetangulo = new Retangulo(0, new Random().nextInt(480 -40), 40, 40);
			vetorRetangulos.add(objRetangulo);
		}
		
		for(int i = 0; i < vetorRetangulos.size(); i++) {
			Retangulo current = vetorRetangulos.get(i);
			vetorRetangulos.get(i).tick();;
			
			if(current.x > Game.WIDTH) {
				vetorRetangulos.remove(current);
				Game.contadorTempo--;
			}
			if(Game.mouseClicado) {
				if(Game.mouse_x >= current.x && Game.mouse_x < current.x + current.width &&
					Game.mouse_y >= current.y && Game.mouse_y < current.y + current.height) {
					vetorRetangulos.remove(current);
					Game.pontuacao++;
					Game.mouseClicado = false;
					for(int n = 0; n < 50; n++) {
						objParticula = new Particula(current.x, current.y, 8, 8, current.cor);
						vetorParticulas.add(objParticula);
					}
				}
			}
		}
		
		for(int i = 0; i < vetorParticulas.size(); i++) {
			vetorParticulas.get(i).tick();
			objParticula = vetorParticulas.get(i);
			if(objParticula.tempoParticula >= 30) {
				vetorParticulas.remove(objParticula);
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < vetorRetangulos.size(); i++) {
			objRetangulo = vetorRetangulos.get(i);
			Graphics2D g2 = (Graphics2D) g;
			g2.rotate(Math.toRadians(objRetangulo.rotation), objRetangulo.x + objRetangulo.width/2, objRetangulo.y + objRetangulo.height/2);
			g2.setColor(objRetangulo.cor);
			g2.fillRect(objRetangulo.x,objRetangulo.y, objRetangulo.width, objRetangulo.height);
			g2.rotate(-Math.toRadians(objRetangulo.rotation), objRetangulo.x + objRetangulo.width/2, objRetangulo.y + objRetangulo.height/2);
		}
		for(int i = 0; i < vetorParticulas.size(); i++) {
			vetorParticulas.get(i).render(g);
		}
	}
}