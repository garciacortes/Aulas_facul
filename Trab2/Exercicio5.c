#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>
#include <Windows.h>

int main() {
    int i, Idade, QuantFaixa[5];

    system("cls");

    for (i = 0; i != 5; i++) {
        QuantFaixa[i] = 0;
    }

    for (i = 1; i < 11; i++) {
        printf("Digite o %d Numero: ", i);
        scanf("%d", &Idade);
        if (Idade <= 15) {
            QuantFaixa[0]++;
        }
        else if (Idade <= 30) {
            QuantFaixa[1]++;
        }
        else if (Idade <= 45) {
            QuantFaixa[2]++;
        }
        else if (Idade <= 60) {
            QuantFaixa[3]++;
        }
        else {
            QuantFaixa[4]++;
        }
    }
    printf("%d%% ou %d Pessoas tem ate 15 Anos.\n%d Pessoas tem de 16 a 30 Anos.\n%d Pessoas tem de 31 a 45 Anos.\n%d Pessoas tem de 46 a 60 Anos."
        "\n%d%% ou %d Pessoas tem acima de 61 Anos.", QuantFaixa[0] * 10, QuantFaixa[0], QuantFaixa[1], QuantFaixa[2], QuantFaixa[3], QuantFaixa[4] * 10, QuantFaixa[4]);

    return 0;
}