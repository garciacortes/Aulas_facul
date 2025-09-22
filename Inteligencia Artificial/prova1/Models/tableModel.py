from PyQt6.QtCore import QAbstractTableModel, Qt, QModelIndex
from PyQt6.QtGui import QColor

class TableModel(QAbstractTableModel):
  def __init__(self):
    super().__init__()
    self._dados = [["" for _ in range(7)] for _ in range(9)]
    self._cores = [[QColor("white") for _ in range(7)] for _ in range(9)]
    
  def rowCount(self, parent=QModelIndex()):
    return len(self._dados)
  
  def columnCount(self, parent=QModelIndex()):
    return len(self._dados[0]) if self._dados else 0
  
  def data(self, index, role=Qt.ItemDataRole.DisplayRole):
    if role == Qt.ItemDataRole.DisplayRole or role == Qt.ItemDataRole.EditRole:
      return self._dados[index.row()][index.column()]
    elif role == Qt.ItemDataRole.BackgroundRole:
      return self._cores[index.row()][index.column()]
  
  def setData(self, index, value, role=Qt.ItemDataRole.EditRole):
        if not index.isValid():
            return False
        if role == Qt.ItemDataRole.EditRole:
            self._dados[index.row()][index.column()] = value
        elif role == Qt.ItemDataRole.BackgroundRole:
            self._cores[index.row()][index.column()] = value
        self.dataChanged.emit(index, index, [Qt.ItemDataRole.DisplayRole, Qt.ItemDataRole.BackgroundRole])
        return True
  
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