# PyQt6 - Tutorial Completo: 3. Layouts e Organização

Até agora, colocamos um único widget como o widget central. Para construir interfaces úteis, precisamos organizar múltiplos widgets. Posicionar cada widget manualmente com `.move(x, y)` é ineficiente e não se adapta a diferentes tamanhos de janela ou fontes.

A solução é usar **Gerenciadores de Layout**. Eles organizam os widgets automaticamente dentro de um container.

O processo geral é:

1.  Criar um widget "container" principal (normalmente um `QWidget`).
2.  Criar uma instância de um gerenciador de layout (ex: `QVBoxLayout`).
3.  Adicionar seus widgets ao layout (`layout.addWidget(...)`).
4.  Definir o layout no seu widget container (`container.setLayout(layout)`).
5.  Definir o widget container como o widget central da sua `QMainWindow`.

## 1. `QVBoxLayout`: Layout Vertical

Empilha os widgets um sobre o outro, verticalmente.

```python
import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QWidget,
    QLabel, QPushButton, QLineEdit, QVBoxLayout
)

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Layout Vertical (QVBoxLayout)")

        # 1. Cria o layout
        layout = QVBoxLayout()

        # 2. Adiciona widgets ao layout
        layout.addWidget(QLabel("Formulário de Cadastro"))
        layout.addWidget(QLineEdit(placeholderText="Nome de usuário"))
        layout.addWidget(QLineEdit(placeholderText="Senha", echoMode=QLineEdit.EchoMode.Password))
        layout.addWidget(QPushButton("Entrar"))

        # 3. Cria um widget container e aplica o layout a ele
        container = QWidget()
        container.setLayout(layout)

        # 4. Define o container como widget central
        self.setCentralWidget(container)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    janela = JanelaPrincipal()
    janela.show()
    sys.exit(app.exec())
```

## 2. `QHBoxLayout`: Layout Horizontal

Posiciona os widgets lado a lado, horizontalmente.

```python
# ... (imports similares)
from PyQt6.QtWidgets import QHBoxLayout

# ... (dentro da classe JanelaPrincipal)
        # ...
        layout = QHBoxLayout()

        layout.addWidget(QPushButton("OK"))
        layout.addWidget(QPushButton("Cancelar"))
        layout.addWidget(QPushButton("Aplicar"))
        # ... (cria container, aplica layout, etc.)
```

## 3. `QGridLayout`: Layout em Grade

Organiza os widgets em uma grade de linhas e colunas, como uma planilha. É extremamente útil para formulários complexos.

```python
# ... (imports similares)
from PyQt6.QtWidgets import QGridLayout, QTextEdit

# ... (dentro da classe JanelaPrincipal)
        # ...
        layout = QGridLayout()

        # Adiciona widgets especificando (linha, coluna)
        layout.addWidget(QLabel("Nome:"), 0, 0)
        layout.addWidget(QLineEdit(), 0, 1)

        layout.addWidget(QLabel("Email:"), 1, 0)
        layout.addWidget(QLineEdit(), 1, 1)

        # Um widget pode ocupar múltiplas colunas/linhas
        # (widget, linha, coluna, rowspan, colspan)
        layout.addWidget(QLabel("Comentários:"), 2, 0)
        layout.addWidget(QTextEdit(), 2, 1, 1, 2) # Ocupa 1 linha, 2 colunas

        layout.addWidget(QPushButton("Enviar"), 3, 1)
        # ... (cria container, aplica layout, etc.)
```

## 4. Aninhando Layouts

O verdadeiro poder vem de combinar layouts. Você pode adicionar um layout dentro de outro para criar interfaces complexas.

Exemplo: Um formulário (`QGridLayout`) com uma linha de botões (`QHBoxLayout`) na parte inferior.

```python
# ... (dentro da classe JanelaPrincipal)
        # Layout principal que organiza tudo verticalmente
        layout_principal = QVBoxLayout()

        # Layout para o formulário
        layout_formulario = QGridLayout()
        layout_formulario.addWidget(QLabel("Nome:"), 0, 0)
        layout_formulario.addWidget(QLineEdit(), 0, 1)
        layout_formulario.addWidget(QLabel("Email:"), 1, 0)
        layout_formulario.addWidget(QLineEdit(), 1, 1)

        # Layout para os botões
        layout_botoes = QHBoxLayout()
        layout_botoes.addWidget(QPushButton("Salvar"))
        layout_botoes.addWidget(QPushButton("Cancelar"))

        # Adiciona os layouts aninhados ao layout principal
        # Para adicionar um layout a outro, usamos addLayout()
        layout_principal.addLayout(layout_formulario)
        layout_principal.addLayout(layout_botoes)

        container = QWidget()
        container.setLayout(layout_principal)
        self.setCentralWidget(container)
```

Usar layouts é a base para criar interfaces organizadas, redimensionáveis e profissionais. Sempre prefira layouts ao posicionamento manual.
