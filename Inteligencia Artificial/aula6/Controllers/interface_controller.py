class interfaceController():
  def __init__(self, modelTable, view=None):
    self.modelTable = modelTable
    self.view = view
    
    self.connections()
    
  def connections(self):
    self.view.table.setModel(self.modelTable)