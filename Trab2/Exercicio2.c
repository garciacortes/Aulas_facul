#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, Soma, i;
    float Media;

    i = 0;
    system("cls");
    do {
        printf("Digite o Numero a somar|zero para finalizar: ");
        scanf("%d", &Num);
        if (Num % 2 == 0 and Num != 0) {
            Soma += Num;
            i++;
        }
    } while (Num != 0);
    printf("A Media Aritimetica dos numeros pares sera: %.2f", (float)Soma / i);

    return 0;
}