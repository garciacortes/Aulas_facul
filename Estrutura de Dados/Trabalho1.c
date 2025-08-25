#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define TAMANHO_STRING 40

typedef struct produto listaProdutos;

struct produto
{
    int codigo;
    char nome[TAMANHO_STRING];
    struct produto* prox;
};

char* obtemString()
{
    char* nome;

    //aloca TAMANHO_STRING caracteres para o nome
    nome = malloc(TAMANHO_STRING * sizeof(char));

    //limpa o buffer de entrada
    fflush(stdin);

    //obtem a string desejada
    nome = fgets(nome, TAMANHO_STRING, stdin);

    //outra opcao seria, porem apresenta o problema de estouro de buffer
    //nome = gets(nome);

    //remove a quebra de linha inserida pelo fgets
    for (int i = 0; i < strlen(nome); i++)
    {
        if (nome[i] == '\n')
            nome[i] = '\0';
    }

    return nome;
}

void pausa()
{
    //funcao utilizada todas as vezes que for exibido uma mensagem e for necesario pausar a execucao do
    //algoritmo para que o usuario tenha tempo para visualizar a mensagem
    printf("\nPressione <ENTER> para continuar ...");
    fflush(stdin);
    //getchar obriga o usuario a digitar um ENTER para continuar a execucao
    getchar();
}

listaProdutos* criaProduto()
{
    int codProd;
    char* nome; //trataremos as strings como ponteiros pois utilizaremos a funcao obtemString para realizar a leitura

    //obtem os dados para o novo no
    printf("Informe o codigo do Produto: ");
    scanf("%d", &codProd);

    printf("Informe o nome do Produto:  ");
    nome = obtemString(); //criado uma funcao especifica para ler uma estring


    //obtem um endereço para o novo no
    listaProdutos* novoProduto = malloc(sizeof(listaProdutos));
    //preenche o novo no com os dados obtidos
    novoProduto->codigo = codProd;
    strcpy(novoProduto->nome, nome); //strcpy deve ser utilizada todas as vezes para armazenar dados em uma variavel do tipo string (vetor de char)

    return novoProduto;
}

listaProdutos* inserir(listaProdutos* lp)
{
    //armazenar o endereço do no que esta sendo criado
    listaProdutos* novoProduto;

    //chama que pegar o endereço de memoria livre e armazenar os dados do novo novo
    novoProduto = criaProduto();

    //encadeamento do no que esta sendo criado agora com o no anterior
    novoProduto->prox = lp;

    //como foi criado um novo no o inicio da lista deve apontar para este novo endereco
    return novoProduto;
}

listaProdutos* removeFim(listaProdutos* lp)
{
    listaProdutos* no = lp;
    listaProdutos* penultimoProduto;

    if (lp == NULL)
    {
        printf("A lista esta vazia!");
        pausa();
    }
    else
    {

        //navega ate o penultimo elemento considerando que o ultimo e nullo
        while (no->prox != NULL)
        {
            //armazena o endereco do no anterior ao no atual. mantem defasado uma posicao em relacao a variavel no
            penultimoProduto = no;
            no = no->prox;
        }

        //se a variavel no for igual a lp mesmo apos a execucao do laco anterior
        //significa que a lista possui apenas um no
        if (no == lp)
        {
            //remove o no
            free(lp);
            //faz lp conter null para ser retornada pelo return
            lp = NULL;
        }
        else
        {
            //a lista tem mais de um no
            //faz o pemultimo no apontar para null
            penultimoProduto->prox = NULL;
            //desaloca o atual ultimo no
            free(no);
        }
    }

    return lp;
}

listaProdutos* removeTudo(listaProdutos* lp)
{
    while (lp != NULL)   //percorre todos os elementes ate chegar no ultimo que e nulo
    {
        free(lp);  //desaloca a memoria utilizada pelo no antes de <<<pegar o proximo no>>
        lp = lp->prox; //pega o proximo
    }

    return lp;
}

listaProdutos* removePosicao(listaProdutos* lp)
{

    //variavel auxilar para navegar na lista
    listaProdutos* no = lp;

    if (no == NULL)
    {
        printf("A lista esta vazia!");
        pausa();
    }
    else
    {
        listaProdutos* anteriorProduto = NULL;

        int cont = 1;
        int posicao;

        printf("Digite a posicao desejada: ");
        scanf("%d", &posicao);

        //navega na lista ate a posicao desejada pelo usuario
        for (cont = 1; cont < posicao; cont++)
        {
            if (no->prox != NULL)
            {
                anteriorProduto = no;
                no = no->prox;
            }
            else     //se o no->prox for nulo nao faz sentido continuar no laco ja estamos no ultimo no
            {
                break; //cancela a execucao do for
            }
        }

        if (cont == 1)   //foi escolhido excluir o primeiro no
        {
            if (no->prox == NULL)   //nao existe mais nos na fila
            {
                free(lp);
                lp = NULL;
            }
            else     //existe mais nos na fila
            {
                lp = no->prox; //faz o lp apontar para o proximo no (este sera a partir de agora o primeiro no)
                free(no);
            }
        }
        else
        {
            if (no->prox == NULL)   //existe mais de um no na fila e foi escolhido excluir o ultimo no
            {
                anteriorProduto->prox = NULL;
            }
            else     //existe mais de um no na fila e no escolhido esta no meio nem no inicio nem no fim
            {
                anteriorProduto->prox = no->prox;
            }
            free(no);
        }
    }

    return lp;
}

void inserirFim(listaProdutos* lp)
{

    if (lp == NULL)
    {
        printf("A lista esta vazia!");
        pausa();
    }
    else
    {
        listaProdutos* penultimoProduto;
        listaProdutos* novoProduto;

        while (lp != NULL)
        {
            penultimoProduto = lp;
            lp = lp->prox;
        }

        novoProduto = criaProduto();
        novoProduto->prox = NULL;
        penultimoProduto->prox = novoProduto;
    }

}

void inserirPosicao(listaProdutos* lp)
{

    if (lp == NULL)
    {
        printf("A lista esta vazia!");
        pausa();
    }
    else
    {
        int cont;
        int posicao;

        //variavel utilizada para armazenar o endereco do no que ficara anterior ao novo no cadastrado
        listaProdutos* anteriorProduto;
        //variavel utilizada para armazenar o endereco do novo no
        listaProdutos* novoProduto;

        printf("Digite a posicao desejada: ");
        scanf("%d", &posicao);

        //navega na lista para encontrar a posicao desejada pelo usuario
        for (cont = 0; cont < posicao; cont++)
        {
            //obtem o endereço de lp para manter esta variavel defasada uma posicao em relacao a lp
            anteriorProduto = lp;
            //navega na lista ate a posicao desejada pelo usuario ou ate chegar no fim
            //caso a posicao seja maior que a posicao do ultimo no existente
            if (lp->prox != NULL)
            {
                //ainda na chegou no fim, obtem o proximo endereco
                lp = lp->prox;
            }
            else
            {
                //o proximo no e o fim da lista
                //o novo objeto sera inseriado como o ultimo da lista e apontara para lp que sera null
                lp = NULL;
                break;
            }
        }

        novoProduto = criaProduto();
        anteriorProduto->prox = novoProduto;
        novoProduto->prox = lp;
    }
}



listaProdutos* inverterPosicao(listaProdutos* lp) {

    int cont, Posicao1, Posicao2;
    listaProdutos* no = lp;
    listaProdutos* noAnterior;
    listaProdutos* Pos1[3];
    listaProdutos* Pos2[3];

    printf("Digite a Posicao 1 que deseja trocar: ");
    scanf("%d", &Posicao1);
    printf("Digite a Posicao 2 que deseja trocar: ");
    scanf("%d", &Posicao2);

    for (cont = 1; cont < Posicao1; cont++) {
        noAnterior = no;
        no = no->prox;
    }
    Pos1[0] = noAnterior; // lp
    printf("Pos[0]: %p\n", Pos1[0]);
    Pos1[1] = no; // 1
    printf("Pos[0]: %p\n", Pos1[1]);
    Pos1[2] = no->prox; // 2
    printf("Pos[0]: %p\n", Pos1[2]);

    no = lp;
    for (cont = 1; cont < Posicao2; cont++) {
        noAnterior = no;
        no = no->prox;
    }
    Pos2[0] = noAnterior; //4
    Pos2[1] = no; //5
    Pos2[2] = no->prox; // null

    if (Posicao1 == 0 || Posicao2 == 0 || Posicao1 == 1 || Posicao2 == 1) {
        Pos2[1]->prox = Pos1[2];
        Pos1[1]->prox = Pos2[2];
        Pos2[0]->prox = Pos1[1];
        return Pos2[1];
    }
    else {
        Pos2[1]->prox = Pos1[2];
        Pos1[0]->prox = Pos2[1];
        Pos1[1]->prox = Pos2[2];
        Pos2[0]->prox = Pos1[1];
    }

    pausa();

}



void imprimir(listaProdutos* lp)
{
    int i = 0; //novo
    printf("\nLista listaProdutos:");

    if (lp == NULL)
    {
        //identifica na primeira execucao do algoritmo que a lista esta vazia e apresenta uma mensagem customizada
        printf(" Nao existem produtos cadastrados ainda!\n");
    }
    else
    {
        printf("\n\n");
        while (lp != NULL)
        {
            //contador utilizado apenas para imprimir a posicao do no (posicao 1, posicao 2. ..)
            i++;
            //imprimindo os dados de um determinado no da lista
            printf("Posicao: %d | Endereco no: %p | Codigo: %d | Nome: %s | Proximo no: %p\n", i, lp, lp->codigo, lp->nome, lp->prox);
            //obtendo o endereco do proximo no
            lp = lp->prox;
        }
    }
}

void busca(listaProdutos* p)
{
    int codBuscado;

    printf("\nInforme o codigo do produto a ser buscado: ");
    scanf("%d", &codBuscado);

    //navega na lista ate chegar no ultimo no (null) ou caso encontre o no desejado
    while ((p != NULL) && (p->codigo != codBuscado))
    {
        //obtem o endereco do proximo no
        p = p->prox;
    }

    //se p for nulo e porque navegamos na lista e nao encontramos o no desejado
    if (p == NULL)
    {
        printf("\nProduto não encontrado!!!\n");
    }
    else
    {
        printf("\nProduto encontrado no endereco %p\n", p);
    }

    pausa();
}

int main()
{
    int resposta;

    //variavel que vai possuir o endereco do primeiro no da lista
    //esta variavel deve ser atualizada por funcoes todas as vezes que um novo no for adicionado como primeiro da lista
    listaProdutos* inicioLista;

    inicioLista = NULL;

    do
    {
        //ao reiniciar o laco ja imprime a lista
        imprimir(inicioLista);
        printf("\n");
        printf("------------------- MENU -------------------\n\n");
        printf("1 - Inserir no inicio\n");
        printf("2 - Inserir no fim\n");
        printf("3 - Inserir no depois de uma posicao\n");
        printf("4 - Buscar no\n");
        printf("5 - Imprimir lista\n");
        printf("6 - Remover no do fim\n");
        printf("7 - Remover no de uma posicao\n");
        printf("8 - Remover todos os nos da lista\n");
        printf("9 - Inverter duas posicoes\n");
        printf("10 - Sair\n\n");
        printf("Digite a opcao escolhida: ");
        scanf("%d", &resposta);

        switch (resposta)
        {
        case 1:
            inicioLista = inserir(inicioLista);
            break;
        case 2:
            inserirFim(inicioLista);
            break;
        case 3:
            inserirPosicao(inicioLista);
            break;
        case 4:
            busca(inicioLista);
            break;
        case 5:
            imprimir(inicioLista);
            break;
        case 6:
            inicioLista = removeFim(inicioLista);
            break;
        case 7:
            inicioLista = removePosicao(inicioLista);
            break;
        case 8:
            inicioLista = removeTudo(inicioLista);
            break;
        case 9:
            inicioLista = inverterPosicao(inicioLista);
            break;
        case 10:
            break;       //opcao para sair do laco e finalizar o programa
        default:
            printf("Opcao incorreta!!! \n");
            pausa();
            break;
        }

        //system("clear");
        system("cls"); //caso esteja em ambiente windows
    } while (resposta != 10);

    return 0;
}
