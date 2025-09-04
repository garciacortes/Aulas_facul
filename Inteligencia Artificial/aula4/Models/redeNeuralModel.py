from PyQt6.QtCore import QObject, pyqtSignal

class RedeNeuralModel(QObject):
    
    ciclos_changed = pyqtSignal(float)
    W_Pesos_changed = pyqtSignal(list)
    diagnostico_changed = pyqtSignal(str)
    
    def __init__(self):
        super().__init__()
        self._ciclos = 0
        self._W_Pesos = [0, 0, 0, 0, 0]
        self._diagnostico = "--"
        
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
        return self.diagnostico
    
    @diagnostico.setter
    def diagnostico(self, value):
        self._diagnostico = value
        self.diagnostico_changed.emit(value)