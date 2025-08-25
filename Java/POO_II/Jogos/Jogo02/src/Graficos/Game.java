package Graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static int contadorTempo = 100;
	public static int pontuacao = 0;
	public static int mouse_x, mouse_y;
	public static boolean mouseClicado = false;
	public boolean JogoAcabou = false;
	public final static int WIDTH = 640;
	public final int HEIGHT = 480;
	public final int SCALE = 1;
	
	private BufferedImage image;
	
	public Particula particula;
	public Retangulo retangulo;
	public Gerador objGerador;
	
	public Game() {
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		this.addMouseListener(new MouseEvents());
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		objGerador = new Gerador();
	}
	
	public void initFrame() {
		frame = new JFrame("GAME ENGINE");
		frame.add(this);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
	}

	public static void main(String[] args) {
		Game game  = new Game();
		game.start();
	}
	
	public void tick() {
		if(!JogoAcabou) {
			objGerador.tick();
			if(contadorTempo <= 0) {
				JogoAcabou = true;
			}
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		if(!JogoAcabou) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Pontos: " + pontuacao, 10, 40);
			g.setColor(Color.green);
			g.fillRect(WIDTH/2-100-70, 20, contadorTempo*3, 20);
			g.setColor(Color.white);
			g.drawRect(WIDTH/2-100-70, 20, 300, 20);
			objGerador.render(g);
		} else {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("GAME OVER!", WIDTH/2-100, HEIGHT/2);
		}
		bs.show();
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		final double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
		}
		
		stop();
	}

}