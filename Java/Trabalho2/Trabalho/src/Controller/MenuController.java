package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Arvore;
import Model.No;
import View.MenuView;

public class MenuController {
	
	private JTextField txtValor, txtValor2;
	private JLabel lbl;
	private int x, y;
	private MenuView menu;
	
	public MenuController(MenuView menu, Arvore model) {
		
		this.menu = menu;
		
		menu.MenuSair(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menu.Adicionar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtValor = menu.getTextField();
				if(txtValor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não foi digitado nenhum Valor");
					txtValor.requestFocus();
				}
				else {
					model.InsereNovoNo(Integer.valueOf(txtValor.getText()));
					txtValor.setText(null);
					txtValor.requestFocus();
					//model.ImprimeArvoreCrescente(model.Raiz);
				}
			}
		});
		menu.Desenhar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x = 200;
				y = 0;
				desenharArvore(model.Raiz, x, y, 0);
			}
		});
		
		txtValor2 = menu.getTextField2();
		menu.ArvoreCrescente(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getAreaLog().setText(null);
				model.ImprimeArvoreCrescente(model.Raiz);
			}
		});
		menu.ArvoreDecrescente(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getAreaLog().setText(null);
				model.ImprimeArvoreDecrescente(model.Raiz);
			}
		});
		menu.ContarElemento(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Contar;
				Contar = model.SomaElemento(model.Raiz);
				menu.getAreaLog().setText("A quantidade de nós: " + Contar);
			}
		});
		menu.MenorElemento(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Menor;
				Menor = model.SomaElemento(model.Raiz);
				menu.getAreaLog().setText("O menor elemento: " + Menor);
			}
		});
		menu.MaiorElemento(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Maior;
				Maior = model.SomaElemento(model.Raiz);
				menu.getAreaLog().setText("O maior elemento: " + Maior);
			}
		});
		menu.SomarElemento(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soma;
				soma = model.SomaElemento(model.Raiz);
				menu.getAreaLog().setText("A soma dos nòs: " + soma);
			}
		});
		menu.ImprimeFolha(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getAreaLog().setText(null);
				menu.getAreaLog().append("Impressão Folhas: ");
				model.ImprimeFolha(model.Raiz);
			}
		});
		menu.ImprimeNaoFolha(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getAreaLog().setText(null);
				menu.getAreaLog().append("Impressão nãoFolhas: ");
				model.ImprimeNaoFolha(model.Raiz);
			}
		});
		menu.Profundidade(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getAreaLog().setText(null);
				ImprimeProfundidade(model.Raiz);
			}
		});
		menu.Altura(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int altura = model.Altura(model.Raiz);
				if(txtValor2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não foi digitado nenhum Valor");
					txtValor2.requestFocus();
				}
				else {
					menu.getAreaLog().setText("A altura do valor procurado: " + altura + "!");
				}
			}
		});
		menu.RemocaoNo(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtValor2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não foi digitado nenhum Valor");
					txtValor2.requestFocus();
				}
				else {
					model.RemoveNo(Integer.valueOf(txtValor2.getText()));
					txtValor2.setText(null);
					txtValor2.requestFocus();
					
				}
			}
		});
		menu.ProcurarValor(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtValor2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não foi digitado nenhum Valor");
					txtValor2.requestFocus();
				}
				else {
					if(model.ProcuraValor(Integer.valueOf(txtValor2.getText()))) {
						menu.getAreaLog().setText("Valor Encontrado!\n");
					}
					else {
						menu.getAreaLog().setText("Valor não Encontrado!\n");
					}
				}
			}
		});
	}
	
	public void desenharArvore(No raiz, int x, int y, int lado) {
		if(raiz != null) {
			if(raiz.profundidade < 2) {
				if(lado == 1) {
					x = x + 90;
					y = y + 40;
				}
				if(lado == 2) {
					x = x - 90;
					y = y + 40;
				}
			}
			else if (raiz.profundidade < 3) {
				if(lado == 1) {
					x = x + 65;
					y = y + 40;
				}
				if(lado == 2) {
					x = x - 65;
					y = y + 40;
				}
			}
			else if (raiz.profundidade > 2) {
				if(lado == 1) {
					x = x + 35;
					y = y + 40;
				}
				if(lado == 2) {
					x = x - 35;
					y = y + 40;
				}
			}
			System.out.println(raiz.valor);
			lbl = new JLabel();
    		lbl.setVisible(true);
    		lbl.setText(String.valueOf(raiz.valor));
    		lbl.setBounds(x, y, 20, 15);
    		menu.getPanel_1().add(lbl);
    		menu.getPanel_1().repaint();
            desenharArvore(raiz.no_esquerda, x, y, 2);
            desenharArvore(raiz.no_direita, x, y, 1);
        }
	}
	
	public void ImprimeProfundidade(No raiz) {
        if(raiz != null) {
            this.ImprimeProfundidade(raiz.no_esquerda);
            menu.getAreaLog().append("No: " + raiz.valor + " profundidade: " + raiz.profundidade + "\n");
            this.ImprimeProfundidade(raiz.no_direita);
        }
    }
}
