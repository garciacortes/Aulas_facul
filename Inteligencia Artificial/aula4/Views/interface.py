from PyQt6.QtWidgets import QMainWindow, QWidget, QVBoxLayout, QLabel, QGridLayout, QFrame, QSizePolicy
from PyQt6.QtCore import Qt
from .neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        central = QWidget()
        self.setCentralWidget(central)
        
        layout_Geral = QGridLayout()
        layout_Rede = QVBoxLayout()
        
        frame = QFrame()
        layout_widget = QVBoxLayout()
        frame.setLayout(layout_widget)
        
        
        self.lbl_Title = QLabel("Treinando Rede Neural")
        self.label = QLabel("Os Pesos são P1 = -2 P2 = 2 P3 = 0 P4 = 0 P5 = 0")
        self.lbl_Title.setSizePolicy(QSizePolicy.Policy.Preferred, QSizePolicy.Policy.Fixed)
        
        frame.setEnabled(False)
        layout_widget.addWidget(self.label)
        #[layout_widget.itemAt(w).widget().setVisible(False) for w in range(layout_widget.count())]
        
        self.canvas = NeuralNetworkCanvas(model, controller)
        layout_Rede.addWidget(self.canvas.native)
        
        layout_Geral.addLayout(layout_Rede, 0, 1)
        layout_Geral.addWidget(self.lbl_Title, 0, 0)
        layout_Geral.addWidget(frame, 1, 0)
        
        central.setLayout(layout_Geral)
