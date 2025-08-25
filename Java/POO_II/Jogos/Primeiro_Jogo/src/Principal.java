import javax.swing.JFrame;

public class Principal {
	
	public static void main(String[] args) {
		
		Jogo game = new Jogo();
		
		JFrame frame = new JFrame("Ping Pong");
		
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
}
