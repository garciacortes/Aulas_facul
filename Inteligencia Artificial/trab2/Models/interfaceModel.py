from PyQt6.QtCore import QObject, pyqtSignal
import numpy as np

class InterfaceModel(QObject):
  graph_erro = pyqtSignal(object)
  graph_func = pyqtSignal(object)
  training_finished = pyqtSignal(bool)
  
  def __init__(self):
    super().__init__()
    self._taxaAprendizagem = None
    self._momento = None
    self._ciclos = None
    self._data_X = []
    self._data_Y = []
    
  def validData(self):
    if not isinstance(self._taxaAprendizagem, (float)):
      return "Taxa Aprendizagem não é um numero!"
    elif not isinstance(self._momento, (float)):
      return "Momento não é um numero!"
    elif not isinstance(self._ciclos, (int)):
      return "ciclos não é um numero!"
    else:
      return True
      
  
  def dataErro(self, value):
    if value.size != 0:
      self._data_X.append(value[0])
      self._data_Y.append(value[1])
    else:
      self._data_X.clear()
      self._data_Y.clear()
    self.graph_erro.emit(np.array([self._data_X, self._data_Y]))
    
  def dataFunc(self, value):
    if value.size != 0:
      self._data_X.append(value[0])
      self._data_Y.append(value[1])
    else:
      self._data_X.clear()
      self._data_Y.clear()
    self.graph_func.emit(np.array([self._data_X, self._data_Y]))
      
  @property
  def taxaAprendizagem(self):
    return self._taxaAprendizagem
  
  @taxaAprendizagem.setter
  def taxaAprendizagem(self, value):
    if value:
      self._taxaAprendizagem = float(value)
  
  @property
  def momento(self):
    return self._momento
  
  @momento.setter
  def momento(self, value):
    if value:
      self._momento = float(value)
  
  @property
  def ciclos(self):
    return self._ciclos
  
  @ciclos.setter
  def ciclos(self, value):
    if value:
      self._ciclos = int(value)