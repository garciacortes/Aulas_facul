import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterrupcaoTeclado extends KeyAdapter {
	
	RaqueteJogador objRaqueteJogador;
	
	public InterrupcaoTeclado(RaqueteJogador obj) {
		objRaqueteJogador = obj;
	}
	
	public void keyPressed(KeyEvent e) {
		try {
			objRaqueteJogador.Mutex.acquire();
			
			if(e.getKeyCode() == KeyEvent.VK_D) {
				objRaqueteJogador.direita = true;
			} else if(e.getKeyCode() == KeyEvent.VK_A) {
				objRaqueteJogador.esquerda = true;
			}
		} catch(InterruptedException el) {
			el.printStackTrace();
		}
		objRaqueteJogador.Mutex.release();
	}
	
	public void keyReleased(KeyEvent e) {
		try {
			objRaqueteJogador.Mutex.acquire();
			
			if(e.getKeyCode() == KeyEvent.VK_D) {
				objRaqueteJogador.direita = false;
			} else if(e.getKeyCode() == KeyEvent.VK_A) {
				objRaqueteJogador.esquerda = false;
			}
		} catch(InterruptedException el) {
			el.printStackTrace();
		}
		objRaqueteJogador.Mutex.release();
	}
}
