from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QTableView, QHBoxLayout,
    QGroupBox, QPushButton, QSizePolicy, QApplication, QLineEdit
)
from PyQt6.QtCore import Qt, QLocale
from PyQt6.QtGui import QCursor, QIntValidator, QDoubleValidator
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
        
        layout_inputs_user.setContentsMargins(0, 18, 0, 18)
        
        self.graph_func = pg.PlotWidget()
        self.graph_erro = pg.PlotWidget()
        
        self.input_ciclos = QLineEdit()
        self.input_taxaAprendizagem = QLineEdit()
        self.input_momento = QLineEdit()
        
        self.btn_treinar = QPushButton("Treinar")
        
        self.input_ciclos.setValidator(QIntValidator())
        locale = QLocale(QLocale.Language.English, QLocale.Country.UnitedStates)
        validador = QDoubleValidator()
        validador.setNotation(QDoubleValidator.Notation.StandardNotation)
        validador.setLocale(locale)
        self.input_taxaAprendizagem.setValidator(validador) 
        self.input_momento.setValidator(validador) 
        
        
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
        
        layout_inputs_user.addWidget(self.input_taxaAprendizagem)
        layout_inputs_user.addWidget(self.input_momento)
        layout_inputs_user.addWidget(self.input_ciclos)
        
        layout_inputs_main.addLayout(layout_inputs_user)
        
        layout_inputs_main.addWidget(self.btn_treinar, alignment=Qt.AlignmentFlag.AlignTop)
        
        layout_graphs.addWidget(self.graph_func)
        layout_graphs.addWidget(self.graph_erro)
        
        layout.addLayout(layout_inputs_main)
        layout.addLayout(layout_graphs)
        
        self.setCentralWidget(central)
        central.setLayout(layout)
        
    def add_value_graph_erro(self, values):
        if values.size != 0:
            self.curva_erro.setData(values[0], values[1]) 
        else:
            self.curva_erro.clear()
        QApplication.processEvents()
        
    def add_value_graph_func(self, values):
        if values.size != 0:
            self.curva_func.setData(values[0], values[1]) 
        else:
            self.curva_func.clear()
        QApplication.processEvents()
        