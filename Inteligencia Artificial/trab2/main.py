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

    interface_Model = InterfaceModel()
    network_Operation = Network_Operation(interface_Model)
    view = Interface()
    interface_controller = interfaceController(network_Operation, interface_Model, view);
    
    view.resize(500, 600)
    view.show()
    sys.exit(app.exec())
