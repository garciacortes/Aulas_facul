import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Jogo extends Canvas implements KeyListener, Runnable{
	
	public static int largura = 160;
	public static int altura = 120;
	public static int escala = 3;
	public BufferedImage TelaJogo = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
	
	public static RaqueteJogador objRaqueteJogador;
	public static Bola objBola;
	
	private static final long serialVersionUID = 1L;
	
	public Jogo() {
		this.setPreferredSize(new Dimension(largura * escala, altura * escala));
		objRaqueteJogador = new RaqueteJogador(100, altura - 5);
		objBola = new Bola(100, altura / 2 - 1);
		setFocusable(true);
		this.addKeyListener(new InterrupcaoTeclado(objRaqueteJogador));
	}
	
	public void AtualizarposicoesObjeto() throws InterruptedException {
		objRaqueteJogador.AtualizarPosicao();
		objBola.AtualizarPosicao();
	}
	
	public void DesenharJogoTela() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//desenha os objs do jogo no backbuffer
		Graphics g = TelaJogo.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, largura, altura);
		objRaqueteJogador.Desenhar(g);
		objBola.Desenhar(g);
		
		//transfere do backbuffer -> frontbuffer
		g = bs.getDrawGraphics();
		g.drawImage(TelaJogo, 0, 0, largura * escala, altura * escala, null);
		bs.show();
	}
	
	public void run() {
		while(true) {
			try {
				AtualizarposicoesObjeto();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			DesenharJogoTela();
			try {
				Thread.sleep(15);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
