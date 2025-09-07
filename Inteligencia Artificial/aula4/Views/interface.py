from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QLabel, QGridLayout, QHBoxLayout,
    QGroupBox, QPushButton, QCheckBox, QRadioButton
)
from PyQt6.QtCore import Qt

from .neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        
        self.central = QWidget()
        self.setCentralWidget(self.central)
        
        self.canvas = NeuralNetworkCanvas(model, controller)
        
        self.setup_ui()
    
    def setup_ui(self):
        layout_Geral = QHBoxLayout()
        layout_Widget = QVBoxLayout()
        layout_Rede = QVBoxLayout()
        layout_Treino =  QGridLayout()
        layout_teste_rede =  QGridLayout()
        layout_pesos = QVBoxLayout()
        layout_doenças = QVBoxLayout()
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
        
        layout_Treino.setHorizontalSpacing(100)
        layout_Treino.addWidget(self.lbl_ciclos, 0, 0, alignment=Qt.AlignmentFlag.AlignTop)
        layout_Treino.addWidget(self.btn_treinar, 1, 0)
        layout_pesos.addWidget(self.lbl_peso1)
        layout_pesos.addWidget(self.lbl_peso2)
        layout_pesos.addWidget(self.lbl_peso3)
        layout_pesos.addWidget(self.lbl_peso4)
        layout_pesos.addWidget(self.lbl_peso5)
        
        self.checkBox_febre = QCheckBox("Febre")
        self.checkBox_enjoo = QCheckBox("Enjôo")
        self.checkBox_dores = QCheckBox("Dores")
        self.radioBtn_manchasP = QRadioButton("Pequenas")
        self.radioBtn_manchasG = QRadioButton("Grandes")
        self.lbl_diagnostico = QLabel("Diagnóstico:     --")
        self.btn_testar = QPushButton("Testar")
        
        self.btn_testar.setEnabled(False)
        self.checkBox_febre.setStyleSheet("""
                QCheckBox {
                    font-size: 14px;
                }
                QCheckBox::indicator {
                    width: 23px;
                    height: 23px;
                }
        """)
        self.checkBox_enjoo.setStyleSheet("""
                QCheckBox {
                    font-size: 14px;
                }
                QCheckBox::indicator {
                    width: 23px;
                    height: 23px;
                }
        """)
        self.checkBox_dores.setStyleSheet("""
                QCheckBox {
                    font-size: 14px;
                }
                QCheckBox::indicator {
                    width: 23px;
                    height: 23px;
                }
        """)
        
        layout_teste_rede.setHorizontalSpacing(50)
        layout_doenças.addWidget(self.checkBox_febre)
        layout_doenças.addWidget(self.checkBox_enjoo)
        layout_doenças.addWidget(self.checkBox_dores)
        layout_teste_rede.addWidget(self.btn_testar, 1, 0)
        layout_teste_rede.addWidget(GroupBox_manchas, 0, 1)
        layout_teste_rede.addWidget(self.lbl_diagnostico, 1, 1, alignment=Qt.AlignmentFlag.AlignCenter)
        layout_manchas.addWidget(self.radioBtn_manchasP)
        layout_manchas.addWidget(self.radioBtn_manchasG)
        
        self.canvas.native.setFixedSize(250, 300)
        layout_Rede.addWidget(self.canvas.native, alignment=Qt.AlignmentFlag.AlignBottom)
        
        layout_Treino.addLayout(layout_pesos, 0, 1, alignment=Qt.AlignmentFlag.AlignJustify)
        
        GroupBox_treino.setLayout(layout_Treino)
        GroupBox_teste_rede.setLayout(layout_teste_rede)
        GroupBox_manchas.setLayout(layout_manchas)
        
        layout_teste_rede.addLayout(layout_doenças, 0, 0)
        
        layout_Geral.addLayout(layout_Widget)
        layout_Geral.addLayout(layout_Rede)
        
        self.central.setLayout(layout_Geral)
    
    def atualuzar_Pesos(self, values):
        for idx, value in enumerate(values, start=1):
            getattr(self, f"lbl_peso{idx}").setText(str(f"w{idx}   {value:.2f}"))
            
    def atualizar_Ciclos(self, value):
        self.lbl_ciclos.setText(f"Quantidade de Ciclos:      {int(value)}")
        
    def atualizar_Diagnostico(self, value):
        self.lbl_diagnostico.setText(f"Diagnóstico:     {value}")