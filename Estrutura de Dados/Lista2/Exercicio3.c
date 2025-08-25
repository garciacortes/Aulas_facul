#include <stdlib.h>
#include <stdio.h>
#include <iso646.h>

float VetorPos(float Notas[3], float Pesos[3]) {
    float CopiarNotas[2], CopiarPesos[2];

    CopiarNotas[0] = Notas[1];
    CopiarNotas[1] = Notas[2];
    Notas[2] = Notas[0];
    Notas[1] = CopiarNotas[1];
    Notas[0] = CopiarNotas[0];
    /* Muda a ordem dos pesos para verificar qual(is) é maior,
    mantendo peso que sera usado para verificar no Vetor 0 e 1*/
    CopiarPesos[0] = Pesos[1];
    CopiarPesos[1] = Pesos[2];
    Pesos[2] = Pesos[0];
    Pesos[0] = CopiarPesos[0];
    Pesos[1] = CopiarPesos[1];

    return Notas[3], Pesos[3];
}

int VerificarPeso(float Pesos[3], float Notas[3], float Ponderada, int NotPass, int Posicao[3]) {
    if (Pesos[0] > Pesos[1] and Pesos[0] > Pesos[2]) {
        printf("\nA Media Ponderada das Notas %.2f, %.2f, %.2f sera: %.2f.\n", Notas[0], Notas[1], Notas[2], Ponderada);
        printf("A Nota %d (%.2f) e a maior nota apos o Calculo do Peso %d (%.2f).", Posicao[0], Notas[0], Posicao[0], Pesos[0]);
        return NotPass = 1;
    }
    else if (Pesos[0] == Pesos[1] and Pesos[0] > Pesos[2]) {
        printf("\nA Media Ponderada das Notas %.2f, %.2f, %.2f sera: %.2f.\n", Notas[0], Notas[1], Notas[2], Ponderada);
        printf("As Notas %d (%.2f) e %d (%.2f) foram as maiores notas apos o calculo do peso %d (%.2f) e peso %d (%.2f)", Posicao[1], Notas[0], Posicao[2], Notas[1], Posicao[1], Pesos[0], Posicao[2], Pesos[1]);
    }
    else if (Pesos[0] == Pesos[1] and Pesos[0] == Pesos[2]) {
        printf("\nA Media Ponderada das Notas %.2f, %.2f, %.2f sera: %.2f.\n", Notas[0], Notas[1], Notas[2], Ponderada);
        printf("As tres Notas foram iguais.");
    }
    else {
        return NotPass;
    }
}

int main() {
    float Notas[3], Ponderada, Pesos[3], Copiar;
    int NotPass, i, x, Posicao[3];

    system("cls");
    x = 1;
    for (i = 0; i < 3; i++) {
        printf("digite as Tres Notas, 1 por vez: ");
        scanf("%f", &Notas[i]);
        Pesos[i] = Notas[i] * x;
        Posicao[i] = 1;
        x++;
        Ponderada = Ponderada + Pesos[i];
    }
    Ponderada = Ponderada / 6;
    Posicao[2] = 2;

    while (1) {
        // 0 usado caso caia no Else, ele faça a mudança dos Pesos para efetuar outras Verificação.
        NotPass = VerificarPeso(Pesos, Notas, Ponderada, 0, Posicao);
        if (NotPass == 0) {
            VetorPos(Notas, Pesos);
            if (Posicao[1] != 2) {
                Posicao[1]++;
                Posicao[2]++;
            }
            else {
                Posicao[1]--;
                Copiar = Notas[0];
                Notas[0] = Notas[1];
                Notas[1] = Copiar;
            }
            Posicao[0]++;
        }
        else {
            break;
        }
    }

    return 0;
}