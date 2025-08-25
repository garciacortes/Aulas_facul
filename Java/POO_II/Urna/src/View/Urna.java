package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Urna extends JFrame  {
	
	private JLabel lblCandidatos, lblVoto, lblTotal, lblBrancos, lblVotoCand1, lblVotoCand2, lblAviso, lblTotalVotos, lblTotalBrancos, lblTotalCand1, lblTotalCand2;
	private Container ctn;
	private JPanel pnCandidatos;
	private JButton btnVotar,  btnApurar;
	private JTextField txtVoto;
	private final String[] candidatos = {"Joaquim Santos", "Karina Constas", "Voto em Branco"};
	
	private Votacao objVotos;
	
	private static final long serialVersionUID = 1L;
	
	public Urna() {
		setSize(320, 430);
		setTitle("Urna Eletronica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);;
		
		ctn = getContentPane();
		ctn.setLayout(null);
		
		lblCandidatos = new JLabel("Candidatos");
		lblCandidatos.setBounds(10, 10, 100, 25);
		
		lblVoto = new JLabel("Voto: ");
		lblVoto.setBounds(10, 80, 100, 25);
		txtVoto = new JTextField();
		txtVoto.setBounds(43 ,80, 23, 23);
		
		pnCandidatos = new JPanel();
		pnCandidatos.setBounds(150, 10, 150, 60);
		GridBagLayout layout = new GridBagLayout();
		pnCandidatos.setLayout(layout);
		GridBagConstraints gbc =  new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		for(int i  = 0; i < candidatos.length; i ++) {
			gbc.weighty = 0.5;
			gbc.gridx = 0;
			pnCandidatos.add(new JLabel(( i + 1) + " - " + candidatos[i]), gbc);
		}
		
		btnVotar = new JButton("Votar");
		btnVotar.setBounds(190, 85, 65, 23);
		
		btnApurar = new JButton("Apuração dos Votos");
		btnApurar.setBounds(25, 195, 250 ,25);
				
		lblAviso = new JLabel("Digite o seu voto!!!");
		lblAviso.setBounds(10, 105, 200, 25);
		lblAviso.setVisible(false);
		
		lblTotal = new JLabel("Total de votos");
		lblTotal.setBounds(10, 280, 250, 25);
		lblTotalVotos = new JLabel("--");
		lblTotalVotos.setBounds(170, 280, 30, 25);
		
		lblBrancos = new JLabel("Total em brancos");
		lblBrancos.setBounds(10, 300, 250, 25);
		lblTotalBrancos = new JLabel("--");
		lblTotalBrancos.setBounds(170, 300, 30, 25);
		
		lblVotoCand1 = new JLabel("Votos em candidato 1");
		lblVotoCand1.setBounds(10, 320, 250, 25);
		lblTotalCand1 = new JLabel("--");
		lblTotalCand1.setBounds(170, 320, 30, 25);
		
		lblVotoCand2 = new JLabel("Votos em candidato 2");
		lblVotoCand2.setBounds(10, 340, 250 ,25);
		lblTotalCand2 = new JLabel("--");
		lblTotalCand2.setBounds(170, 340, 30, 25);
		
		
		ctn.add(lblCandidatos);
		ctn.add(lblVoto);
		ctn.add(pnCandidatos);
		ctn.add(lblAviso);
		ctn.add(lblVoto);
		ctn.add(lblTotal);
		ctn.add(lblBrancos);
		ctn.add(lblVotoCand1);
		ctn.add(lblVotoCand2);
		ctn.add(lblTotalVotos);
		ctn.add(lblTotalBrancos);
		ctn.add(lblTotalCand1);
		ctn.add(lblTotalCand2);
		ctn.add(btnVotar);
		ctn.add(btnApurar);
		ctn.add(txtVoto);
		
		setVisible(true);
		
		objVotos = new Votacao();
		
		btnVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtVoto.getText().equals("")) {
					lblAviso.setVisible(true);
				} else {
					int escolha = Integer.parseInt(txtVoto.getText());
					int resultado = objVotos.Votar(escolha);
					lblAviso.setVisible(true);
					
					TimerTask task = new TimerTask() {
						public void run() {
							lblAviso.setVisible(false);
							txtVoto.setText(null);
						}
					};
					Timer timer = new Timer("Timer");
					timer.schedule(task, 1500);
					
					if(resultado == 1) {
						lblAviso.setText("Voto computado com suceeso!");
					} else {
						lblAviso.setText("Candidato Invalido!");
					}
				}
			}
		});
		
		btnApurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTotalVotos.setText(Integer.toString(objVotos.getTotal()));
				lblTotalBrancos.setText(Integer.toString(objVotos.getBranco()));
				lblTotalCand1.setText(Integer.toString(objVotos.getCand1()));
				lblTotalCand2.setText(Integer.toString(objVotos.getCand2()));
			}
		});
	}
}
