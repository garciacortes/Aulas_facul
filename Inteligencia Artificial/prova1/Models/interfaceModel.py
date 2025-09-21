from PyQt6.QtCore import QObject, pyqtSignal


class InterfaceModel(QObject):
  new_value_graph = pyqtSignal(float)
  
  def __init__(self,):
    super().__init__()
    self._dados = []
    
  @property
  def dados(self):
    return self._dados
  
  @dados.setter
  def dados(self, value):
    self._dados.append(value)
    self.new_value_graph.emit(self._dados)