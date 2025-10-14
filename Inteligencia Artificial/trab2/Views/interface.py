from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QTableView, QHBoxLayout,
    QGroupBox, QPushButton, QSizePolicy, QApplication
)
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QCursor
import pyqtgraph as pg

class Interface(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        central = QWidget()
        
        layout = QHBoxLayout()
        layout_graphs = QVBoxLayout()
        layout_inputs_main = QVBoxLayout()
        layout_inputs_user = QHBoxLayout()
        
        self.graph_func = pg.PlotWidget()
        self.graph_erro = pg.PlotWidget()
        
        
        self.curva_func = self.graph_func.plot([], [], pen='b', symbol='o', symbolSize=10, symbolBrush='r')
        self.graph_func.setBackground(None)
        self.curva_erro = self.graph_erro.plot([], [], pen='b', symbol='o', symbolSize=10, symbolBrush='r')
        self.graph_erro.setBackground(None)
        
        # self.btn_treinar.setStyleSheet("""
            # QPushButton {
                # margin-top: 8px;
                # background-color: lightgray;
                # border: 1px solid black;
                # border-radius: 10px;
                # font-size: 16px;
                # padding-right: 6px;
                # padding-left: 6px;
            # }
        # """)
        
        layout_graphs.addWidget(self.graph_func)
        layout_graphs.addWidget(self.graph_erro)
        
        layout_inputs_main.addLayout(layout_inputs_user)
        layout.addLayout(layout_graphs)
        layout.addLayout(layout_inputs_main)
        
        self.setCentralWidget(central)
        central.setLayout(layout)
        
    def add_value_graph_erro(self, values):
        if values.size != 0:
            self.curva_erro.addPoints([values[0]], [values[1]]) 
        else:
            self.curva_erro.clear()
        QApplication.processEvents()
        