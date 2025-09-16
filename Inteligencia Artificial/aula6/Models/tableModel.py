from PyQt6.QtCore import QAbstractTableModel, Qt, QModelIndex

class TableModel(QAbstractTableModel):
  def __init__(self):
    super().__init__()
    self._dados = []
    
  def rowCount(self, parent=QModelIndex()):
    return len(self._dados)
  
  def columnCount(self, parent=QModelIndex()):
    return len(self._dados[0]) if self._dados else 0
  
  def data(self, index, role=Qt.ItemDataRole.DisplayRole):
    if role == Qt.ItemDataRole.DisplayRole or role == Qt.ItemDataRole.EditRole:
      return self._dados[index.row()][index.column()]
  
  def add_row(self, row_data):
    self.beginInsertRows(QModelIndex(), self.rowCount(), self.rowCount())
    self._dados.append(row_data)
    self.endInsertRows()