#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iso646.h>
#include <windows.h>

#define tam 5
#define TAMANHO_STRING 40

struct matricula{
    int ra;
    char nome[80];
    char email[40];
    char turma;
};

//criando um tipo de dados que ira representar o registro de alunos
typedef struct matricula matAluno;

//criando um tipo de dados baseado em matAluno que sera um vetor de ponteiros
typedef matAluno * hashTable[tam];

int espalhamento(int ra) {
    //5 % 3  o simbolo % significa o resto da divisao inteira
    return(ra % tam);
}

char *obtemString(){
    char *nome;

    //aloca TAMANHO_STRING caracteres para o nome
    nome = malloc(TAMANHO_STRING*sizeof(char));

    //limpa o buffer de entrada
    fflush(stdin);

    //obtem a string desejada
    nome = fgets(nome,TAMANHO_STRING,stdin);

    //outra opcao seria, porem apresenta o problema de estouro de buffer
    //nome = gets(nome);

    //remove a quebra de linha inserida pelo fgets
    for (int i = 0; i < strlen(nome); i++) {
        if (nome[i] == '\n')
            nome[i] = '\0';
    }

    return nome;
}

void inserir(hashTable tab, int ra, char* name, char* mail, char turma) {
    int qtde = 0;

    //obtem a posicao resultante do espalhamento
    int posVetor = espalhamento(ra);

    printf("====> Retorno funcao espalhamento: %d\n",posVetor);

    while(tab[posVetor] != NULL && qtde < tam) {
        //verifica se o valor e para ser atualizado
        if (tab[posVetor]->ra == ra){
            break;
        }
        //calculando nova posicao
        posVetor = espalhamento(posVetor+1);
        printf("Endereco inicial ocupado. Novo endereco: %d\n",posVetor);
        qtde++;
    }

    if (qtde < tam) {
        //Caso o vetor esteja com a posicao vazia, aloca a estrutura dinamica
        //Se o vetor ja tiver uma estrutura alocada, nao faz nada pois trata-se do mesmo aluno
        //entao sera atualizado os valores
        if (tab[posVetor] == NULL) {
            tab[posVetor] = malloc(sizeof(matAluno));
        }

        //Adiciona ou altera as informações na tabela hash
        tab[posVetor]->ra = ra;
        strcpy(tab[posVetor]->nome, name);
        strcpy(tab[posVetor]->email, mail);
        tab[posVetor]->turma = turma;
    } else {
        printf("Nao existe espaco na tabela de armazenamento.\n");
    }
}

void remover(hashTable tab, int ra){
    int posicao = NULL;

    //Invoca a funcao buscar para obter o indice do vetor onde esta o item a ser excluido
    posicao = buscar(tab,ra);

    if (posicao != NULL) {
        //desaloca a estrutura dinamica
        free(tab[posicao]);
        //atualiza a posicao do vetor como NULL para futura utilizacao
        tab[posicao] = NULL;
        printf("RA excluido!\n");
    } else {
        printf("RA nao encontrado!\n");
    }
}

int buscar(hashTable tab, int ra) {
    int qtde = 0;

    //obtem a posicao resultante do espalhamento
    int h = espalhamento(ra);

    //busca ate encontrar uma posicao vazia
    while (tab[h] != NULL && qtde < tam) {
        //para cada posicao com um endereco existente testa pra ver se o ra foi encontrado
        //se sim, retorna a posicao do vetor
        if (tab[h]->ra == ra){
            return h;
        }

        h = espalhamento(h+1);
        qtde++;
    }

    printf("RA nao encontrado!\n");

    //se o laco terminar e chegar neste ponto e porque o ra buscado nao existe na tabela
    return NULL;
}

void inicializaTabela(hashTable tab){
    int i;
    //percorre todas as posicoes do vetor e armazena NULL
    for(i = 0; i < tam; i++){
        tab[i] = NULL;
    }
}

void imprimirDados(hashTable tabela, int posicao){
    if (posicao != NULL){
        printf("Nome: %s \nRA: %d \nEmail: %s \nTurma: %c\n\n",tabela[posicao]->nome,tabela[posicao]->ra,tabela[posicao]->email,tabela[posicao]->turma);
    } else {
       printf("RA nao encontrado\n\n");
    }
}

void imprimitTabela(hashTable tabela){
    printf("Tabela HASH: ");
    for (int i = 0; i<tam; i++){
       printf("%x ",tabela[i]);
    }
    printf("\n\n");
}

int main() {
    //variavel para armazenar o resultado de uma busca
    int rBusca, Ra, Opcao;
    char *Nome;
    char *Email;
    char Turma;

    hashTable tabela;
    matAluno* retorno;

    inicializaTabela(tabela);

    do {
        system("cls");
        imprimitTabela(tabela);
        printf("\n");
        printf("---------------MENU---------------\n");
        printf("1-Inserir Dados\n");
        printf("2-Buscar Dados\n");
        printf("3-Exibir Tabela\n");
        printf("4-Atualizar Dados\n");
        printf("5-Excluir Item\n");
        printf("6-Sair\n");
        printf("----------------------------------\n");
        printf("Digite a Opcao Desejada: ");
        scanf("%d", &Opcao);
        printf("\n");

        switch(Opcao) {
            case 1:
                printf("Digite o Valor do RA: ");
                scanf("%d", &Ra);
                printf("Digite o Nome: ");
                Nome = obtemString();
                printf("Digite o Email: ");
                Email = obtemString();
                printf("Digite a Turma: ");
                scanf("%c", &Turma);
                inserir(tabela, Ra, Nome, Email, Turma);
                Sleep(650);
                break;
            case 2:
                printf("Digite o Valor do RA que deseja buscar: ");
                scanf("%d", &Ra);
                rBusca = buscar(tabela,Ra);
                imprimirDados(tabela, rBusca);
                printf("\n");
                system("pause");
                break;
            case 3:
                printf("Exibindo Tabela:\n");
                imprimitTabela(tabela);
                printf("\n");
                system("pause");
                break;
            case 4:
                printf("Digite o Valor do RA que deseja Atualizar: ");
                scanf("%d", &Ra);
                printf("Digite o Nome: ");
                Nome = obtemString();
                printf("Digite o Email: ");
                Email = obtemString();
                printf("Digite a Turma: ");
                scanf("%c", &Turma);
                inserir(tabela, Ra, Nome, Email, Turma);
                printf("\n");
                system("pause");
                break;
            case 5:
                printf("Digite o Valor do RA que deseja Excluir: ");
                scanf("%d", &Ra);
                remover(tabela,Ra);
                break;
            case 6:
                exit(1);

        }
    }while(1);


    printf("\n\n");
    return 0;
}
