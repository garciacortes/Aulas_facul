import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Semaphore;

public class RaqueteJogador {
	
	public boolean direita, esquerda;
	public int x, y;
	public int larguraRaquete, alturaRaquete;
	public Semaphore Mutex;
	
	public RaqueteJogador(int x, int y) {
		this.x = x;
		this.y = y;
		this.larguraRaquete = 40;
		this.alturaRaquete = 5;
		Mutex = new Semaphore(1);
	}
	
	public void AtualizarPosicao() throws InterruptedException {
		Mutex.acquire();
		if(direita) {
			x++;
		} else if(esquerda) {
			x--;
		}
		Mutex.release();
		
		if(x+larguraRaquete > Jogo.largura) {
			x = Jogo.largura - larguraRaquete;
		} else if(x < 0) {
			x = 0;
		}
	}
	
	public void Desenhar(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, larguraRaquete, alturaRaquete);
	}
}
