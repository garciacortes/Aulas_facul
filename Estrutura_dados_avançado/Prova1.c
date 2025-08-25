#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>
#include <iso646.h>
#include <windows.h>

typedef struct Item info;

struct Item {
    int valor;
    double tempo;
    char nome[40];
};

void InserirNumero(info vetor[], int tamanho) {

    int i, rept, x;

    i = 0;
    printf("[");
    do {
        vetor[i].valor = rand() % 100000;
        rept = 0;
        for (x = 0; x < i; x++) {
            if (vetor[x].valor == vetor[i].valor) {
                rept = 1;
            }
        }
        if (rept == 0) {
            //printf("%d ", vetor[i].valor);
            i++;
        }
    } while (i < tamanho);

    printf("]");
}

void imprime_vetor(info vetor[], int n) {

    printf(" [ ");
    for (int i = 0; i < n; i++) {
        printf(" %d ", vetor[i].valor);
    }
    printf(" ] ");
    return;
}

void Menu(info vetor[], int TamanhoDoVetor) {

    int opcao2;

    printf("------------------- Menu -------------------\n");
    printf("1 - Digitar cada Valor\n");
    printf("2 - Gerar aleatorio\n");
    printf("Digite a opcao escolhida: ");
    scanf("%d", &opcao2);

    switch (opcao2)
    {
    case 1:
        for (int i = 0; i < TamanhoDoVetor; i++) {
            printf("------ Digite os Valores ------\n");
            printf("[%d]: ", i);
            scanf("%d", &vetor[i].valor);
        }
        break;

    case 2:
        printf("\n******** Digite o vetor para ordenar: \n");
        InserirNumero(vetor, TamanhoDoVetor);
        break;
    }

    printf("\n ******** Vetor Original: \n \n ");
    imprime_vetor(vetor, TamanhoDoVetor);
}

double insertion_sort(info vetor[], int tamanhovetor, int p) {

    int k, aux;

    clock_t antes = clock();

    for (int i = 1; i < tamanhovetor; i++) {

        k = i - 1;

        while ((k >= 0) && (vetor[k].valor > vetor[k + 1].valor)) {
            aux = vetor[k + 1].valor;
            vetor[k + 1].valor = vetor[k].valor;
            vetor[k].valor = aux;
            k = k - 1;
        }
    }

    clock_t depois = clock();
    double segundosGastos = (double)(depois - antes) / CLOCKS_PER_SEC;
    if (p < 6) {
        imprime_vetor(vetor, tamanhovetor);
        printf("\n\n --- Tempo gasto: %f seg", segundosGastos);
    }

    return segundosGastos;
}

double bubble_sort(info vetor[], int tamanhovetor, int p) {

    clock_t antes = clock();

    int aux;
    for (int k = 1; k < tamanhovetor; k++) {
        for (int j = 0; j < tamanhovetor - 1; j++) {
            if (vetor[j].valor > vetor[j + 1].valor) {
                aux = vetor[j].valor;
                vetor[j].valor = vetor[j + 1].valor;
                vetor[j + 1].valor = aux;
            }
        }
    }

    clock_t depois = clock();
    double segundosGastos = (double)(depois - antes) / CLOCKS_PER_SEC;
    if (p < 6) {
        imprime_vetor(vetor, tamanhovetor);
        printf("\n\n --- Tempo gasto: %f seg", segundosGastos);
    }

    return segundosGastos;
}

double selection_sort(info vetor[], int tamanhovetor, int p) {

    int posicao_menor;
    int temp;

    clock_t antes = clock();

    for (int i = 0; i < tamanhovetor - 1; i++) {

        posicao_menor = i;

        for (int j = i + 1; j < tamanhovetor; j++) {
            if (vetor[j].valor < vetor[posicao_menor].valor) {
                posicao_menor = j;
            }
        }

        temp = vetor[posicao_menor].valor;
        vetor[posicao_menor].valor = vetor[i].valor;
        vetor[i].valor = temp;
    }

    clock_t depois = clock();
    double segundosGastos = (double)(depois - antes) / CLOCKS_PER_SEC;
    if (p < 6) {
        imprime_vetor(vetor, tamanhovetor);
        printf("\n\n --- Tempo gasto: %f seg", segundosGastos);
    }

    return segundosGastos;
}

void merge(info vetor[], int* vetor_temporario, int indice_inicial, int indice_meio, int indice_fianl) {

    int indice_percorreVetor;
    int copia_indiceInicial = indice_inicial;
    int copia_indiceMeio = indice_meio + 1;

    for (indice_percorreVetor = indice_inicial; indice_percorreVetor <= indice_fianl; indice_percorreVetor++) {
        vetor_temporario[indice_percorreVetor] = vetor[indice_percorreVetor].valor;
    }

    indice_percorreVetor = indice_inicial;

    while (copia_indiceInicial <= indice_meio && copia_indiceMeio <= indice_percorreVetor++) {
        if (vetor_temporario[copia_indiceInicial] <= vetor[copia_indiceMeio].valor) {
            vetor[indice_percorreVetor++].valor = vetor_temporario[copia_indiceInicial++];
        }
        else {
            vetor[indice_percorreVetor++].valor = vetor_temporario[copia_indiceMeio++];
        }
    }

    while (copia_indiceInicial <= indice_meio) {
        vetor[indice_percorreVetor++].valor = vetor_temporario[copia_indiceInicial++];
    }

    while (copia_indiceMeio <= indice_fianl) {
        vetor[indice_percorreVetor++].valor = vetor_temporario[copia_indiceMeio++];
    }
}

void sort(info vetor[], int* vetor_temporario, int indice_inicial, int indice_final) {

    if (indice_inicial >= indice_final) {
        return;
    }

    int indice_meio = (indice_inicial + indice_final) / 2;

    sort(vetor, vetor_temporario, indice_inicial, indice_meio);
    sort(vetor, vetor_temporario, indice_meio + 1, indice_final);

    if (vetor[indice_meio].valor <= vetor[indice_meio + 1].valor) {
        return;
    }

    merge(vetor, vetor_temporario, indice_inicial, indice_meio, indice_final);
}

double mergesort(info vetor[], int TamanhoDoVetor, int p) {

    clock_t antes = clock();

    int* vetor_temporario = (int*)malloc(sizeof(int) * TamanhoDoVetor);

    sort(vetor, vetor_temporario, 0, TamanhoDoVetor - 1);

    free(vetor_temporario);

    clock_t depois = clock();

    double segundosGastos = (double)(depois - antes) / CLOCKS_PER_SEC;
    if (p < 6) {
        imprime_vetor(vetor, TamanhoDoVetor);
        printf("\n\n --- Tempo gasto: %f seg", segundosGastos);
    }

    return segundosGastos;
}

void trocar(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void quicksort(info vetor[], int TamanhoDoVetor, int baixo, int alto) {

    if (baixo < alto) {
        int pivo = vetor[alto].valor;

        int i = (baixo - 1);

        for (int j = baixo; j <= alto - 1; j++) {
            if (vetor[j].valor <= pivo) {
                i++;
                trocar(&vetor[i].valor, &vetor[j].valor);
            }
        }

        // Coloca o pivô em seu lugar correto
        trocar(&vetor[i + 1].valor, &vetor[alto].valor);

        // Ordena os elementos antes e depois do pivô
        quicksort(vetor, TamanhoDoVetor, baixo, i);
        quicksort(vetor, TamanhoDoVetor, i + 2, alto);
    }
}

double quicksortIni(info vetor[], int TamanhoDoVetor, int baixo, int alto, int p) {

    clock_t antes = clock();
    quicksort(vetor, TamanhoDoVetor, baixo, alto);

    clock_t depois = clock();
    double segundosGastos = (double)(depois - antes) / CLOCKS_PER_SEC;
    if (p < 6) {
        imprime_vetor(vetor, TamanhoDoVetor);
        printf("\n\n --- Tempo gasto: %f seg", segundosGastos);
    }

    return segundosGastos;
}

int main() {

    int tamanho;
    int opcao;
    info* vetor;

    srand(time(NULL));
    do {
        printf("\n------------------- Menu -------------------\n");
        printf("1 - Bubble Sort\n");
        printf("2 - Insertion Sort\n");
        printf("3 - Selection Sort\n");
        printf("4 - Merge Sort\n");
        printf("5 - Quick Sort\n");
        printf("6 - Comparativo de tempo de execucao\n");
        printf("7 - sair\n");
        printf("Digite a opcao escolhida: ");
        scanf("%d", &opcao);
        if (opcao < 6) {
            printf("digite quantos Numeros serao Ordenados: ");
            scanf("%d", &tamanho);
            vetor = (info*)malloc(tamanho * sizeof(info));
        }

        if (vetor == NULL) {
            printf("Erro na alocação de memoria. \n");
            return 1;
        }

        switch (opcao)
        {
        case 1:
            printf("\nbubble Sort Escolhida \n");
            Menu(vetor, tamanho);
            printf("\n******** Vetor ORDENADO - BUBBLE SORT: \n \n");
            bubble_sort(vetor, tamanho, opcao);
            break;
        case 2:
            printf("\nInsertion Sort Escolhida \n");
            Menu(vetor, tamanho);
            printf("\n******** Vetor ORDENADO - INSERTION SORT: \n \n");
            insertion_sort(vetor, tamanho, opcao);
            break;
        case 3:
            printf("\nselection Sort Escolhida \n");
            Menu(vetor, tamanho);
            printf("\n******** Vetor ORDENADO - SELECTION SORT: \n \n");
            selection_sort(vetor, tamanho, opcao);
            break;
        case 4:
            printf("\nMerge Sort Escolhida \n");
            Menu(vetor, tamanho);
            printf("\n******** Vetor ORDENADO - Merge SORT: \n \n");
            mergesort(vetor, tamanho, opcao);
            break;
        case 5:
            printf("\nQuick Sort Escolhida \n");
            Menu(vetor, tamanho);
            printf("\n******** Vetor ORDENADO - Quick SORT: \n \n");
            quicksortIni(vetor, tamanho, 0, tamanho - 1, opcao);
            break;
        case 6:
            tamanho = 10000;
            do {
                printf("\nComparativo de Tempo Escolhida \n");
                vetor = (info*)malloc(tamanho * sizeof(info));
                printf("-------- Comparativo com %d Numeros --------\n", tamanho);
                InserirNumero(vetor, tamanho);

                strcpy(vetor[0].nome, "Bubble Sort");
                vetor[0].tempo = bubble_sort(vetor, tamanho, opcao);

                strcpy(vetor[1].nome, "Insertion Sort");
                vetor[1].tempo = insertion_sort(vetor, tamanho, opcao);

                strcpy(vetor[2].nome, "Selection Sort");
                vetor[2].tempo = selection_sort(vetor, tamanho, opcao);

                strcpy(vetor[3].nome, "Merge Sort");
                vetor[3].tempo = mergesort(vetor, tamanho, opcao);

                strcpy(vetor[4].nome, "Quick Sort");
                vetor[4].tempo = quicksortIni(vetor, tamanho, 0, tamanho - 1, opcao);

                // Ordenar manualmente os algoritmos por tempo (Bubble Sort para Quick Sort)
                for (int i = 0; i < 5; i++) {
                    for (int j = i + 1; j < 5; j++) {
                        if (vetor[i].tempo > vetor[j].tempo) {
                            // Troca os elementos
                            info temp = vetor[i];
                            vetor[i] = vetor[j];
                            vetor[j] = temp;
                        }
                    }
                }

                printf("\n--- Ordem de execucao dos algoritmos por tempo ---\n");
                for (int i = 0; i < 5; i++) {
                    printf("%s: %f segundos\n", vetor[i].nome, vetor[i].tempo);
                }
                tamanho += 20000;
            } while (tamanho == 30000 || tamanho == 10000);
            break;
        case 7:
            exit(1);
        }
    } while (opcao != 7);



    free(vetor);
    return 0;
}
