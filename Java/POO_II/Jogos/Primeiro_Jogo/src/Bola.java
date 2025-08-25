import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bola {
	
	public double x, y, dx, dy;
	public int larguraBola, alturaBola;
	public double velocidade = 1;
	public int score = 0;
	public int scoreAux = 0;
	
	public Bola(int x, int y) {
		this.x = x;
		this.y = y;
		this.larguraBola = 4;
		this.alturaBola = 4;
		
		CalcularAnguloDeslocamento();
	}
	
	public void CalcularAnguloDeslocamento() {
		int angle = new Random().nextInt(120 -45) + 45 + 1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void AtualizarPosicao() {
		if(x + (dx * velocidade) + larguraBola >= Jogo.largura) {
			dx *= -1;
		} else if(x + (dx * velocidade) < 0) {
			dx *= -1;
		}
		
		if(y >= Jogo.altura) {
			System.out.println("Voce Perdeu!");
			x = 0;
			y = 0;
			score = 0;
			scoreAux = 0;
			velocidade = 1;
			return;
		}
		
		Rectangle regiaoBola = new Rectangle((int)(x + (dx * velocidade)), (int)(y + (dy * velocidade)), larguraBola, alturaBola);
		Rectangle regiaoRaqueteJogador = new Rectangle(Jogo.objRaqueteJogador.x, Jogo.objRaqueteJogador.y, Jogo.objRaqueteJogador.larguraRaquete, Jogo.objRaqueteJogador.alturaRaquete);
		
		if(regiaoBola.intersects(regiaoRaqueteJogador)) { //Verifica Colisao da bola com a raquete
			CalcularAnguloDeslocamento();
			score++;
			scoreAux++;
			if(dy > 0) {
				dy *= -1;
			}
		}
		
		if(y < 0) {
			CalcularAnguloDeslocamento();
			if(dy < 0) {
				dy *= -1;
			}
		}
		
		if(scoreAux == 10) {
			scoreAux = 0;
			velocidade += 0.2;
		}
		
		//altualizar posição da bola
		x += dx * velocidade;
		y += dy * velocidade;
	}
	
	public void Desenhar(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, larguraBola, alturaBola);
		g.setColor(Color.white);
		Font f  = new Font("Dialog", Font.PLAIN, 12);
		g.setFont(f);
		g.drawString(Integer.toString(score), 0, 10);
	}
}
