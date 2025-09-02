from PyQt6.QtWidgets import QMainWindow, QWidget, QVBoxLayout
from .neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        central = QWidget()
        self.setCentralWidget(central)
        layout = QVBoxLayout()
        central.setLayout(layout)

        self.canvas = NeuralNetworkCanvas(model, controller)
        layout.addWidget(self.canvas.native)
