class RedeNeuralController:
    
    def __init__(self, model, view, calculo_Rede):
        self.model = model
        self.view = view
        self.calculo_Rede = calculo_Rede
        
        self.connections_ui()
        
    def connections_ui(self):
        self.model.W_Pesos_changed.connect(self.view.atualuzar_Pesos)
        self.model.ciclos_changed.connect(self.view.atualizar_Ciclos)
        self.view.diagnostico_changed.clicked.connect(self.view.atualizar_Diagnostico)
        self.view.btn_treinar.clicked.connect(self.calculo_Rede.treinar)
        
    