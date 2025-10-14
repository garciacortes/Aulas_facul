class interfaceController():
  def __init__(self, model_Table_training, model_Table_testing, 
               network_Operation, interface_Model, view=None):
    self.model_Table_training = model_Table_training
    self.model_Table_testing = model_Table_testing
    self.network_Operation = network_Operation
    self.interface_Model = interface_Model
    self.view = view
    
    self.connections()
    
  def connections(self):
    self.interface_Model.dataErro.connect(self.view.add_value_graph_erro)