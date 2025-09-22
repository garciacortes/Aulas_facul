import numpy as np
from PyQt6.QtCore import QModelIndex, QObject, Qt
from PyQt6.QtTest import QTest
from PyQt6.QtGui import QColor
import time

class Network_Operation(QObject):
  def __init__(self, model_Table_training, model_Table_testing, interface_Model):
    super().__init__()
    self.model_Table_training = model_Table_training
    self.model_Table_testing = model_Table_testing
    self.interface_Model = interface_Model
    
    self.fontes = np.array([
        [-1, -1, 1, 1, -1, -1, -1,
         -1, -1, -1, 1, -1, -1, -1,
         -1, -1, -1, 1, -1, -1, -1,
         -1, -1, 1, -1, 1, -1, -1,
         -1, -1, 1, -1, 1, -1, -1,
         -1, 1, 1, 1, 1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         1, 1, 1, -1, 1, 1, 1, 1], #Fonte 1 letra A
        
        [1, 1, 1, 1, 1, 1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, 1, 1, 1, 1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         1, 1, 1, 1, 1, 1, -1, 1], #Fonte 1 letra B
        
        [-1, -1, 1, 1, 1, 1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, -1, 1, 1, 1, 1, -1, 1], #Fonte 1 letra C 
        
        [1, 1, 1, 1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, 1, -1, 
         1, 1, 1, 1, 1, -1, -1, 1], #Fonte 1 letra D
        
        [1, 1, 1, 1, 1, 1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, -1,
         -1, 1, -1, 1, -1, -1, -1,
         -1, 1, 1, 1, -1, -1, -1,
         -1, 1, -1, 1, -1, -1, -1,
         -1, 1, -1, -1, -1, -1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         1, 1, 1, 1, 1, 1, 1, 1], #Fonte 1 letra E
        
        [-1, -1, -1, 1, 1, 1, 1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, -1, 1, 1, 1, -1, -1, 1], #Fonte 1 letra J
        
        [1, 1, 1, -1, -1, 1, 1,
         -1, 1, -1, -1, 1, -1, -1,
         -1, 1, -1, 1, -1, -1, -1,
         -1, 1, 1, -1, -1, -1, -1,
         -1, 1, 1, -1, -1, -1, -1,
         -1, 1, -1, 1, -1, -1, -1,
         -1, 1, -1, -1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         1, 1, 1, -1, -1, 1, 1, 1],#Fonte 1 letra K
        
        [-1, -1, -1, 1, -1, -1, -1,
         -1, -1, -1, 1, -1, -1, -1,
         -1, -1, -1, 1, -1, -1, -1,
         -1, -1, 1, -1, 1, -1, -1,
         -1, -1, 1, -1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, 1, 1, 1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1, 1], #Fonte 2 letra A
        
        [1, 1, 1, 1, 1, 1, -1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, 1, 1, 1, 1, 1, -1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, 1, 1, 1, 1, 1, -1, 1], #Fonte 2 letra B
        
        [-1, -1, 1, 1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, -1, 1, 1, 1, -1, -1, 1], #Fonte 2 letra C 
        
        [1, 1, 1, 1, 1, -1, -1,
         1, -1, -1, -1, -1, 1, -1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, 1, -1, 
         1, 1, 1, 1, 1, -1, -1, 1], #Fonte 2 letra D
        
        [1, 1, 1, 1, 1, 1, 1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, 1, 1, 1, 1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, 1, 1, 1, 1, 1, 1, 1], #Fonte 2 letra E
        
        [-1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, -1, 1, 1, 1, -1, -1, 1], #Fonte 2 letra J
        
        [1, -1, -1, -1, -1, 1, -1,
         1, -1, -1, -1, 1, -1, -1,
         1, -1, -1, 1, -1, -1, -1,
         1, -1, 1, -1, -1, -1, -1,
         1, 1, -1, -1, -1, -1, -1,
         1, -1, 1, -1, -1, -1, -1,
         1, -1, -1, 1, -1, -1, -1,
         1, -1, -1, -1, 1, -1, -1,
         1, -1, -1, -1, -1, 1, -1, 1], #Fonte 2 letra K 
        
        [-1, -1, -1, 1, -1, -1, -1,
         -1, -1, -1, 1, -1, -1, -1,
         -1, -1, 1, -1, 1, -1, -1,
         -1, -1, 1, -1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, 1, 1, 1, 1, -1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, 1, -1, -1, -1, 1, 1, 1], #Fonte 3 letra A
        
        [1, 1, 1, 1, 1, 1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, 1, 1, 1, 1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         1, 1, 1, 1, 1, 1, -1, 1], #Fonte 3 letra B
        
        [-1, -1, 1, 1, 1, -1, 1,
         -1, 1, -1, -1, -1, 1, 1,
         1, -1, -1, -1, -1, -1, 1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, -1,
         1, -1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, -1, 1, 1, 1, -1, -1, 1], #Fonte 3 letra C 
        
        [1, 1, 1, 1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, -1, 1, -1, 
         1, 1, 1, 1, 1, -1, -1, 1], #Fonte 3 letra D
        
        [1, 1, 1, 1, 1, 1, 1,
         -1, 1, -1, -1, -1, -1, 1,
         -1, 1, -1, -1, 1, -1, -1,
         -1, 1, 1, 1, 1, -1, -1,
         -1, 1, -1, -1, 1, -1, -1,
         -1, 1, -1, -1, -1, -1, -1,
         -1, 1, -1, -1, -1, -1, -1,
         -1, 1, -1, -1, -1, -1, 1,
         1, 1, 1, 1, 1, 1, 1, 1], #Fonte 3 letra E
        
        [-1, -1, -1, -1, 1, 1, 1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, -1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, -1, 1, 1, 1, -1, -1, 1], #Fonte 3 letra J
        
        [1, 1, 1, -1, -1, 1, 1,
         -1, 1, -1, -1, -1, 1, -1,
         -1, 1, -1, -1, 1, -1, -1,
         -1, 1, -1, 1, -1, -1, -1,
         -1, 1, 1, -1, -1, -1, -1,
         -1, 1, -1, 1, -1, -1, -1,
         -1, 1, -1, -1, 1, -1, -1,
         -1, 1, -1, -1, -1, 1, -1,
         1, 1, 1, -1, -1, 1, 1, 1], #Fonte 3 letra K
        
    ])
    self.Saidas_desejadas = np.array([
        [1,-1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1,-1, -1, -1, -1],
        [-1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1,-1], 
        [-1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1],
        [-1, -1, -1, 1, -1, -1, -1, -1, -1,-1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1],
        [-1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1],
        [-1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1],
        [-1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1]
    ])
    
    self.TaxaAprendizagem = 0.002
    self.QuantidadeMaximaCiclos = 1000
    self.ErroMinimo = 0.0001
    
    self.fonte_combo = -1
    self.letra_combo = -1
    self.pesos = []
  
  def PreencherGrid(self, index_recebido):
    combo = self.sender() 
    
    if combo.objectName() == "Fonte":
      self.fonte_combo = index_recebido
    else: 
      self.letra_combo = index_recebido
      
    if self.fonte_combo == -1 or self.letra_combo == -1:
      return
    
    index_final = self.fonte_combo * 7 + self.letra_combo
    linha = 0
    coluna = 0
    
    for i in range(63):
      index_model = self.model_Table_training.index(linha, coluna)
      QTest.qWait(100)
      if self.fontes[index_final][i] == 1:
        self.model_Table_training.setData(index_model, QColor("black"), role=Qt.ItemDataRole.BackgroundRole)
      else: 
        self.model_Table_training.setData(index_model, QColor("white"), role=Qt.ItemDataRole.BackgroundRole)

      coluna += 1
      if coluna == 7:
        coluna = 0
        linha += 1
    
  def btn_treinar(self):
    self.pesos = np.zeros((7, 64))
    
    self.interface_Model.dados = -99999999999
    
    ErroQuadraticoMedio = 1
    SomaErroQuadraticoCiclo = 0
    ErroQuadraticoMedioAtual = 0
    ErroQuadraticoMedioAnterior = 1
    
    Ciclos = 0
    erro = 0
    
    while (abs(ErroQuadraticoMedioAtual - ErroQuadraticoMedioAnterior) > self.ErroMinimo 
           and (Ciclos < self.QuantidadeMaximaCiclos)):
      
      SomaErroQuadraticoCiclo = 0
      ErroQuadraticoMedioAnterior = ErroQuadraticoMedioAtual
      
      for amostraTreinamento in range(21):
        
        for neuronioSaida in range(7):
          
          sinapse = 0
          
          for j in range(64):
            
            sinapse = sinapse + (self.fontes[amostraTreinamento][j] * self.pesos[neuronioSaida][j])
          
          erro = self.Saidas_desejadas[neuronioSaida][amostraTreinamento] - sinapse
          
          for j in range(64):
            
            deltaW = self.fontes[amostraTreinamento][j] * erro * self.TaxaAprendizagem
            self.pesos[neuronioSaida][j] = self.pesos[neuronioSaida][j] + deltaW
          
          SomaErroQuadraticoCiclo = SomaErroQuadraticoCiclo + pow(erro, 2)
          
      ErroQuadraticoMedio = SomaErroQuadraticoCiclo / 21
      
      ErroQuadraticoMedioAtual = ErroQuadraticoMedio
      
      self.interface_Model.ciclos = 1
    
      self.interface_Model.dados = abs(ErroQuadraticoMedio)
    #mostrar ciclos
    self.interface_Model.finished = True
  
  def btn_testar(self):
    
    matriz = np.array(self.model_Table_testing.dados)
    Letras = ["A", "B", "C", "D", "E", "J", "k"]
    
    vetor = np.where(matriz == "#", 1, -1).flatten()
    vetor = np.append(vetor, 1)
    
    for i in range(7):
      
      resultado = 0
      for j in range(64):
        
        resultado = resultado + (vetor[j] * self.pesos[i][j])

      if resultado >= 0:
        self.interface_Model.result = Letras[i]