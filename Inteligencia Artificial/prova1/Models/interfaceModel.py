from PyQt6.QtCore import QObject, pyqtSignal


class InterfaceModel(QObject):
  new_value_graph = pyqtSignal(list)
  result_changed = pyqtSignal(str)
  ciclos_changed = pyqtSignal(int)
  training_finished = pyqtSignal(bool)
  
  def __init__(self,):
    super().__init__()
    self._dados = []
    self._result = 0
    self._finished = False
    self._ciclos = 0
    
  @property
  def dados(self):
    return self._dados
  
  @dados.setter
  def dados(self, value):
    if value == -99999999999:
      self._dados.clear()
    else:
      self._dados.append(value)
    self.new_value_graph.emit(self._dados)
    
  @property
  def result(self):
    return self._result
  
  @result.setter
  def result(self, value):
    self._result = value
    self.result_changed.emit(self._result)
    
  @property
  def ciclos(self):
    return self._result
  
  @ciclos.setter
  def ciclos(self, value):
    self._ciclos += value
    self.ciclos_changed.emit(self._ciclos)
    
  @property
  def finished(self):
    return self._result
  
  @finished.setter
  def finished(self, value):
    self._finished = value
    self.training_finished.emit(self._finished)
    