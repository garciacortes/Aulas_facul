#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int Num, Result, i;
    system("cls");

    Result - 0;
    printf("Digite um numero: ");
    scanf("%d", &Num);
    for (i = 2; i <= Num / 2; i++) {
        if (Num % i == 0) {
            Result++;
            break;
        }
    }

    if (Result == 0) {
        printf("%d e um numero primo.", Num);
    }
    else {
        printf("%d nao e um numero primo.", Num);
    }
    return 0;
}