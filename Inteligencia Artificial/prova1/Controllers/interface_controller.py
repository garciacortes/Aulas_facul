class interfaceController():
  def __init__(self, model_Table_training, model_Table_testing, network_Operation, view=None):
    self.model_Table_training = model_Table_training
    self.model_Table_testing = model_Table_testing
    self.network_Operation = network_Operation
    self.view = view
    
    self.connections()
    
  def connections(self):
    self.view.table_training.setModel(self.model_Table_training)
    self.view.table_testing.setModel(self.model_Table_testing)
    self.view.btn_treinar.clicked.connect(self.network_Operation.btn_treinar)