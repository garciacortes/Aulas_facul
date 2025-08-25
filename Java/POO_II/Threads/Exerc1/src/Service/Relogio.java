package Service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Relogio extends TimerTask {
	
	private JLabel lblTimer;
	int hora = 0;
	int min = 0;
	int sec = 0;
	LocalTime tempo = LocalTime.of(0, 0, 0);
	
	public Relogio(JLabel lbl) {
		lblTimer = lbl;
	}
	
	public void run() {
		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
			lblTimer.setText(formato.format(tempo));
			tempo = tempo.plusSeconds(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
