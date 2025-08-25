#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, i, Soma;
    float Media;

    system("cls");

    Soma = 0;
    printf("Digite numeros entre 0 e 10, qualquer outro encerrara o Loop|Calculo.\n");
    for (i = 1; i < 3; i++) {
        printf("Digite o %d Numero: ", i);
        scanf("%d", &Num);
        if (Num < 10 and Num > 0) {
            Soma += Num;
        }
        else {
            printf("Foi digitado um numero que nao esta entre 0 e 10.\n");
            break;
        }
    }
    Media = Soma / 2;
    printf("A media entre os dois Numeros e %.2f", Media);

    return 0;
}