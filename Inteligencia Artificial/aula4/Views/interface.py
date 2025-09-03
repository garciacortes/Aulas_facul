from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QLabel, QGridLayout, QHBoxLayout,
    QGroupBox, QPushButton, QComboBox, QRadioButton
)
from PyQt6.QtCore import Qt

from Models.Business.RedeNeuralCalc import CalculoRede
from .neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller, RedeNeuralCalc):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        
        self.RedeNeuralCalc = RedeNeuralCalc
        
        central = QWidget()
        self.setCentralWidget(central)
        
        layout_Geral = QHBoxLayout()
        layout_Widget = QVBoxLayout()
        layout_Rede = QVBoxLayout()
        layout_Treino =  QGridLayout()
        layout_teste_rede =  QGridLayout()
        layout_pesos = QVBoxLayout()
        layout_manchas = QHBoxLayout()
        
        GroupBox_treino = QGroupBox("Treinamento")
        GroupBox_teste_rede = QGroupBox("Teste da Rede")
        GroupBox_manchas = QGroupBox("Manchas")
        layout_Widget.addWidget(GroupBox_treino)
        layout_Widget.addWidget(GroupBox_teste_rede)
        
        self.lbl_ciclos = QLabel("Quantidade de Ciclos:      --")
        self.btn_treinar = QPushButton("Treinar")
        
        self.lbl_peso1 = QLabel("w1   --") 
        self.lbl_peso2 = QLabel("w2   --")
        self.lbl_peso3 = QLabel("w3   --")
        self.lbl_peso4 = QLabel("w4   --")
        self.lbl_peso5 = QLabel("w5   --")
        
        layout_Treino.addWidget(self.lbl_ciclos, 0, 0, 1, 4, alignment=Qt.AlignmentFlag.AlignTop)
        layout_Treino.addWidget(self.btn_treinar, 1, 0)
        layout_pesos.addWidget(self.lbl_peso1)
        layout_pesos.addWidget(self.lbl_peso2)
        layout_pesos.addWidget(self.lbl_peso3)
        layout_pesos.addWidget(self.lbl_peso4)
        layout_pesos.addWidget(self.lbl_peso5)
        
        self.btn_treinar.clicked.connect(self.RedeNeuralCalc.buttonClicar)
        
        self.comboBox_doencas = QComboBox()
        self.radioBtn_manchasP = QRadioButton("Pequenas")
        self.radioBtn_manchasG = QRadioButton("Grandes")
        self.lbl_diagnostico = QLabel("Diagnóstico:     --")
        self.btn_testar = QPushButton("Testar")
        
        self.comboBox_doencas.addItems(["Escolha a Doença ", "Febre", "Enjôo", "Dores"])
        
        layout_teste_rede.addWidget(self.comboBox_doencas, 0, 0)
        layout_teste_rede.addWidget(self.btn_testar, 1, 0)
        layout_teste_rede.addWidget(GroupBox_manchas, 0, 1)
        layout_teste_rede.addWidget(self.lbl_diagnostico, 1, 1, alignment=Qt.AlignmentFlag.AlignCenter)
        layout_manchas.addWidget(self.radioBtn_manchasP)
        layout_manchas.addWidget(self.radioBtn_manchasG)
        
        self.canvas = NeuralNetworkCanvas(model, controller)
        layout_Rede.addWidget(self.canvas.native)
        
        layout_Treino.addLayout(layout_pesos, 0, 5)
        GroupBox_treino.setLayout(layout_Treino)
        GroupBox_teste_rede.setLayout(layout_teste_rede)
        GroupBox_manchas.setLayout(layout_manchas)
        layout_Geral.addLayout(layout_Widget)
        #layout_Geral.addLayout(layout_Rede)
        central.setLayout(layout_Geral)
