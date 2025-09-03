class CalculoRede():
    
    def __init__(self):
        self.X_VetoresTreinamento = [ 
            [ 1, 1, 1, 1, 1],
            [-1, -1, -1, -1, 1],
            [1, 1, 1, -1, 1],
            [1, -1, 1, 1, 1],
            [-1, -1, -1, 1, 1]
        ]

        self.Y_SaidaDesejada = [1, -1, -1, -1, -1, 1]

        self.W_Pesos = [0, 0, 0, 0, 0]

        self.TaxaAprendizagem = 0.02
    
    def set_InterfaceView(self, InterfaceView):
        self.InterfaceView = InterfaceView
        
    def buttonClicar(self):
        Ciclos = 0
        
        temErro = True
        
        while (temErro == True):
            
            temErro = False
            
            for i in range(6):
                
                somatorio = 0
                
                for j in range(5):
                    
                    somatorio += self.X_VetoresTreinamento[i][j] * self.W_Pesos[j]
                    
                RespostaDaRede = 1 if somatorio >= 0 else -1
                    
                Erro = self.Y_SaidaDesejada[i] - RespostaDaRede
                
                if Erro != 0:
                    temErro = True
                
                    self.W_Pesos[j] = [
                        self.W_Pesos[j] + self.X_VetoresTreinamento[i][j] * Erro * self.TaxaAprendizagem
                        for j in range(5)
                    ]
            Ciclos += 1
            
        self.InterfaceView.lbl_peso1 = str(self.W_Pesos[0])
        self.InterfaceView.lbl_peso2 = str(self.W_Pesos[1])
        self.InterfaceView.lbl_peso3 = str(self.W_Pesos[2])
        self.InterfaceView.lbl_peso4 = str(self.W_Pesos[3])
        self.InterfaceView.lbl_peso5 = str(self.W_Pesos[4])