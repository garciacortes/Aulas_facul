from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QLabel, QGridLayout, QHBoxLayout,
    QGroupBox, QPushButton
)
from PyQt6.QtCore import Qt
from .neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        
        central = QWidget()
        self.setCentralWidget(central)
        
        layout_Geral = QHBoxLayout()
        layout_Widget = QVBoxLayout()
        layout_Rede = QVBoxLayout()
        layout_Treino =  QGridLayout()
        layout_teste_rede =  QGridLayout()
        layout_pesos = QVBoxLayout()
        
        GroupBox_treino = QGroupBox("Treinamento")
        GroupBox_teste_rede = QGroupBox("Teste da Rede")
        layout_Widget.addWidget(GroupBox_treino)
        layout_Widget.addWidget(GroupBox_teste_rede)
        
        self.lbl_ciclos = QLabel("Quantidade de Ciclos:      --")
        self.btn_treinar = QPushButton("Treinar")
        
        self.lbl_peso1 = QLabel("w1   --") 
        self.lbl_peso2 = QLabel("w2   --")
        self.lbl_peso3 = QLabel("w3   --")
        self.lbl_peso4 = QLabel("w4   --")
        self.lbl_peso5 = QLabel("w5   --")
        
        layout_Treino.addWidget(self.lbl_ciclos, 0, 0, alignment=Qt.AlignmentFlag.AlignTop)
        layout_Treino.addWidget(self.btn_treinar, 1, 0)
        layout_pesos.addWidget(self.lbl_peso1)
        layout_pesos.addWidget(self.lbl_peso2)
        layout_pesos.addWidget(self.lbl_peso3)
        layout_pesos.addWidget(self.lbl_peso4)
        layout_pesos.addWidget(self.lbl_peso5)
        
        
        self.canvas = NeuralNetworkCanvas(model, controller)
        layout_Rede.addWidget(self.canvas.native)
        
        layout_Treino.addLayout(layout_pesos, 0, 1)
        GroupBox_treino.setLayout(layout_Treino)
        layout_Geral.addLayout(layout_Widget)
        #layout_Geral.addLayout(layout_Rede)
        central.setLayout(layout_Geral)
