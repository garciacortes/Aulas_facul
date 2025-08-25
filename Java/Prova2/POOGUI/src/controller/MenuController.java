package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import view.AdicionarView;
import view.ListaContatos;
import view.MenuView;

public class MenuController {

	MenuView view;
	AdicionarView adicionar;
	ListaContatos contatos;
	private int x;
	
	public MenuController(MenuView view, AdicionarView adicionar, ListaContatos contatos) {
		
		this.view = view;
		this.adicionar = adicionar;
		this.contatos = contatos;
		x = 120;
		
		view.btnShow1(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				view.getShow1().setBackground(Color.decode("#64b5f6"));
				view.getShow2().setBackground(Color.decode("#64b5f6"));
				
			}
			public void mouseExited(MouseEvent e) {
				view.getShow1().setBackground(null);
				view.getShow2().setBackground(null);
			}
			public void mouseClicked(MouseEvent e) {
				Thread th = new Thread() {
					@Override
					public void run() {
						try {
							for(int i = 40; i < x; i++) {
								Thread.sleep(1);
								view.getMenuPanel().setSize(i, 377);
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
				};th.start();
				view.getShow1().setVisible(false);
				view.getShow2().setVisible(true);
			}
		});
		
		view.btnShow2(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Thread th = new Thread() {
					@Override
					public void run() {
						try {
							for(int i = x; i >= 40; i--) {
								Thread.sleep(1);
								view.getMenuPanel().setSize(i, 377);
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
				};th.start();
				view.getShow1().setVisible(true);
				view.getShow2().setVisible(false);
			}
			public void mouseEntered(MouseEvent e) {
				view.getShow1().setBackground(Color.decode("#64b5f6"));
				view.getShow2().setBackground(Color.decode("#64b5f6"));
				
			}
			public void mouseExited(MouseEvent e) {
				view.getShow1().setBackground(null);
				view.getShow2().setBackground(null);
			}
		});
		
		view.panelExit(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				view.getMenuExit().setBackground(Color.decode("#64b5f6"));
				
			}
			public void mouseExited(MouseEvent e) {
				view.getMenuExit().setBackground(null);
			}
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		view.panelContato(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				view.getMenuContatos().setBackground(Color.decode("#64b5f6"));
				
			}
			public void mouseExited(MouseEvent e) {
				view.getMenuContatos().setBackground(null);
			}
			public void mouseClicked(MouseEvent e) {
				adicionar.getAdicionarPanel().setVisible(false);
				contatos.getContatosPanel().setVisible(true);
			}
		});
		
		view.panelAdicionar(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				view.getMenuAdicionar().setBackground(Color.decode("#64b5f6"));
			}
			public void mouseExited(MouseEvent e) {
				view.getMenuAdicionar().setBackground(null);
			}
			public void mouseClicked(MouseEvent e) {
				contatos.getContatosPanel().setVisible(false);
				adicionar.getAdicionarPanel().setVisible(true);
			}
		});
				
	}
	
}
