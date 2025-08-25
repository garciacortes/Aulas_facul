package Model;

import java.util.Scanner;

import View.MenuView;

public class Arvore {
	public No Raiz;
    public int Altura;
    Scanner scan = new Scanner(System.in);
	private MenuView menu;
    
    public Arvore() {
        Raiz = null;
        Altura = 0;
        this.menu = null;
    }
    
    public void setMenu(MenuView menu) {
		this.menu = menu;
	}

	public void InsereNovoNo(int valor) {
        No NovoNo = new No(valor);
        NovoNo.profundidade = 0;
        
        if(Raiz == null) {
            Raiz = NovoNo;
        } 
        else {
            No ponto_insercao;
            No Busca;
            
            Busca = this.Raiz;
            ponto_insercao = Busca;
            
            while(Busca != null) {
                ponto_insercao = Busca;
                NovoNo.profundidade = NovoNo.profundidade + 1;
                
                if(valor < Busca.valor) {
                    Busca = Busca.no_esquerda;
                }
                else if(valor > Busca.valor) {
                    Busca = Busca.no_direita;
                }
                else {
                    ponto_insercao = null;
                    Busca = null;
                }
            }
            
            if(ponto_insercao != null) {
                if(valor < ponto_insercao.valor) {
                    ponto_insercao.no_esquerda = NovoNo;
                }
                else {
                    ponto_insercao.no_direita = NovoNo;
                }
            }
        }
    }
    
    public boolean ProcuraValor(int valor) {
        boolean achou;
        achou = false;
        
        if(Raiz == null) {
            return false;
        }
        else {
            No Busca = Raiz;
            while((!achou) && (Busca != null)) {
                if(Busca.valor == valor) {
                    achou = true;
                }
                else if(valor < Busca.valor) {
                    Busca = Busca.no_esquerda;
                }
                else {
                    Busca = Busca.no_direita;
                }
            }
            return achou;
        }
    }
    
    public void ImprimeArvore(No raiz, int i) {
    	if(i == 1) {
    		if(raiz != null) {
    			System.out.print("Direita -> " + raiz.valor + "\n");
    		}
    		else System.out.print("Direita -> Null\n");
    	}
    	else if(i == 2) {
    		if(raiz != null) {
    			System.out.print("Esquerda -> " + raiz.valor + "\n");
    		}
    		else System.out.print("Esquerda -> Null\n");
    	}
    	else if(i == 3) {
    		if(raiz != null) {
    			System.out.print("Raiz -> " + raiz.valor + "\n");
    		}
    		else System.out.print("Raiz -> Null\n");
    	}
    	if(raiz != null) {
    		System.out.print("\nAtual -> " + raiz.valor);
    		scan.nextLine();
            ImprimeArvore(raiz.no_esquerda, 2);
            //System.out.print(raiz.valor + " ");
            System.out.print("\nAtual -> " + raiz.valor);
    		scan.nextLine();
            ImprimeArvore(raiz.no_direita, 1);
        }
    }
    
    public void ImprimeArvoreCrescente(No raiz) {
    	if(raiz != null) {
            ImprimeArvoreCrescente(raiz.no_esquerda);
            menu.getAreaLog().append(raiz.valor + " ");
            ImprimeArvoreCrescente(raiz.no_direita);
        }
    }
    
    public void ImprimeArvoreDecrescente(No raiz) {
        if(raiz != null) {
            ImprimeArvoreDecrescente(raiz.no_direita);
            menu.getAreaLog().append(raiz.valor + " ");
            ImprimeArvoreDecrescente(raiz.no_esquerda);
        }
    }

    public int ContaElemento(No nozinho) {
        int conta = 0;
        
        if(nozinho != null) {
            conta = ContaElemento(nozinho.no_esquerda) + ContaElemento(nozinho.no_direita) + 1;
            return conta;
        }
        else {
            return 0;
        }
    }
    
    public int SomaElemento(No nozinho) {
        int soma = 0;
        
        if(nozinho != null) {
            soma = SomaElemento(nozinho.no_esquerda) + SomaElemento(nozinho.no_direita) + nozinho.valor;
            return soma;
        }
        else {
            return 0;
        }
    }
    
    public int MaiorElemento(No nozinho) {
        int maior = -99999;
        
        if(nozinho != null) {
            if(nozinho.valor > maior) {
                maior = nozinho.valor;
            }
            MaiorElemento(nozinho.no_esquerda);
            MaiorElemento(nozinho.no_direita);
            
            return maior;
        }
        else {
            return 0;
        }
    }
    
    public int MenorElemento(No nozinho) {
        int menor = 9999999;
        
        if(nozinho != null) {
            if(nozinho.valor < menor) {
                menor = nozinho.valor;
            }
            MenorElemento(nozinho.no_esquerda);
            MenorElemento(nozinho.no_direita);
            
            return menor;
        }
        else {
            return 0;
        }
    }
    
    public void ImprimeFolha(No raiz) {
        if(raiz != null) {
            this.ImprimeFolha(raiz.no_esquerda);
            
            if((raiz.no_direita == null) && (raiz.no_esquerda == null)) {
                menu.getAreaLog().append(raiz.valor + " | ");
            }
            
            this.ImprimeFolha(raiz.no_direita);
        }
    }
    
    public void ImprimeNaoFolha(No raiz) {
        if(raiz != null) {
            ImprimeNaoFolha(raiz.no_esquerda);
            
            if((raiz.no_direita != null ) || (raiz.no_esquerda != null)) {
                menu.getAreaLog().append(raiz.valor + " | ");;
            }
            
            ImprimeNaoFolha(raiz.no_direita);
        }
    }

    public int Altura(No nozinho) {
        if(nozinho != null) {
            if(nozinho.profundidade > this.Altura) {
                this.Altura = nozinho.profundidade;
            }
            Altura(nozinho.no_esquerda);
            Altura(nozinho.no_direita);
            
            return this.Altura;
        }
        else {
            return 0;
        }
    }
    
    public void RemoveNo(int chave) {
        No p = null;
        No v = null;
        No t = null;
        No s = null;
        No anterior = null;
        int achou;
        
        p = this.Raiz;
        achou = 0;
        
        while ((p != null) && (achou != 1)) {
            if(chave == p.valor) {
                achou = 1;
            }
            else {
                anterior = p;
                if(chave < p.valor) {
                    p = p.no_esquerda;
                }
                else {
                    p = p.no_direita;
                }
            }
        }
        
        if(achou == 1) {
            if(p.no_esquerda == null) {
                v = p.no_direita;
            }
            else {
                if(p.no_direita == null) {
                    v = p.no_esquerda;
                }
                else {
                    t = p;
                    v = p.no_direita;
                    s = v.no_esquerda;
                    while(s != null) {
                        t = v;
                        v = s;
                        s = v.no_esquerda;
                    }
                    if(t != p) {
                        t.no_esquerda = v.no_direita;
                        v.no_direita = p.no_direita;
                    }
                    v.no_esquerda = p.no_esquerda;
                }
            }
            
           if(anterior == null) {
        	   this.Raiz = v;
           }
           else if(p == anterior.no_esquerda) {
                anterior.no_esquerda = v;
           }
           else {
                anterior.no_direita = v;
           }
        }
    }
}
