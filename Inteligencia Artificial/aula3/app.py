from PyQt6.QtWidgets import QApplication
from views.interface import Interface

if __name__ == "__main__":
    app = QApplication([])
    janela = Interface()
    janela.show()
    app.exec()
