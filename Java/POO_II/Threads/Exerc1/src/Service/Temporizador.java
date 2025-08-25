package Service;

import java.util.Timer;

import javax.swing.JLabel;

public class Temporizador extends Timer {

	private Timer timer;
	private Relogio relogio;
	
	public Temporizador(JLabel lblTimer) {
		timer = new Timer();
		relogio = new Relogio(lblTimer);
		timer.schedule(relogio, 0, 1000);
	}
}
