import numpy as np
from PyQt6.QtCore import QModelIndex, QObject, Qt
from PyQt6.QtTest import QTest
from PyQt6.QtGui import QColor

class Network_Operation(QObject):
  def __init__(self, model_Table_training, model_Table_testing, interface_Model):
    super().__init__()
    self.model_Table_training = model_Table_training
    self.model_Table_testing = model_Table_testing
    self.interface_Model = interface_Model
    
    self.quantidadeVetoresTreinamento = 100
    self.vetorEntradas_X = np.zeros(self.quantidadeVetoresTreinamento)
    self.vetorSaidaDesejada_Y = np.zeros(self.quantidadeVetoresTreinamento)
    
    self.pesosW = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.pesosV = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.pesosW_bias = None
    self.pesosV_bias = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.quantidadeNeuroniosEscondidos = 100
  
  def PreencherGrid(self):
    
    posAtual = 0
    
    for i in np.arange(0, (2 * np.pi), (2 * np.pi) / self.quantidadeVetoresTreinamento):
      
      y = np.sin(i) * np.sin(2 * i)
      
      self.vetorEntradas_X[posAtual] = i
      self.vetorSaidaDesejada_Y[posAtual] = y
      posAtual += 1
      
      # Grafico futuramente
      
      if (posAtual >= self.quantidadeVetoresTreinamento):
        break
    
    return
    
  def initPeso(self, quantidadeNeuroniosEscondidos):
    self.pesosW = np.random.uniform(-1/3, 1/3, size=quantidadeNeuroniosEscondidos)
    self.pesosW_bias = np.random.uniform(-1/3, 1/3)
    
    self.pesosV = np.random.uniform(-1/3, 1/3, size=quantidadeNeuroniosEscondidos)
    self.pesosV_bias = np.random.uniform(-1/3, 1/3, size=quantidadeNeuroniosEscondidos)
  
  def derivadaDaFuncaoDeAtivacao(x):
    # forma +- vetorizada com np mas com calculos manuais
    # funcaoX = (2 / (1 + np.exp(-1 * x))) -1 
    # return (0.5 * ((1 + funcaoX) * (1 - funcaoX)))
    
    return 1 - np.tanh(x)**2 # forma direta e vetorizada com NP
  
  def funcaoDeAtivacao(x):
    return np.tanh(x)
    
    
  def btn_treinar(self):
    ciclos = 0
    numeroDeCiclosDesejados = 10
    
    while(ciclos != numeroDeCiclosDesejados):
      
      ciclos += 1
      erroParcial = 0
      
      for i in range(self.quantidadeVetoresTreinamento):
        z_in = self.vetorEntradas_X[i] * self.pesosW + self.pesosW_bias
        zj = self.funcaoDeAtivacao(z_in)
      
        y_in = np.dot(zj, self.pesosV) + self.pesosV_bias
        y = self.funcaoDeAtivacao(y_in)
        
        erroParcial = (self.vetorSaidaDesejada_Y[i] - y) * self.derivadaDaFuncaoDeAtivacao(y_in)
        
        self.pesosV += self.taxaAprendizagem * erroParcial * zj
        self.pesosV_bias += self.taxaAprendizagem * erroParcial

        delta_hidden = self.derivadaDaFuncaoDeAtivacao(z_in) * (erroParcial * self.pesosV)
        self.pesosW += self.taxaAprendizagem * delta_hidden * self.vetorEntradas_X[i]
        self.pesosW_bias += self.taxaAprendizagem * delta_hidden  
  
  # def btn_testar(self):