#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>
#include <iso646.h>

void insertion_sort(int vetor[], int tamanhovetor){

    int k;
    int i;
    int aux;

    for (int iteracao = 1; iteracao < tamanhovetor; iteracao++) {

        k = iteracao - 1;

        while ( (k>=0) && (vetor[k] > vetor[k+1]) ) {

            aux = vetor[k+1];
            vetor[k+1] = vetor[k];
            vetor[k] = aux;
            k = k - 1;
        }
    }
}

void imprime_vetor(int vetor[], int n) {

    printf(" [ ");
    for(int i = 0; i < n; i++) {

        printf(" %d ", vetor[i]);
    }
    printf(" ] ");
    return;
}
int main() {

    int tamanho = 1000;
    int vetor[tamanho];
    int t;

    printf("\n ******** Digite o vetor para ordenar: ");
    for(int i = 0; i < tamanho; i++) {
        vetor[i] = rand() % 1000;
    }

    printf("\n ******** Vetor Original: \n \n ");
    imprime_vetor(vetor, tamanho);

    printf("\n ******** Vetor ORDENADO - INSERTION SORT: \n \n");

    clock_t antes = clock();
    insertion_sort(vetor, tamanho);

    clock_t depois = clock();
    imprime_vetor(vetor,tamanho);

    float segundosGastos = (depois-antes) / CLOCKS_PER_SEC;
    printf("\n\n --- Tempo gasto: %f seg", segundosGastos);
    return 0;
}
