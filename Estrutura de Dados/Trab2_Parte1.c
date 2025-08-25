#include <stdio.h>
#include <stdlib.h>
#include <iso646.h>
#include <windows.h>

#define PILHA_VAZIA "\nA pilha esta vazia!\n"
#define PILHA_CHEIA "A pilha esta cheia!\n"

typedef struct peca Pecas;

struct peca {
    int topo;
    int qtd;
    float *pPeca;
};

void criarTeste(Pecas *p, int qtdePos){
    p->topo = -1;
    p->qtd = qtdePos;
    p->pPeca = (float*) malloc (qtdePos * sizeof(float));
}

int notebookVazio (Pecas *p){
    if(p->topo == -1)
        return 1;
    else
        return 0;
}

int notebookCompleto (Pecas *p){
    int retorno = 0;

    if (p->topo == p->qtd - 1)
        retorno = 1;

    return retorno;
}

void inserirPeca(Pecas *p, float num){
    if (notebookCompleto(p) == 1) {
        printf(PILHA_CHEIA);
    } else {
        p->topo++;
        p->pPeca[p->topo] = num;
        printf("Empilhando peca %.2f\n",num);
    }
}

void removerPeca(Pecas *p){
    if (!notebookVazio(p)) {
        printf("\nDesempilhando peca: %.2f\n",p->pPeca[p->topo]);
        p->topo--;
    } else {
        printf(PILHA_VAZIA);
    }
}

void exibirTopo(Pecas *p ){
    if (!notebookVazio(p)) {
        printf("\nTOPO => Posicao: %d | Valor: %.2f\n", p->topo, p->pPeca[p->topo]);
    } else {
        printf(PILHA_VAZIA);
    }
}

void exibirValoresControle(Pecas *p){
    printf("\nTOPO: %d | Qtde: %d\n", p->topo, p->qtd);

}

void desalocarPilha(Pecas *p) {
    free(p->pPeca);
}

int main() {
    Pecas *testePecas;
    int Opcao, Peca;

    //aloca um ponteiro para o struct
    testePecas = (Pecas*) malloc (sizeof(Pecas));

    int qtd;

    printf("\nInforme a quantidade de pecas do notebook? ");
    scanf("%d", &qtd);
    //inicializa a pilha pPecas
    criarTeste(testePecas, qtd);

    do {
        system("cls");
        exibirTopo(testePecas);
        printf("\n");
        printf("---------------MENU---------------\n");
        printf("1-Inserir Peca\n");
        printf("2-Remover Peca\n");
        printf("3-Exibir Topo\n");
        printf("4-Exibir Valores Controle\n");
        printf("5-Sair\n");
        printf("----------------------------------\n");
        printf("Digite a Opcao Desejada: ");
        scanf("%d", &Opcao);
        printf("\n");

        switch(Opcao) {
            case 1:
                printf("Digite o Valor da Peca: ");
                scanf("%d", &Peca);
                inserirPeca(testePecas, Peca);
                Sleep(650);
                break;
            case 2:
                printf("Removendo Peca: ");
                removerPeca(testePecas);
                printf("\n");
                system("pause");
                break;
            case 3:
                printf("Exibindo Topo:");
                exibirTopo(testePecas);
                printf("\n");
                system("pause");
                break;
            case 4:
                printf("Valores de Controle: ");
                exibirValoresControle(testePecas);
                printf("\n");
                system("pause");
                break;
            case 5:
                printf("Desalocando Pilha\n");
                desalocarPilha(testePecas);
                free(testePecas);
                printf("Pilha Desalocada, Saindo");
                exit(1);
                break;

        }

    }while(1);

    return 0;
}
