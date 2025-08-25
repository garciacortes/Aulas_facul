package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Model.MenuModel;

public class MenuView {
	
	private JPanel menuPanel, menuExit, menuAgenda, menuAdd;
	private JLabel show1, show2, exit, exitText, agendaText, agenda, adicionar, addText;
	
	MenuModel model;
	
	public MenuView() {
		model = new MenuModel();
		
		menuPanel = new JPanel();
		menuExit = new JPanel();
		menuAgenda = new JPanel();
		menuAdd = new JPanel();
		show1 = new JLabel();
		show2 = new JLabel();
		exit = new JLabel();
		exitText = new JLabel();
		agenda = new JLabel();
		agendaText = new JLabel();
		agendaText = new JLabel();
		adicionar = new JLabel();
		addText = new JLabel();
		
		menuPanel.setBounds(0, 0, 40, 377);
		menuPanel.setBackground(model.getMenuColor());
		menuPanel.setLayout(null);
		
		
		Border bShow = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.getColor(null)),
                BorderFactory.createEmptyBorder(3, 4, 2, 2));
		show1.setIcon(model.getIconShow());
		show1.setBounds(4, 2, 30, 30);
		show1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		show1.setOpaque(true);
		show1.setBackground(null);
		show1.setBorder(bShow);
		
		show2.setIcon(model.getIconShow());
		show2.setBounds(4, 2, 30, 30);
		show2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		show2.setOpaque(true);
		show2.setBackground(null);
		show2.setBorder(bShow);
		show2.setVisible(false);
		
		menuExit.setBackground(null);
		menuExit.setBounds(4, 155, 80, 40);
		menuExit.setLayout(null);
		menuExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		exit.setIcon(model.getIconExit());
		exit.setBounds(7, 4, 33, 33);
		exit.setOpaque(true);
		exit.setBackground(null);
		
		exitText.setBackground(null);
		exitText.setBounds(45, 0, 35, 40);
		exitText.setText("Sair");
		exitText.setOpaque(true);
		exitText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		menuAgenda.setBackground(null);
		menuAgenda.setBounds(4, 55, 110, 40);
		menuAgenda.setLayout(null);
		menuAgenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		agenda.setIcon(model.getIconContato());
		agenda.setBounds(3, 4, 33, 33);
		agenda.setOpaque(true);
		agenda.setBackground(null);
		
		agendaText.setBackground(null);
		agendaText.setBounds(45, 0, 65, 40);
		agendaText.setText("Contatos");
		agendaText.setOpaque(true);
		agendaText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		menuAdd.setBackground(null);
		menuAdd.setBounds(4, 105, 110, 40);
		menuAdd.setLayout(null);
		menuAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		adicionar.setIcon(model.getIconAdd());
		adicionar.setBounds(3, 4, 33, 33);
		adicionar.setOpaque(true);
		adicionar.setBackground(null);
		
		addText.setBackground(null);
		addText.setBounds(45, 0, 65, 40);
		addText.setText("Contatos");
		addText.setOpaque(true);
		addText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		menuPanel.add(show1);
		menuPanel.add(show2);
		menuPanel.add(menuExit);
		menuPanel.add(menuAgenda);
		menuPanel.add(menuAdd);
		menuExit.add(exit);
		menuExit.add(exitText);
		menuAgenda.add(agenda);
		menuAgenda.add(agendaText);
		menuAdd.add(adicionar);
		menuAdd.add(addText);
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}
	
	public JLabel getShow1() {
		return show1;
	}
	
	public JLabel getShow2() {
		return show2;
	}
	
	public JPanel getMenuExit() {
		return menuExit;
	}
	
	public JPanel getMenuAdicionar() {
		return menuAdd;
	}
	
	public JPanel getMenuContatos() {
		return menuAgenda;
	}
	
	public void btnShow1(MouseAdapter actions) {
		show1.addMouseListener(actions);
	}
	
	public void panelExit(MouseAdapter actions) {
		menuExit.addMouseListener(actions);
	}
	
	public void panelContato(MouseAdapter actions) {
		menuAgenda.addMouseListener(actions);
	}
	
	public void panelAdicionar(MouseAdapter actions) {
		menuAdd.addMouseListener(actions);
	}
	
	public void btnShow2(MouseAdapter actions) {
		show2.addMouseListener(actions);
	}
}
