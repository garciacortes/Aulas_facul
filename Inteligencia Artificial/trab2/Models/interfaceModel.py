from PyQt6.QtCore import QObject, pyqtSignal


class InterfaceModel(QObject):
  graph_erro = pyqtSignal(object)
  training_finished = pyqtSignal(bool)
  
  def __init__(self):
    super().__init__()
    self.taxaAprendizagem = None
    self.momento = None
    self.qtd_ciclos = None
    
  
  def dataErro(self, value):
    self.graph_erro.emit(value)
    
  # @property
  # def finished(self):
  #   return self._result
  
  # @finished.setter
  # def finished(self, value):
  #   self._finished = value
  #   self.training_finished.emit(self._finished)
    