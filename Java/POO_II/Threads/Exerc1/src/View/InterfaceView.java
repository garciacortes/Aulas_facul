package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.InterfaceController;
import Service.Temporizador;

public class InterfaceView extends JFrame {
	
	private JTextArea txtProducer, txtConsumer;
	private JButton btnStart, btnStop;
	private JLabel lblTimer, lblProducer, lblConsumer;
	private JScrollPane scrollProducer, scrollConsumer;
	private Container ctn;
	
	InterfaceController iController;
	
	private static final long serialVersionUID = 1L;

	public InterfaceView( ) {
		this.iController = new InterfaceController();
		
		setSize(310,580);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ctn = getContentPane();
		ctn.setLayout(null);
		
		lblProducer = new JLabel("Gerados");
		lblProducer.setBounds(55, 0, 75, 50);
		txtProducer  = new JTextArea();
		scrollProducer = new JScrollPane(txtProducer);
		scrollProducer.setBounds(30, 33, 100, 400);
		
		lblConsumer = new JLabel("Processados");
		lblConsumer.setBounds(175, 0, 100, 50);
		txtConsumer = new JTextArea();
		scrollConsumer = new JScrollPane(txtConsumer);
		scrollConsumer.setBounds(165, 33, 100, 400);
		
		lblTimer = new JLabel("00:00:00");
		lblTimer.setBounds(117, 450, 100, 30);
		
		btnStart = new JButton("Iniciar");
		btnStart.setBounds(63, 485, 75, 30);
		
		btnStop = new JButton("Parar");
		btnStop.setBounds(150, 485, 75, 30);
		
		ctn.add(lblProducer);
		ctn.add(scrollProducer);
		ctn.add(lblConsumer);
		ctn.add(scrollConsumer);
		ctn.add(lblTimer);
		ctn.add(btnStart);
		ctn.add(btnStop);
		
		setVisible(true);
		
		new Temporizador(lblTimer);
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProducer.setText("");
				txtConsumer.setText("");
				iController.Iniciar(txtProducer, txtConsumer);
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iController.Parar();
			}
		});
	}
}
