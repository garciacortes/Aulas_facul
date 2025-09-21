class interfaceController():
  def __init__(self, model_Table_training, model_Table_testing, view=None):
    self.model_Table_training = model_Table_training
    self.model_Table_testing = model_Table_testing
    self.view = view
    
    self.connections()
    
  def connections(self):
    self.view.table_training.setModel(self.model_Table_training)
    self.view.table_testing.setModel(self.model_Table_testing)