from PyQt6.QtWidgets import (
    QMainWindow, QApplication, QWidget, QGridLayout, 
    QPushButton, QListView, QLabel
)
from PyQt6.QtCore import Qt
from controllers.arvore_Genetica import ArvoreGenetica
from models.populacao_Model import PopulacaoModel
from PyQt6.QtGui import QFont, QTextCursor

class Interface(QMainWindow):
    def __init__(self,):
        super().__init__()
        
        self.arvore = ArvoreGenetica()
        
        self.setWindowTitle("Arvore Genetica")
        self.resize(600, 600)
        
        center_widget = QWidget()
        self.setCentralWidget(center_widget)
        layout  = QGridLayout()
        center_widget.setLayout(layout)
        
        self.pop_initial_title = QLabel("População Inicial")
        self.pop_evolution_title = QLabel("População após Evolução")
        self.pop_initial_title.setStyleSheet("font-size: 15px; font-weight: bold;")
        self.pop_evolution_title.setStyleSheet("font-size: 15px; font-weight: bold;")
        
        self.qtd_Geracoes_Label = QLabel(f"Quantidade Gerações: {0}")
        self.qtd_Geracoes_Label.setStyleSheet("font-size: 14px; font-weight: bold;")
        
        
        self.btn_Iniciar = QPushButton("Iniciar")
        self.btn_Iniciar.setFixedSize(100, 30)
        self.btn_Iniciar.setStyleSheet("""
            font-weight: bold;
            border: 1px solid black;
            border-radius: 10px;
            font-size: 13px;
        """)
        self.btn_Iniciar.clicked.connect(self.arvore.iniciar)
        
        self.pop_initial_model = PopulacaoModel()
        self.pop_initial_view = QListView()
        self.pop_initial_view.setModel(self.pop_initial_model)
        self.pop_initial_view.setFont(QFont("arial", 12))
        
        self.pop_evolution_model = PopulacaoModel()
        self.pop_evolution_view = QListView()
        self.pop_evolution_view.setModel(self.pop_evolution_model)
        self.pop_evolution_view.setFont(QFont("arial", 12))
        
        self.arvore.pop_initial_atualizar.connect(self.pop_initial_model.atualizar)
        self.arvore.pop_evolution_atualizar.connect(self.pop_evolution_model.atualizar)
        self.arvore.pop_initial_clear.connect(self.pop_initial_model.clear)
        self.arvore.pop_evolution_clear.connect(self.pop_evolution_model.clear)
        self.arvore.geracoes_atualizar.connect(
                lambda val: self.qtd_Geracoes_Label.setText(f"Quantidade Gerações: {val}")
        )
        
        layout.addWidget(self.pop_initial_title, 0, 0)
        layout.addWidget(self.pop_evolution_title, 0, 1)
        layout.addWidget(self.pop_initial_view, 1, 0)
        layout.addWidget(self.pop_evolution_view, 1, 1)
        layout.addWidget(self.btn_Iniciar, 2, 0, alignment=Qt.AlignmentFlag.AlignCenter)
        layout.addWidget(self.qtd_Geracoes_Label, 2, 1, alignment=Qt.AlignmentFlag.AlignRight)

        self.setLayout(layout)