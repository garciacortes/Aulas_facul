from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QTableView, QHBoxLayout,
    QGroupBox, QPushButton, QSizePolicy
)
from PyQt6.QtCore import Qt
import pyqtgraph as pg

from Views.neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        central = QWidget()
        self.setCentralWidget(central)
        layout = QHBoxLayout()
        group_layout_training = QVBoxLayout()

        self.canvas = NeuralNetworkCanvas(model, controller)
        
        self.groupBox_training = QGroupBox("Treinamento")
        
        self.table = QTableView()
        self.plot_graph = pg.PlotWidget()
        self.btn_treinar = QPushButton("Treinar")
    
        self.plot_graph.setMouseEnabled(x=False, y=False)
        self.plot_graph.getViewBox().setMenuEnabled(False)
        self.plot_graph.setBackground('w')
        self.plot_graph.showGrid(x=True, y=True, alpha=1)
        self.plot_graph.setXRange(0, 10)
        self.plot_graph.setYRange(0, 1)
        self.plot_graph.getAxis("left").setPen('k')
        #self.plot_graph.getAxis("left").setTickSpacing(1, 1e-6)
        self.plot_graph.getAxis("bottom").setPen('k')
        #self.plot_graph.getAxis("bottom").setTickSpacing(0.2, 1e-6)
        self.curve = self.plot_graph.plot(pen='b')
        
        self.btn_treinar.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Fixed)
        self.btn_treinar.setStyleSheet("""
            QPushButton {
                margin-top: 12px;
                margin-bottom: 4px;
                background-color: lightgray;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 16px;
                padding-right: 8px;
                padding-left: 8px;
            }
        """)



        group_layout_training.addWidget(self.table)
        group_layout_training.addWidget(self.plot_graph)
        group_layout_training.addWidget(self.btn_treinar, alignment=Qt.AlignmentFlag.AlignCenter)
        
        layout.addWidget(self.groupBox_training)
        layout.addWidget(self.canvas.native)
        
        self.groupBox_training.setLayout(group_layout_training)
        central.setLayout(layout)
        
    def add_value_graphics(self, value):
        self.curve.setData(value)