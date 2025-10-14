import sys
from PyQt6.QtWidgets import QApplication
from PyQt6.QtCore import QThread

from Controllers.interface_controller import interfaceController
from Models.business.redeCalculo import Network_Operation
from Models.interfaceModel import InterfaceModel
from Models.tableModel import TableModel
from Views.interface import Interface

if __name__ == "__main__":
    app = QApplication(sys.argv)

    model_Table_training = TableModel()
    model_Table_testing = TableModel()
    interface_Model = InterfaceModel()
    network_Operation = Network_Operation(model_Table_training, model_Table_testing, interface_Model)
    view = Interface()
    interface_controller = interfaceController(model_Table_training, model_Table_testing, network_Operation, interface_Model, view);
    
    view.resize(500, 600)
    view.show()
    sys.exit(app.exec())
