from PyQt6.QtCore import QAbstractListModel, Qt

class PopulacaoModel(QAbstractListModel):
    def __init__(self, populacao = None):
        super().__init__()
        self._populacao = populacao or []
    
    def data(self, index, role):
        if role == Qt.ItemDataRole.DisplayRole:
            return str(self._populacao[index.row()])
    
    def rowCount(self, index):
        return len(self._populacao)
    
    def atualizar(self, nova_populacao):
        self.beginResetModel()
        self._populacao = list(nova_populacao)
        self.endResetModel()
    
    def clear(self):
        self.atualizar([])