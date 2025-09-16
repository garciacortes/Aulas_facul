import sys
from PyQt6.QtWidgets import QApplication

from Controllers.neuralColor_controller import NeuralColorController
from Controllers.redeNeuralController import RedeNeuralController
from Models.Business.calculoRede import CalculoRede
from Models.colorsModel import NeuralNetworkModel
from Models.Business.positions import neural_calculate
from Models.redeNeuralModel import RedeNeuralModel
from Views.interface import Interface

if __name__ == "__main__":
    app = QApplication(sys.argv)

    layers = [5, 1]
    positions, lines, layer_indices = neural_calculate(layers)

    model_Color = NeuralNetworkModel(positions, lines, layer_indices)
    redeNeural_model = RedeNeuralModel()
    controller_Color = NeuralColorController(model_Color)
    Interface_view = Interface(model_Color, controller_Color)
    calculo_Rede = CalculoRede(redeNeural_model, controller_Color)
    RedeNeural_Controller = RedeNeuralController(redeNeural_model, Interface_view, calculo_Rede)
    
    Interface_view.resize(700, 480)
    controller_Color.canvas = Interface_view.canvas

    Interface_view.show()
    qr = Interface_view.frameGeometry()
    cp = QApplication.primaryScreen().availableGeometry().center()
    qr.moveCenter(cp)
    Interface_view.move(qr.topLeft())
    sys.exit(app.exec())
