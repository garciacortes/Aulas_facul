from PyQt6.QtCore import QAbstractTableModel, Qt, QModelIndex

class TableModel(QAbstractTableModel):
  def __init__(self):
    super().__init__()
    self._dados = [["" for _ in range(7)] for _ in range(9)]
    
  def rowCount(self, parent=QModelIndex()):
    return len(self._dados)
  
  def columnCount(self, parent=QModelIndex()):
    return len(self._dados[0]) if self._dados else 0
  
  def data(self, index, role=Qt.ItemDataRole.DisplayRole):
    if role == Qt.ItemDataRole.DisplayRole or role == Qt.ItemDataRole.EditRole:
      return self._dados[index.row()][index.column()]
  
  def setData(self, index, value, role=Qt.ItemDataRole):
        if role == Qt.ItemDataRole.EditRole:
            self._dados[index.row()][index.column()] = value
            self.dataChanged.emit(index, index, [Qt.ItemDataRole.DisplayRole, Qt.ItemDataRole.EditRole])
            return True
        return False
  
  def add_row(self, row_data):
    self.beginInsertRows(QModelIndex(), self.rowCount(), self.rowCount())
    self._dados.append(row_data)
    self.endInsertRows()
  
  def flags(self, index):
    return Qt.ItemFlag.ItemIsSelectable | Qt.ItemFlag.ItemIsEnabled | Qt.ItemFlag.ItemIsEditable
  
  def headerData(self, section, orientation, role=Qt.ItemDataRole.DisplayRole):
        if role != Qt.ItemDataRole.DisplayRole:
            return None
        if orientation == Qt.Orientation.Horizontal:
            return f"{section+1}"
        else:
            return f"{section+1}"
  
  @property
  def dados(self):
    return self._dados