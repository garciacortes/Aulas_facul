import random

BIAS = 1
TAXA_APRENDIZADO = 0.05

def relu(x):
    return max(0, min(x, 10000))

class Neuronio:
    def __init__(self, qtd_ligacoes):
        self.pesos = [random.uniform(-1, 1) for _ in range(qtd_ligacoes)]
        self.erro = 0
        self.saida = 1.0
        self.qtd_ligacoes = qtd_ligacoes

class Camada:
    def __init__(self, qtd_neuronios, qtd_ligacoes):
        self.neuronios = [Neuronio(qtd_ligacoes) for _ in range(qtd_neuronios)]
        self.qtd_neuronios = qtd_neuronios

class RedeNeural:
    def __init__(self, qtd_entrada, qtd_ocultas, qtd_saida, qtd_camadas_ocultas):
        # Adiciona BIAS
        self.camada_entrada = Camada(qtd_entrada + BIAS, 0)
        self.qtd_camadas_ocultas = qtd_camadas_ocultas

        # Cria camadas ocultas
        self.camadas_ocultas = []
        for i in range(qtd_camadas_ocultas):
            if i == 0:
                self.camadas_ocultas.append(Camada(qtd_ocultas + BIAS, qtd_entrada + BIAS))
            else:
                self.camadas_ocultas.append(Camada(qtd_ocultas + BIAS, qtd_ocultas + BIAS))

        # Camada de saída
        self.camada_saida = Camada(qtd_saida, qtd_ocultas + BIAS)

    def copiar_para_entrada(self, vetor_entrada):
        for i in range(len(vetor_entrada)):
            self.camada_entrada.neuronios[i].saida = vetor_entrada[i]
        # BIAS da entrada já está como 1

    def calcular_saida(self):
        # Entrada -> primeira camada oculta
        for i in range(self.camadas_ocultas[0].qtd_neuronios - BIAS):
            soma = 0
            for j, neuronio_entrada in enumerate(self.camada_entrada.neuronios):
                soma += neuronio_entrada.saida * self.camadas_ocultas[0].neuronios[i].pesos[j]
            self.camadas_ocultas[0].neuronios[i].saida = relu(soma)

        # Camadas ocultas subsequentes
        for k in range(1, self.qtd_camadas_ocultas):
            for i in range(self.camadas_ocultas[k].qtd_neuronios - BIAS):
                soma = 0
                for j in range(self.camadas_ocultas[k-1].qtd_neuronios):
                    soma += self.camadas_ocultas[k-1].neuronios[j].saida * self.camadas_ocultas[k].neuronios[i].pesos[j]
                self.camadas_ocultas[k].neuronios[i].saida = relu(soma)

        # Última camada oculta -> saída
        ultima_oculta = self.camadas_ocultas[-1]
        for i in range(self.camada_saida.qtd_neuronios):
            soma = 0
            for j in range(ultima_oculta.qtd_neuronios):
                soma += ultima_oculta.neuronios[j].saida * self.camada_saida.neuronios[i].pesos[j]
            self.camada_saida.neuronios[i].saida = relu(soma)

    def copiar_saida(self):
        return [n.saida for n in self.camada_saida.neuronios]