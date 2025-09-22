import sys
from PyQt6.QtWidgets import QApplication
from PyQt6.QtCore import QThread


from Controllers.interface_controller import interfaceController
from Controllers.neural_controller import NeuralNetworkController
from Models.business.redeCalculo import Network_Operation
from Models.colorsModel import NeuralNetworkModel
from Models.business.positions import neural_calculate
from Models.interfaceModel import InterfaceModel
from Models.tableModel import TableModel
from Views.interface import Interface

if __name__ == "__main__":
    app = QApplication(sys.argv)

    layers = [4,3,2]
    positions, lines, layer_indices = neural_calculate(layers)

    model = NeuralNetworkModel(positions, lines, layer_indices)
    model_Table_training = TableModel()
    model_Table_testing = TableModel()
    interface_Model = InterfaceModel()
    network_Operation = Network_Operation(model_Table_training, model_Table_testing, interface_Model)
    controller = NeuralNetworkController(model) 
    view = Interface(model, controller)
    interface_controller = interfaceController(model_Table_training, model_Table_testing, network_Operation, interface_Model, view);
    
    graph_thread = QThread()
    interface_Model .moveToThread(graph_thread)
    graph_thread.start()
    
    controller.canvas = view.canvas

    view.resize(500, 600)
    view.show()
    sys.exit(app.exec())
