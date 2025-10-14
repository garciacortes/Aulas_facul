import numpy as np
from PyQt6.QtCore import QModelIndex, QObject, Qt
from PyQt6.QtTest import QTest
from PyQt6.QtGui import QColor

class Network_Operation(QObject):
  def __init__(self, interface_Model):
    super().__init__()
    self.interface_Model = interface_Model
    
    self.quantidadeVetoresTreinamento = 100
    self.quantidadeNeuroniosEscondidos = 100
    
    self.pesosV = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.pesosV_bias = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.deltaPesoV = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.deltaPesoV_bias = np.zeros(self.quantidadeNeuroniosEscondidos)
    
    self.pesosW = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.pesosW_bias = None
    self.deltaPesoW = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.deltaPesoW_bias = None
    
    self.vetorEntradas_X = np.zeros(self.quantidadeVetoresTreinamento)
    self.vetorSaidaDesejada_Y = np.zeros(self.quantidadeVetoresTreinamento)
    
    self.z_in = np.zeros(self.quantidadeNeuroniosEscondidos)
    self.z = np.zeros(self.quantidadeNeuroniosEscondidos)
    
    self.taxaAprendizagem = 0.001
    
  
  def PreencherGrid(self):
    
    posAtual = 0
    
    for i in np.arange(0, (2 * np.pi), (2 * np.pi) / self.quantidadeVetoresTreinamento):
      
      y = np.sin(i) * np.sin(2 * i)
      
      self.vetorEntradas_X[posAtual] = i
      self.vetorSaidaDesejada_Y[posAtual] = y
      posAtual += 1
      
      # Grafico futuramente
      
      if posAtual >= self.quantidadeVetoresTreinamento:
        break
    
    return
    
  def initPeso(self, quantidadeNeuroniosEscondidos):
    self.pesosW = np.random.uniform(-1/9, 1/9, size=quantidadeNeuroniosEscondidos)
    self.pesosW_bias = np.random.uniform(-1/3, 1/3)
    
    self.pesosV = np.random.uniform(-1/3, 1/3, size=quantidadeNeuroniosEscondidos)
    self.pesosV_bias = np.random.uniform(-1/3, 1/3, size=quantidadeNeuroniosEscondidos)
  
  def derivadaDaFuncaoDeAtivacao(self, x):
    # forma +- vetorizada com np mas com calculos manuais
    # funcaoX = (2 / (1 + np.exp(-1 * x))) -1 
    # return (0.5 * ((1 + funcaoX) * (1 - funcaoX)))
    
    return 1 - np.tanh(x)**2 # forma direta e vetorizada com NP
  
  def funcaoDeAtivacao(self, x):
    return np.tanh(x)
    
    
  def btn_treinar(self):
    ciclos = 0
    numeroDeCiclosDesejados = 10
    momento = 0.005
    
    self.interface_Model.dataErro(np.array([]))
    
    self.initPeso(self.quantidadeNeuroniosEscondidos)
    
    while(ciclos != numeroDeCiclosDesejados):
      
      ciclos += 1
      erroParcial = 0
      
      for vetorAtual in  range(self.quantidadeVetoresTreinamento):
        
        y_in = 0
        
        for j in range(self.quantidadeNeuroniosEscondidos):
          
          self.z_in[j] = (self.vetorEntradas_X[vetorAtual] * self.pesosV[j])
          self.z_in[j] += self.pesosV_bias[j]
          self.z[j] = self.funcaoDeAtivacao(self.z_in[j])
          
          y_in += self.z[j] * self.pesosW[j]
        
        y_in += self.pesosW_bias
        
        y = self.funcaoDeAtivacao(y_in)
        t = self.vetorSaidaDesejada_Y[vetorAtual]
        
        deltinha = (t - y) * self.derivadaDaFuncaoDeAtivacao(y_in)
        
        erroParcial += 0.5 * ((t - y) ** 2)
        
        self.deltaPesoW = (self.taxaAprendizagem * deltinha * self.z) * momento
          
        self.deltaPesoW_bias = (self.taxaAprendizagem * deltinha) * momento
        
        for i in range(self.quantidadeNeuroniosEscondidos):
          deltinha_in = deltinha * self.pesosW[i]
          deltinha = deltinha_in * self.derivadaDaFuncaoDeAtivacao(self.z_in[i])
          
          self.deltaPesoV[i] = self.taxaAprendizagem * deltinha * self.vetorEntradas_X[vetorAtual] * momento
          self.deltaPesoV_bias[i] = (self.taxaAprendizagem * deltinha) * momento
          
          self.pesosV[i] += self.deltaPesoV[i]
          self.pesosV_bias[i] += self.deltaPesoV_bias[i]
        
        self.pesosW += self.deltaPesoW
        self.pesosW_bias += self.deltaPesoW_bias
        
        self.interface_Model.dataErro(np.array([ciclos, erroParcial]))
  
  # def btn_testar(self):