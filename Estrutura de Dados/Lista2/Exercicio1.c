#include <stdio.h>
#include <stdlib.h>

int main(){
    float Frente, Lateral, Area, Preco, Preco_Metro;

    printf("Quantos Metros o terreno possui de Frente:");
    scanf("%f", &Frente);
    printf("Quantos Metros o terreno possui de Lateral: ");
    scanf("%f", &Lateral);
    printf("Digite o valor do Metro: ");
    scanf("%f", &Preco_Metro);
    Area = Frente * Lateral;
    Preco = Area * Preco_Metro;

    if (fabs(Frente - Lateral) < 0.1 * Frente) {
        Preco = 0.22 * Preco + Preco;
        printf("\nA Area do terreno de %.2f mts de frente com %.2f de mts de lateral sera: %.2f\n", Frente, Lateral, Area);
        printf("O Terreno recebeu um acrescimo de 22%% e custura: R$ %.2f", Preco);
    }
    else if (Frente < 0.4 * Lateral) {
        Preco = fabs(0.12 * Preco - Preco);
        printf("\nA Area do terreno de %.2f mts de frente com %.2f de mts de lateral sera: %.2f\n", Frente, Lateral, Area);
        printf("O Terreno recebeu um decrescimo de 12%% e custura: R$ %.2f", Preco);
    }
    else if (Frente > 0.7 * Lateral) {
        Preco = fabs(0.15 * Preco - Preco);
        printf("\nA Area do terreno de %.2f mts de frente com %.2f de mts de lateral sera: %.2f\n", Frente, Lateral, Area);
        printf("O Terreno recebeu um decrescimo de 15%% e custura: R$ %.2f", Preco);
    }
    else {
        printf("\nA Area do terreno de %.2f mts de frente com %.2f de mts de lateral sera: %.2f\n", Frente, Lateral, Area);
        printf("O terreno não recebeu nenhuma alteracao e custara: %.2f", Preco);
    }

    return 0;
}