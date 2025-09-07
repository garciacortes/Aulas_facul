from PyQt6.QtTest import QTest

class CalculoRede():
    
    def __init__(self, model_RedeCalculo, controller_RedeColor):
        self.model_RedeCalculo = model_RedeCalculo
        self.controller_RedeColor = controller_RedeColor
        self.X_VetoresTreinamento = [ 
            [ 1, 1, 1, 1, 1], #febre, enjoo, manchas, dores, 1 = doente | -1 = saudavel
            [-1, -1, -1, -1, 1],
            [1, 1, 1, -1, 1],
            [1, -1, -1, 1, 1],
            [1, -1, 1, 1, 1],
            [-1, -1, -1, 1, 1]
        ]
        self.Y_SaidaDesejada = [1, -1, -1, 1, -1, 1]
        self.TaxaAprendizagem = 0.02
        self.W_Pesos = self.model_RedeCalculo.W_Pesos
        self.sleep = 70
        self.doenças = {}
        self.manchas = {}
        
    def treinar(self):
        temErro = True
        
        while (temErro == True):
            
            temErro = False
            
            for i in range(6):
                somatorio = 0

                for j in range(5):
                    self.controller_RedeColor.set_neuron_color(0, j, [0.5, 0.0, 0.5, 1.0])
                    QTest.qWait(self.sleep)
                    somatorio += self.X_VetoresTreinamento[i][j] * self.W_Pesos[j]
                    QTest.qWait(self.sleep)
                    self.controller_RedeColor.reset_colors()
                    
                RespostaDaRede = 1 if somatorio >= 0 else -1
                
                Erro = self.Y_SaidaDesejada[i] - RespostaDaRede
                
                if Erro != 0:
                    temErro = True
                
                for j in range(5):
                    DeltaW = self.X_VetoresTreinamento[i][j] * Erro * self.TaxaAprendizagem
                    self.W_Pesos[j] = self.W_Pesos[j] + DeltaW
            self.model_RedeCalculo.ciclos += 1

        self.model_RedeCalculo.btn_testar = True
        self.model_RedeCalculo.W_Pesos = self.W_Pesos
    
    def testar(self):
        self.doenças = self.model_RedeCalculo.doenças
        self.manchas = self.model_RedeCalculo.manchas
        
        febre = 1 if self.doenças.get("Febre") else -1
        enjoo = 1 if self.doenças.get("Enjoo") else -1
        dores = 1 if self.doenças.get("Dores") else -1
        manchas = 1 if self.manchas.get("Pequena") or self.manchas.get("Grande") else -1
        
        #print(f"({self.W_Pesos[0]} * {febre}) + ({self.W_Pesos[1]} * {enjoo}) + ({self.W_Pesos[2]} * {manchas}) + ({self.W_Pesos[3]} * {dores}) + ({self.W_Pesos[4]} * {1})")
        
        somatorio = (self.W_Pesos[0] * febre) + (self.W_Pesos[1] * enjoo) + (self.W_Pesos[2] * manchas) + (self.W_Pesos[3] * dores) + (self.W_Pesos[4] * 1)
        
        self.model_RedeCalculo.diagnostico = "Doente" if somatorio >= 0 else "Saudavel"