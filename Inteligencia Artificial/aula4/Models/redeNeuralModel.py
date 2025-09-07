from PyQt6.QtCore import QObject, pyqtSignal

class RedeNeuralModel(QObject):
    
    ciclos_changed = pyqtSignal(float)
    W_Pesos_changed = pyqtSignal(list)
    diagnostico_changed = pyqtSignal(str)
    btn_testar_enabled = pyqtSignal(bool)
    
    def __init__(self, ):
        super().__init__()
        self._ciclos = 0
        self._W_Pesos = [0, 0, 0, 0, 0]
        self._diagnostico = "--"
        self._doenças = {}
        self._manchas = {}
        self._btn_testar = False
    
    @property
    def ciclos(self):
        return self._ciclos
    
    @ciclos.setter
    def ciclos(self, value):
        self._ciclos = value
        self.ciclos_changed.emit(value)
        
    @property
    def W_Pesos(self):
        return self._W_Pesos
    
    @W_Pesos.setter
    def W_Pesos(self, value):
        self._W_Pesos = value
        self.W_Pesos_changed.emit(value)
    
    @property
    def diagnostico(self):
        return self._diagnostico
    
    @diagnostico.setter
    def diagnostico(self, value):
        self._diagnostico = value
        self.diagnostico_changed.emit(value)
        
    @property
    def doenças(self):
        return self._doenças
    
    @doenças.setter
    def doenças(self, value):
        chave, state = next(iter(value.items()))
        self._doenças[chave] = state
        
    @property
    def manchas(self):
        return self._manchas
    
    @manchas.setter
    def manchas(self, value):
        chave, state = next(iter(value.items()))
        self._manchas[chave] = state
        
    @property
    def btn_testar(self):
        return self._btn_testar
    
    @btn_testar.setter
    def btn_testar(self, value):
        self._btn_testar = value
        self.btn_testar_enabled.emit(value)