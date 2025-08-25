#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, Quant, i;

    system("cls");

    Quant = 0;
    for (i = 1; i < 11; i++) {
        printf("Digite o %d Numero: ", i);
        scanf("%d", &Num);
        if (Num > 30 and Num < 90) {
            Quant++;
        }
    }
    printf("Existem %d Numeros entre 30 e 90.", Quant);

    return 0;
}