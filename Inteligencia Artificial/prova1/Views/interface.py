from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QVBoxLayout, QTableView, QHBoxLayout,
    QGroupBox, QPushButton, QSizePolicy, QApplication, QLabel,
    QComboBox, QHeaderView
)
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QCursor
import pyqtgraph as pg

from Views.neural_view import NeuralNetworkCanvas

class Interface(QMainWindow):
    def __init__(self, model, controller):
        super().__init__()
        self.setWindowTitle("Neural Network Viewer")
        central = QWidget()
        self.setCentralWidget(central)
        
        layout = QHBoxLayout()
        group_layout_training = QHBoxLayout()
        layout_training_input = QVBoxLayout()
        layout_training_view = QVBoxLayout()
        layout_training_view2 = QVBoxLayout()
        layout_answer = QVBoxLayout()
        left, top, right, bottom = layout_training_view.getContentsMargins()
        layout_training_view.setContentsMargins(60, top, right, bottom)
                
        group_layout_testing = QVBoxLayout()

        self.canvas = NeuralNetworkCanvas(model, controller)
        
        self.groupBox_training = QGroupBox("Base de Treinamento")
        self.groupBox_testing = QGroupBox("Teste da Rede Neural")
        self.groupBox_testing.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Expanding)
        self.groupBox_training.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Expanding)
        
        container_inputs = QWidget()
        container_view = QWidget()
        container_answer = QWidget()
        container_inputs.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Fixed)
        container_view.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Fixed)
        
        self.lbl_fonte = QLabel("Fonte")
        self.lbl_letra = QLabel("Letra")
        self.combo_fonte = QComboBox()
        self.combo_letra = QComboBox()
        self.table_training = QTableView()
        self.table_testing = QTableView()
        self.plot_graph = pg.PlotWidget()
        self.btn_treinar = QPushButton("Treinar")
        self.btn_testar = QPushButton("Testar")
        self.lbl_answer_title = QLabel("Resposta da Rede Neural:")
        self.lbl_answer = QLabel("---")
        self.lbl_ciclos = QLabel("Ciclos: --")
        
        self.lbl_ciclos.setStyleSheet("font-size: 16px;")
        self.lbl_answer.setStyleSheet("font-size: 16px; font-weight: bold")
        self.lbl_answer_title.setStyleSheet("font-size: 16px;")
        
        self.combo_fonte.addItems(["Fonte 1", "Fonte 2", "Fonte 3"])
        self.combo_fonte.setObjectName("Fonte")
        self.combo_fonte.setCurrentIndex(-1)
        self.combo_letra.addItems(["A", "B", "C", "D", "E", "J", "K"])
        self.combo_letra.setObjectName("Letra")
        self.combo_letra.setCurrentIndex(-1)
        
        self.table_training.horizontalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        self.table_training.verticalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        self.table_training.setSelectionMode(QTableView.SelectionMode.NoSelection)
        self.table_training.setFocusPolicy(Qt.FocusPolicy.NoFocus)
        self.table_training.setEditTriggers(QTableView.EditTrigger.NoEditTriggers)
        
        self.table_testing.verticalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        self.table_testing.horizontalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        self.table_testing.setEditTriggers(QTableView.EditTrigger.AllEditTriggers)
        
        self.table_training.setFixedSize(250,250)
        self.table_testing.setFixedSize(250,250)
        
        self.plot_graph.setFixedSize(300, 150)
        self.plot_graph.setMouseEnabled(x=False, y=False)
        self.plot_graph.getViewBox().setMenuEnabled(False)
        self.plot_graph.setBackground('w')
        self.plot_graph.getAxis("left").setPen('k')
        self.plot_graph.getAxis("bottom").setPen('k')
        
        self.btn_treinar.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Fixed)
        self.btn_treinar.setCursor(QCursor(Qt.CursorShape.PointingHandCursor))
        self.btn_treinar.setStyleSheet("""
            QPushButton {
                background-color: lightgray;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 16px;
                padding-right: 8px;
                padding-left: 8px;
            }
        """)
        
        self.btn_testar_enabled(False)
        self.btn_testar.setSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Fixed)
        self.btn_testar.setCursor(QCursor(Qt.CursorShape.PointingHandCursor))
        self.btn_testar.setStyleSheet("""
            QPushButton {
                margin-top: 8px;
                background-color: lightgray;
                border: 1px solid black;
                border-radius: 10px;
                font-size: 16px;
                padding-right: 8px;
                padding-left: 8px;
            }
        """)
        
        container_inputs.setLayout(layout_training_input)
        container_view.setLayout(layout_training_view2)
        container_answer.setLayout(layout_answer)
        
        group_layout_training.addWidget(container_inputs, alignment=Qt.AlignmentFlag.AlignTop)
        group_layout_training.addLayout(layout_training_view)
        layout_training_view.addWidget(self.table_training, alignment=Qt.AlignmentFlag.AlignTop | Qt.AlignmentFlag.AlignCenter)
        layout_training_view.addWidget(container_view, alignment=Qt.AlignmentFlag.AlignBottom)
        #layout_training_view.addSpacing(60)
        layout_training_view2.addWidget(self.plot_graph)
        layout_training_view2.addWidget(self.lbl_ciclos, alignment=Qt.AlignmentFlag.AlignLeft | Qt.AlignmentFlag.AlignVCenter)
        layout_training_view2.addWidget(self.btn_treinar, alignment=Qt.AlignmentFlag.AlignRight)
        
        layout_training_input.addWidget(self.lbl_fonte)
        layout_training_input.addWidget(self.combo_fonte)
        layout_training_input.addWidget(self.lbl_letra)
        layout_training_input.addWidget(self.combo_letra)
        
        group_layout_testing.addWidget(self.table_testing)
        group_layout_testing.addWidget(container_answer, alignment=Qt.AlignmentFlag.AlignCenter)
        layout_answer.addWidget(self.lbl_answer_title)
        layout_answer.addWidget(self.lbl_answer, alignment=Qt.AlignmentFlag.AlignCenter)
        
        group_layout_testing.addWidget(self.btn_testar, alignment=Qt.AlignmentFlag.AlignBottom | Qt.AlignmentFlag.AlignRight)
        
        layout.addWidget(self.groupBox_training)
        layout.addWidget(self.groupBox_testing)
        #layout.addWidget(self.canvas.native)
        
        self.groupBox_training.setLayout(group_layout_training)
        self.groupBox_testing.setLayout(group_layout_testing)
        central.setLayout(layout)
        
    def add_value_graphics(self, values):
        self.bars = pg.BarGraphItem(x=list(range(len(values))), height=values, width=0.6, brush='blue')
        self.plot_graph.addItem(self.bars)
        QApplication.processEvents() 
        
    def result_changed(self, value):
        self.lbl_answer.setText(value)
        
    def ciclos_changed(self, value):
        self.lbl_ciclos.setText((f"Ciclos: {value}"))
    
    def btn_testar_enabled(self, state):
        self.btn_testar.setEnabled(state)