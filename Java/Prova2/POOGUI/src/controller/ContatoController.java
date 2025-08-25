package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Contato;
import Model.Endereco;
import view.ListaContatos;

public class ContatoController {

	ListaContatos contatoView;
	AdicionarController AddController;
	ArrayList<Contato> contatos;
	
	JPanel contatoItemPanel;
	JLabel lblNome, lblTelefone, lblRua, lblNumero, lblCidade, lblEstado;
	Endereco endereco;
	
	private int x;
	private String textField;
	
	public ContatoController(ListaContatos contatoView, AdicionarController AddController) {
		
		this.contatoView = contatoView;
		this.AddController = AddController;
		contatos = AddController.getContatos();
		
		contatoView.Reload(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				contatoView.getReload().setBackground(Color.decode("#64b5f6"));
			}
			public void mouseExited(MouseEvent e) {
				contatoView.getReload().setBackground(null);
			}
			public void mouseClicked(MouseEvent e) {
				if(contatos.isEmpty()) {
					contatoView.getVazio().setVisible(true);
				}
				else {
					contatoView.getVazio().setVisible(false);
					x = 75;
					System.out.println(x);
					contatos.forEach(contato -> {
						lblNome = new JLabel();
						lblTelefone = new JLabel();
						lblRua = new JLabel();
						lblNumero = new JLabel();
						lblCidade = new JLabel();
						lblEstado = new JLabel();
						contatoItemPanel = new JPanel();
						contatoItemPanel.setBounds(43, x, 318, 50);
						contatoItemPanel.setBackground(null);
						contatoItemPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.getColor(null)));
						contatoItemPanel.setLayout(null);
						
						endereco = contato.getEndereco();
						lblNome.setText("Nome: " + contato.getNome());
						lblNome.setBounds(9, 1, 150, 20);
						lblNome.setFont(new Font("Open Sans", Font.PLAIN, 15));
						lblTelefone.setText("Telefone: " + contato.getTelefone());
						lblTelefone.setBounds(105, 1, 160, 20);
						lblTelefone.setFont(new Font("Open Sans", Font.PLAIN, 15));
						lblRua.setText("Rua: " + endereco.getRua());
						lblRua.setBounds(9, 25, 150, 20);
						lblRua.setFont(new Font("Open Sans", Font.PLAIN, 15));
						lblNumero.setText("Numero: " + endereco.getNumero());
						lblNumero.setBounds(230, 1, 150, 20);
						lblNumero.setFont(new Font("Open Sans", Font.PLAIN, 15));
						lblCidade.setText("Cidade: " + endereco.getCidade());
						lblCidade.setBounds(140, 25, 150, 20);
						lblCidade.setFont(new Font("Open Sans", Font.PLAIN, 15));
						lblEstado.setText("Estado: " + endereco.getEstado());
						lblEstado.setBounds(245, 25, 150, 20);
						lblEstado.setFont(new Font("Open Sans", Font.PLAIN, 15));
						
						contatoView.getContatosPanel().repaint();
						contatoView.getContatosPanel().add(contatoItemPanel);
						contatoItemPanel.add(lblNome);
						contatoItemPanel.add(lblTelefone);
						contatoItemPanel.add(lblRua);
						contatoItemPanel.add(lblNumero);
						contatoItemPanel.add(lblCidade);
						contatoItemPanel.add(lblEstado);
						x = x + 60;
					});
				}
			}
		});
		
		contatoView.Search(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				contatoView.getSearchIcon().setBackground(Color.decode("#64b5f6"));
			}
			public void mouseExited(MouseEvent e) {
				contatoView.getSearchIcon().setBackground(null);
			}
			public void mouseClicked(MouseEvent e) {
				if(contatos.isEmpty()) {
					contatoView.getVazio().setVisible(true);
				}
				else {
					contatoView.getVazio().setVisible(false);
					x = 75;
					//System.out.println(x);
					textField = contatoView.getSearch().getText().toLowerCase();
					
					if(contatoItemPanel != null) {
						for(int count = contatoView.getContatosPanel().getComponentCount() - 1; count >= 6; count-- ) {
							contatoView.getContatosPanel().remove(contatoView.getContatosPanel().getComponent(count));
						}
					}
					
					if(textField.isEmpty()) {
					}
					else {
						contatos.stream().filter(i -> i.getNome().toLowerCase().contains(textField)).forEach(contato -> {
							lblNome = new JLabel();
							contatoItemPanel = new JPanel();
							contatoItemPanel.setBounds(43, x, 263, 25);
							contatoItemPanel.setBackground(null);
							contatoItemPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.getColor(null)));
							contatoItemPanel.setLayout(null);
							
							lblNome.setText(contato.getNome());
							lblNome.setBounds(9, 1, 263, 20);
							lblNome.setFont(new Font("Open Sans", Font.PLAIN, 15));
							
							contatoView.getContatosPanel().repaint();
							contatoView.getContatosPanel().add(contatoItemPanel);
							contatoItemPanel.add(lblNome);
							x += 25;
						});
					}
				}
			}
		});
		
		
		
		
//		if(contatos.isEmpty()) {
//			contatoView.getVazio().setVisible(true);
//		}
//		else {
//			contatoView.getVazio().setVisible(false);
//			textField = contatoView.getSearch().getText().toLowerCase();
//			x = 75;
//		contatos.stream().filter(i -> i.getNome().toLowerCase().contains(textField)).forEach(contato -> {
//			lblNome = new JLabel();
//			contatoItemPanel = new JPanel();
//			contatoItemPanel.setBounds(43, x, 263, 25);
//			contatoItemPanel.setBackground(null);
//			contatoItemPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.getColor(null)));
//			contatoItemPanel.setLayout(null);
//			
//			lblNome.setText(contato.getNome());
//			lblNome.setBounds(9, 1, 263, 20);
//			lblNome.setFont(new Font("Open Sans", Font.PLAIN, 15));
//			
//			contatoView.getContatosPanel().repaint();
//			contatoView.getContatosPanel().add(contatoItemPanel);
//			contatoItemPanel.add(lblNome);
//			x += 25;
	}
}
