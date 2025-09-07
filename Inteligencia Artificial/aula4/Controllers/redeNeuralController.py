class RedeNeuralController:
    
    def __init__(self, model, view, calculo_Rede):
        self.model = model
        self.view = view
        self.calculo_Rede = calculo_Rede
        
        self.connections_ui()
        
    
    def connections_ui(self):
        self.model.W_Pesos_changed.connect(self.view.atualuzar_Pesos)
        self.model.ciclos_changed.connect(self.view.atualizar_Ciclos)
        self.model.diagnostico_changed.connect(self.view.atualizar_Diagnostico)
        self.model.btn_testar_enabled.connect(self.view.btn_testar.setEnabled)
        self.view.btn_treinar.clicked.connect(self.calculo_Rede.treinar)
        self.view.checkBox_febre.toggled.connect(lambda state: setattr(self.model, "doenças", {"Febre": state}))
        self.view.checkBox_enjoo.toggled.connect(lambda state: setattr(self.model, "doenças", {"Enjoo": state}))
        self.view.checkBox_dores.toggled.connect(lambda state: setattr(self.model, "doenças", {"Dores": state}))
        self.view.radioBtn_manchasP.toggled.connect(lambda state: setattr(self.model, "manchas", {"Pequena": state}))
        self.view.radioBtn_manchasG.toggled.connect(lambda state: setattr(self.model, "manchas", {"Grande": state}))
        self.view.btn_testar.clicked.connect(self.calculo_Rede.testar)
        
    