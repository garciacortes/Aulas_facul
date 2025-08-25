#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, i;

    system("cls");

    printf("Digite um Numero: ");
    scanf("%d", &Num);
    for (i = 1; i <= Num; i++) {
        Sleep(300);
        if (Num % i == 0) {
            printf("%d e divisivel por %d.\n", Num, i);
        }
        else {
            printf("%d nao e divisivel por %d.\n", Num, i);
        }
    }

    return 0;
}