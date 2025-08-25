#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, Impar, Soma;

    system("cls");

    printf("Digite o Numero: ");
    scanf("%d", &Num);
    Soma = 0;
    Impar = Num % 2;
    while (Impar == 0) {
        Soma += Num;
        printf("Digite o Numero: ");
        scanf("%d", &Num);
        Impar = Num % 2;
    }
    printf("A soma dos Numeros sera %d.", Soma);

    return 0;
}