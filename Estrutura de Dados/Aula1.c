#include <stdio.h>
#include <stdlib.h>

#define QTDE_ALUNO 200

int main()
{
    int matricula[QTDE_ALUNO];
    float notaProva1[QTDE_ALUNO];
    float notaTrabalho1[QTDE_ALUNO];
    float notaProva2[QTDE_ALUNO];
    float notaTrabalho2[QTDE_ALUNO];
    float mediaFinal[QTDE_ALUNO];
    int ProxPosicaoLivre = 0;
    char Resposta;

    //variaveis para auxiliar no calculo da media ponderada
    float mp1b, mp2b;

    //leitura dos dos dados dos alunos
    //for (int i = 0; i < QTDE_ALUNO; i++){
    do {
        printf("Digite os dados do aluno %d\n", ProxPosicaoLivre+1);

        printf("Digite a matricula do aluno: ");
        scanf("%i",&matricula[ProxPosicaoLivre]);

        printf("Digite a nota da prova 1: ");
        scanf("%f",&notaProva1[ProxPosicaoLivre]);

        printf("Digite a nota da trabalho 1: ");
        scanf("%f",&notaTrabalho1[ProxPosicaoLivre]);

        printf("Digite a nota da prova 2: ");
        scanf("%f",&notaProva2[ProxPosicaoLivre]);

        printf("Digite a nota da trabalho 2: ");
        scanf("%f",&notaTrabalho2[ProxPosicaoLivre]);

        ProxPosicaoLivre++;

        printf("\n");
        printf("Deseja Cadastrar um nome aluno [s|n]: ");
        fflush(stdin); //Limpa o Buffer de Entrada.
        scanf("%c", &Resposta);
    } while (Resposta == 's');

    //calculo da media final do aluno
    for (int i = 0; i < ProxPosicaoLivre; i++){
        mp1b = ((notaProva1[i] * 1) + (notaTrabalho1[i] * 2))/3.0;
        mp2b = ((notaProva2[i] * 2) + (notaTrabalho2[i] * 1))/3.0;

        mediaFinal[i] =  (mp1b + mp2b) / 2.0;
    }

    //exibicao do status de aprovacao
    for (int i = 0; i < ProxPosicaoLivre; i++){
        printf("Aluno %d ", i+1);
        printf("\nMatricula: %i", matricula[i]);
        printf("\nMedia Final: %.2f", mediaFinal[i]);

        if (mediaFinal[i] >= 6.0)
            printf("\nStatus: Aprovado\n");
        else if (mediaFinal[i] >= 5.0)
            printf("\nStatus: Recuperacao\n");
        else
            printf("\nStatus: Reprovado\n");

        printf("\n");
    }

    return 0;
}
