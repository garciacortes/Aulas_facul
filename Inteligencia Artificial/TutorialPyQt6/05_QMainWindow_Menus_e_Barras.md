# PyQt6 - Tutorial Completo: 5. QMainWindow, Menus e Barras

Até agora, usamos `QMainWindow` como uma simples janela. No entanto, seu verdadeiro poder está na sua estrutura pré-definida que inclui:

- **Barra de Menus (`QMenuBar`)**: O menu no topo da aplicação (Arquivo, Editar, Ajuda, etc.).
- **Barras de Ferramentas (`QToolBar`)**: Barras com botões de atalho (ícones), geralmente abaixo do menu.
- **Barra de Status (`QStatusBar`)**: Uma barra na parte inferior da janela para exibir mensagens e informações.
- **Widgets Ancoráveis (`QDockWidget`)**: Janelas secundárias que podem ser movidas, flutuadas ou anexadas às laterais da janela principal.

## `QAction`: O Coração da Interação

A maioria das interações em menus e barras de ferramentas não é feita com `QPushButton`, mas sim com `QAction`. Uma `QAction` é um objeto que representa uma ação (ex: "Abrir Arquivo", "Copiar"). Ela pode ser adicionada a menus e barras de ferramentas simultaneamente.

Uma `QAction` possui:

- Texto (ex: "Salvar")
- Ícone (opcional)
- Atalho de teclado (opcional, ex: `Ctrl+S`)
- Texto de ajuda (para a barra de status)
- Um sinal `triggered` que é emitido quando a ação é ativada.

## Construindo uma Interface de Editor de Texto Simples

Vamos criar um esqueleto de um editor de texto que demonstra como usar menus, barras de ferramentas e uma barra de status.

```python
import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QTextEdit, QMessageBox
)
from PyQt6.QtGui import QIcon, QAction

class EditorTexto(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Editor de Texto Básico")
        self.setGeometry(100, 100, 800, 600)

        # Define o widget central
        self.editor = QTextEdit()
        self.setCentralWidget(self.editor)

        # Chama os métodos para criar as partes da UI
        self._criar_acoes()
        self._criar_barra_menus()
        self._criar_barra_ferramentas()
        self._criar_barra_status()

    def _criar_acoes(self):
        # Ações do menu Arquivo
        # Você pode usar ícones padrão do tema do sistema ou arquivos de imagem
        # Para usar arquivos de imagem, use QIcon("caminho/icone.png")
        self.acao_novo = QAction(QIcon.fromTheme("document-new"), "&Novo", self)
        self.acao_novo.setShortcut("Ctrl+N")
        self.acao_novo.setStatusTip("Criar um novo arquivo")
        self.acao_novo.triggered.connect(self.arquivo_novo)

        self.acao_sair = QAction(QIcon.fromTheme("application-exit"), "&Sair", self)
        self.acao_sair.setShortcut("Ctrl+Q")
        self.acao_sair.setStatusTip("Sair da aplicação")
        self.acao_sair.triggered.connect(self.close) # close é um slot padrão de QMainWindow

        # Ações do menu Editar (sem funcionalidade por enquanto)
        self.acao_copiar = QAction(QIcon.fromTheme("edit-copy"), "&Copiar", self)
        self.acao_copiar.setShortcut("Ctrl+C")
        self.acao_copiar.setStatusTip("Copiar texto selecionado")
        # Conecta a ação ao slot padrão de copiar do QTextEdit
        self.acao_copiar.triggered.connect(self.editor.copy)

    def _criar_barra_menus(self):
        # A barra de menus já existe, apenas a obtemos
        barra_menus = self.menuBar()

        # Menu Arquivo
        menu_arquivo = barra_menus.addMenu("&Arquivo")
        menu_arquivo.addAction(self.acao_novo)
        menu_arquivo.addSeparator() # Adiciona uma linha separadora
        menu_arquivo.addAction(self.acao_sair)

        # Menu Editar
        menu_editar = barra_menus.addMenu("&Editar")
        menu_editar.addAction(self.acao_copiar)
        # Adicione outras ações (Recortar, Colar) aqui...

    def _criar_barra_ferramentas(self):
        # Cria uma nova barra de ferramentas
        barra_ferramentas = self.addToolBar("Principal")
        barra_ferramentas.setMovable(False) # Impede que seja movida
        barra_ferramentas.addAction(self.acao_novo)
        barra_ferramentas.addAction(self.acao_copiar)

    def _criar_barra_status(self):
        self.statusBar().showMessage("Pronto")
        # O StatusTip das QActions aparecerá aqui automaticamente

    # SLOTS
    def arquivo_novo(self):
        # Lógica para criar um novo arquivo
        # Primeiro, verificamos se há alterações não salvas
        resposta = QMessageBox.question(self, "Novo Arquivo", "Você tem certeza? Alterações não salvas serão perdidas.")

        if resposta == QMessageBox.StandardButton.Yes:
            self.editor.clear()
            self.statusBar().showMessage("Novo arquivo criado.")

    # Sobrescrevendo o evento de fechamento da janela
    def closeEvent(self, event):
        resposta = QMessageBox.question(
            self,
            "Sair",
            "Deseja realmente sair da aplicação?",
            QMessageBox.StandardButton.Yes | QMessageBox.StandardButton.No,
            QMessageBox.StandardButton.No
        )
        if resposta == QMessageBox.StandardButton.Yes:
            event.accept() # Confirma o fechamento
        else:
            event.ignore() # Cancela o fechamento


if __name__ == '__main__':
    app = QApplication(sys.argv)
    # Define um fallback para os ícones, caso o tema do sistema não os forneça
    QIcon.setFallbackThemeName("breeze")
    janela = EditorTexto()
    janela.show()
    sys.exit(app.exec())
```

### Pontos Chave

- **Reutilização de `QAction`**: A mesma `QAction` (`self.acao_novo`) foi adicionada tanto ao menu quanto à barra de ferramentas. A lógica está em um só lugar.
- **`self.menuBar()` e `self.addToolBar()`**: Métodos convenientes de `QMainWindow` para acessar e criar esses componentes.
- **`setStatusTip`**: O texto definido aqui é exibido automaticamente na `QStatusBar` quando o mouse passa sobre a ação.
- **`closeEvent`**: Este é um "manipulador de eventos". `QMainWindow` nos permite interceptar o evento de fechamento para executar lógica customizada, como pedir confirmação ao usuário.

Esta estrutura é a base para a maioria das aplicações de desktop complexas.
