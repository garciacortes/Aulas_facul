import sys
from PyQt6.QtWidgets import QApplication

from Controllers.neural_controller import NeuralNetworkController
from Models.colorsModel import NeuralNetworkModel
from Models.Business.positions import neural_calculate
from Views.interface import Interface

if __name__ == "__main__":
    app = QApplication(sys.argv)

    layers = [5, 1]
    positions, lines, layer_indices = neural_calculate(layers)

    model = NeuralNetworkModel(positions, lines, layer_indices)
    controller = NeuralNetworkController(model)
    view = Interface(model, controller)
    
    #view.resize(400, 300)
    controller.canvas = view.canvas

    view.show()
    qr = view.frameGeometry()
    cp = QApplication.primaryScreen().availableGeometry().center()
    qr.moveCenter(cp)
    view.move(qr.topLeft())
    sys.exit(app.exec())
