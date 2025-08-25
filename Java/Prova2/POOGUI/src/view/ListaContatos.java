package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Model.ContatoModel;

public class ListaContatos {
	
	MenuView menu = new MenuView();
	ContatoModel model = new ContatoModel();
			
	JPanel contatosPanel, contato;
	JLabel lbl, lblNome, reload, vazio, searchIcon;
	JTextField search;
	
	
	public ListaContatos() {
		
		contatosPanel = new JPanel();
		contato = new JPanel();
		lbl = new JLabel();
		lblNome = new JLabel();
		reload = new JLabel();
		vazio = new JLabel();
		searchIcon = new JLabel();
		search = new JTextField();
		
		contatosPanel.setBounds(40, 0, 326, 416);
		contatosPanel.setBackground(null);
		contatosPanel.setVisible(true);
		contatosPanel.setLayout(null);
		
		lbl.setText("Contatos");
		lbl.setBounds(165, 0, 75, 40);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		vazio.setText("Lista de Contatos Vazia");
		vazio.setBounds(125, 65, 150, 46);
		vazio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		vazio.setVisible(false);
		
		Border bReload = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.getColor(null)),
                BorderFactory.createEmptyBorder(0, 5, 0, 0));
		reload.setIcon(model.getIconReload());
		reload.setBounds(260, 8, 30, 30);
		reload.setOpaque(true);
		reload.setBorder(bReload);
		reload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		Border bSearch = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.getColor(null)),
                BorderFactory.createEmptyBorder(1, 1, 0, 0));
		searchIcon.setIcon(model.getIconSearch());
		searchIcon.setBounds(210, 43, 25, 25);
		searchIcon.setOpaque(true);
		searchIcon.setBorder(bSearch);
		searchIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		search.setBounds(43, 45, 160, 20);
		search.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		contatosPanel.add(menu.getMenuPanel());
		contatosPanel.add(lbl);
		contatosPanel.add(reload);
		contatosPanel.add(vazio);
		contatosPanel.add(search);
		contatosPanel.add(searchIcon);
	}

	public JLabel getVazio() {
		return vazio;
	}

	public JPanel getContatosPanel() {
		return contatosPanel;
	}
	
	public JLabel getReload() {
		return reload;
	}

	public JTextField getSearch() {
		return search;
	}
	
	public JLabel getSearchIcon() {
		return searchIcon;
	}

	public void Reload(MouseAdapter actions) {
		reload.addMouseListener(actions);
	}
	
	public void Search(MouseAdapter actions) {
		searchIcon.addMouseListener(actions);
	}
}
