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
    self.view.table_training.setModel(self.model_Table_training)
    self.view.table_testing.setModel(self.model_Table_testing)
    self.view.btn_treinar.clicked.connect(self.network_Operation.btn_treinar)
    self.view.btn_testar.clicked.connect(self.network_Operation.btn_testar)
    self.view.combo_fonte.currentIndexChanged.connect(self.network_Operation.PreencherGrid)
    self.view.combo_letra.currentIndexChanged.connect(self.network_Operation.PreencherGrid)
    self.interface_Model.result_changed.connect(self.view.result_changed)
    self.interface_Model.training_finished.connect(self.view.btn_testar_enabled)
    self.interface_Model.ciclos_changed.connect(self.view.ciclos_changed)
    self.interface_Model.new_value_graph.connect(self.view.add_value_graphics)