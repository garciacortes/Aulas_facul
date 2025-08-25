#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, Maior, Menor;
    system("cls");
    printf("Digite um Numero: ");
    scanf("%d", &Num);
    Maior = Menor = Num;
    do {
        printf("Digite outro Numero|ou Zero para finalizar: ");
        scanf("%d", &Num);
        if (Num > Maior and Num != 0) {
            Maior = Num;
        }
        else if (Num < Menor and Num != 0) {
            Menor = Num;
        }
    } while (Num != 0);
    printf("O maior Numero e %d, o menor Numero e %d.", Maior, Menor);

    return 0;
}