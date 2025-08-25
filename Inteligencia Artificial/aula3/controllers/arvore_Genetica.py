import random
from PyQt6.QtCore import QObject, pyqtSignal, QTimer


class ArvoreGenetica(QObject):
    
    geracoes_atualizar = pyqtSignal(int)
    pop_initial_atualizar = pyqtSignal(list)
    pop_evolution_atualizar = pyqtSignal(list)
    pop_initial_clear = pyqtSignal()
    pop_evolution_clear = pyqtSignal()
    
    def __init__(self):
        super().__init__()
        
        self.MAXIMO_GERACOES = 20000
        self.TamanhoPopulacao = 50
        self.TaxaMutacao = 0.02
        self.populacao = []
        self.filhos = []
        self.roleta = []
        self.quantidadeGeracoes = 0
        self.timer = QTimer()
        self.timer.timeout.connect(self.process_geracao)
    
    def PopulacaoConvergiu(self):
        return all(x == 1023 for x in self.populacao)
            
    def cruzamento(self):
        self.filhos.clear()

        roleta = [individuo ** 2 for individuo in self.populacao]
        
        for _ in range(25):
            pai, mae = random.choices(self.populacao, weights=roleta, k=2)
            
            # converte para binario bin(var), [2:] deixa so o binario e o zfill completa ate 10 com 0
            binarioPai = f"{pai:010b}" 
            binarioMae = f"{mae:010b}"
            
            filho = int(binarioPai[:5] + binarioMae[5:], 2) # msm coisa do [5:10]
            
            self.filhos.append(filho)
            
    def mutacao(self):
        for i in range(25):
            filhoDecimal = self.filhos[i]
            
            for j in range(10):
                if random.random() < self.TaxaMutacao:
                #   filhosBinario = filhosBinario[:j] + ("0" if filhosBinario[j] == "1" else "1") + filhosBinario[j+1:]
                #filhoDecimal = int(filhosBinario, 2)
                    filhoDecimal ^= (1 << (9 - j))
                
                self.filhos[i] = filhoDecimal

    def iniciar(self):
        self.pop_initial_clear.emit()
        self.pop_evolution_clear.emit()
        self.populacao.clear()
        self.quantidadeGeracoes = 0
        
        self.populacao = [random.randrange(0, 1023) for _ in range(self.TamanhoPopulacao)]
        self.pop_initial_atualizar.emit(self.populacao)
        self.timer.start(300)
        
    def process_geracao(self):
        if self.quantidadeGeracoes < self.MAXIMO_GERACOES and not self.PopulacaoConvergiu():
            self.pop_evolution_clear.emit()
            self.cruzamento()
            self.mutacao()
            self.populacao[:]= sorted(self.filhos + self.populacao, reverse=True)[:50]
            self.pop_evolution_atualizar.emit(self.populacao)
            self.quantidadeGeracoes += 1
            self.geracoes_atualizar.emit(self.quantidadeGeracoes)
        else:
            self.timer.stop()
        