import sys
from PyQt6.QtWidgets import QApplication

from Controllers.interface_controller import interfaceController
from Controllers.neural_controller import NeuralNetworkController
from Models.colorsModel import NeuralNetworkModel
from Models.business.positions import neural_calculate
from Models.tableModel import TableModel
from Views.interface import Interface

if __name__ == "__main__":
    app = QApplication(sys.argv)

    layers = [4,3,2]
    positions, lines, layer_indices = neural_calculate(layers)

    model = NeuralNetworkModel(positions, lines, layer_indices)
    modelTable = TableModel()
    controller = NeuralNetworkController(modelTable) 
    view = Interface(model, controller)
    interface_controller = interfaceController(modelTable, view);
    
    controller.canvas = view.canvas

    view.resize(800, 500)
    view.show()
    sys.exit(app.exec())
