#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>

int main(){
    int i, Valor, Resultado;
    char Operacao;
    float Divisao;

    printf("Digite a Operacao Desejada[+|-|*|/]: ");
    scanf("%c", &Operacao);
    printf("Digite o Valor: ");
    scanf("%d", &Valor);

    i = 0;
    while (i < 10) {
        if (Operacao == '+') {
            Resultado = abs(Valor + i);
            Sleep(500);
            printf("Tabuada do + para o 4:\n");
            printf("%d + %d = %d\n", Valor, i, Resultado);
            printf("-----------------------\n");
            i++;
        }
        else if (Operacao == '/') {
            if (i == 0) {
                printf("Nao e possivel dividir por 0\n");
                i++;
            }
            else {
                Divisao = ((float)Valor / (float)i);
                Sleep(500);
                printf("Tabuada do / para o 4:\n");
                printf("%d / %d = %.2f\n", Valor, i, Divisao);
                printf("-----------------------\n");
                i++;
            }
        }
        else if (Operacao == '*') {
            Resultado = abs(Valor * i);
            Sleep(500);
            printf("Tabuada do * para o 4:\n");
            printf("%d * %d = %d\n", Valor, i, Resultado);
            printf("-----------------------\n");
            i++;
        }
        else if (Operacao == '-') {
            Resultado = abs(Valor - i);
            Sleep(500);
            printf("Tabuada do - para o 4:\n");
            printf("%d - %d = %d\n", Valor, i, Resultado);
            printf("-----------------------\n");
            i++;
        }
    }

}
