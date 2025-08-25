package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Contato;
import Model.Endereco;
import view.AdicionarView;

public class AdicionarController {
	
	AdicionarView adicionar;
	String nome, telefone, rua, numero, cidade, estado;
	Contato contato;
	Endereco endereco;

	ArrayList<Contato> contatos = new ArrayList<Contato>();

	public AdicionarController(AdicionarView adicionar) {
		
		this.adicionar = adicionar;
		
		adicionar.Limpar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar.getNomeText().setText(null);
				adicionar.getTelefoneText().setText(null);
				adicionar.getRuaText().setText(null);
				adicionar.getNumeroText().setText(null);
				adicionar.getCidadeText().setText(null);
				adicionar.getEstadoText().setText(null);
			}
		});
		
		adicionar.SalvarContato(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome = adicionar.getNomeText().getText();
				telefone = adicionar.getTelefoneText().getText();
				rua = adicionar.getRuaText().getText();
				numero = adicionar.getNumeroText().getText();
				cidade = adicionar.getCidadeText().getText();
				estado = adicionar.getEstadoText().getText();
				
				Endereco endereco = new Endereco(rua, numero, cidade, estado);
				contato = new Contato(telefone, nome, endereco);
				contatos.add(contato);
				JOptionPane.showMessageDialog(null, "Contato Adicionado Com Sucesso!");
				
				adicionar.getNomeText().setText(null);
				adicionar.getTelefoneText().setText(null);
				adicionar.getRuaText().setText(null);
				adicionar.getNumeroText().setText(null);
				adicionar.getCidadeText().setText(null);
				adicionar.getEstadoText().setText(null);
			}
		});
	}
	
	public ArrayList<Contato> getContatos() {
		return contatos;
	}
}
