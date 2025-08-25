#include <stdio.h>
#include <stdlib.h>

int main(){
    float Salario_func, Salario_min, Quant_salario;

    printf("Digite o Salario do Funcionario: ");
    scanf("%f", &Salario_func);
    printf("Digite o Salario Minimo: ");
    scanf("%f", &Salario_min);
    Quant_salario = Salario_func / Salario_min;

    if (Salario_func <= Salario_min) {
        printf("O Funcionario ganha menos que um Salario Minimo!");
    }
    else {
        printf("O Funcionario recebe %.1f Salarios Minimos!", Quant_salario);
    }
    return 0;
}