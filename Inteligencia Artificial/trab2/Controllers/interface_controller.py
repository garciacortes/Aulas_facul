from PyQt6.QtWidgets import QMessageBox

class interfaceController():
  def __init__(self, network_Operation, interface_Model, view=None):
    self.network_Operation = network_Operation
    self.interface_Model = interface_Model
    self.view = view
    
    self.connections()
    
  def input_validated(self):
    msg_result = self.interface_Model.validData()
    if isinstance(msg_result, (str)):
      msg = QMessageBox()
      msg.setIcon(QMessageBox.Icon.Warning)
      msg.setWindowTitle("Entrada inválida")
      msg.setText(msg_result)
      msg.setStandardButtons(QMessageBox.StandardButton.Ok)
      msg.exec()
      return
    else:
      self.network_Operation.btn_treinar()
    
  def connections(self):
    self.interface_Model.graph_erro.connect(self.view.add_value_graph_erro)
    self.interface_Model.graph_func.connect(self.view.add_value_graph_func)
    self.view.input_taxaAprendizagem.textChanged.connect(lambda t: setattr(self.interface_Model, 
    "taxaAprendizagem", t))
    self.view.input_momento.textChanged.connect(lambda t: setattr(self.interface_Model, 
    "momento", t))
    self.view.input_ciclos.textChanged.connect(lambda t: setattr(self.interface_Model, 
    "ciclos", t))
    self.view.btn_treinar.clicked.connect(self.input_validated)