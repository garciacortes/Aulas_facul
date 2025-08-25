# PyQt6 - Tutorial Completo: 6. Estilização com QSS (Qt Style Sheets)

A aparência padrão dos widgets do PyQt é funcional, mas muitas vezes queremos customizar o visual da nossa aplicação para que ela tenha uma identidade única ou para melhorar a experiência do usuário (ex: um "modo escuro").

PyQt usa **Qt Style Sheets (QSS)**, uma sintaxe inspirada diretamente no CSS da web. Com QSS, você pode controlar cores, fontes, bordas, preenchimento e muito mais.

## Como Aplicar QSS

Você pode aplicar uma folha de estilo de duas maneiras:

1.  **Globalmente (para toda a aplicação):**
    ```python
    app = QApplication(sys.argv)
    app.setStyleSheet("... seu código QSS aqui ...")
    ```
2.  **Especificamente para um widget e seus filhos:**
    ```python
    meu_botao = QPushButton("Botão Estilizado")
    meu_botao.setStyleSheet("... seu código QSS aqui ...")
    ```
    A aplicação global é a mais comum para temas consistentes.

## Sintaxe Básica do QSS

A sintaxe é `seletor { propriedade: valor; }`.

- **Seletor:** Define a qual(is) widget(s) a regra se aplica.

  - Por tipo de widget: `QPushButton`, `QLineEdit`, `QLabel`.
  - Por nome do objeto: `#nomeDoObjeto` (você define o nome com `widget.setObjectName("nomeDoObjeto")`).
  - Por classe de propriedade: `QPushButton[flat="true"]`.
  - Pseudo-estados: `QPushButton:hover` (quando o mouse está sobre), `QPushButton:pressed` (quando está sendo clicado).

- **Propriedade:** O atributo visual que você quer mudar (`background-color`, `color`, `font-size`, `border`, etc.).

- **Valor:** O valor para a propriedade (`#FF0000`, `14px`, `2px solid black`, etc.).

## Exemplo Prático: Tema Escuro (Dark Mode)

Vamos criar um tema escuro simples e aplicá-lo a uma aplicação. Crie um arquivo `app_com_tema.py`.

```python
import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QWidget,
    QLabel, QPushButton, QLineEdit, QVBoxLayout, QFormLayout
)

# String multi-linha contendo nosso QSS
DARK_THEME_QSS = """
QWidget {
    background-color: #2b2b2b;
    color: #f0f0f0;
    font-size: 14px;
}

QPushButton {
    background-color: #555555;
    border: 1px solid #777777;
    padding: 5px;
    border-radius: 3px;
}

QPushButton:hover {
    background-color: #666666;
}

QPushButton:pressed {
    background-color: #444444;
    border: 1px solid #555555;
}

QLineEdit, QTextEdit {
    background-color: #3c3c3c;
    border: 1px solid #777777;
    padding: 5px;
    border-radius: 3px;
    color: #f0f0f0;
}

QLabel {
    color: #f0f0f0;
}

QMessageBox {
    background-color: #2b2b2b;
}
"""

class JanelaEstilizada(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Aplicação com Tema Escuro")

        layout_principal = QVBoxLayout()
        layout_form = QFormLayout()

        layout_form.addRow("Nome:", QLineEdit())
        layout_form.addRow("Email:", QLineEdit())

        botao_salvar = QPushButton("Salvar")
        botao_cancelar = QPushButton("Cancelar")

        # Atribuindo um nome de objeto para estilização específica
        botao_salvar.setObjectName("botaoSalvar")

        layout_principal.addLayout(layout_form)
        layout_principal.addWidget(botao_salvar)
        layout_principal.addWidget(botao_cancelar)

        container = QWidget()
        container.setLayout(layout_principal)
        self.setCentralWidget(container)

if __name__ == '__main__':
    app = QApplication(sys.argv)

    # Aplicando o tema na aplicação inteira
    app.setStyleSheet(DARK_THEME_QSS)

    # Exemplo de estilização específica por nome de objeto (adicionado ao QSS)
    # app.setStyleSheet(DARK_THEME_QSS + "QPushButton#botaoSalvar { background-color: #006400; }")

    janela = JanelaEstilizada()
    janela.show()
    sys.exit(app.exec())
```

### Análise do QSS

- **`QWidget { ... }`**: Esta é a regra base. Como quase tudo em PyQt herda de `QWidget`, esta regra define o fundo e a cor de texto padrão para toda a aplicação.
- **`QPushButton { ... }`**: Regra específica para todos os `QPushButton`. Ela sobrescreve a regra do `QWidget` para os botões.
- **`QPushButton:hover` e `:pressed`**: Pseudo-estados que mudam a aparência do botão com base na interação do usuário, criando um feedback visual importante.
- **`QLineEdit, QTextEdit { ... }`**: Você pode aplicar a mesma regra a múltiplos tipos de widgets separando-os por vírgula.
- É uma boa prática carregar o QSS de um arquivo externo (`.qss`) para manter o código Python limpo, especialmente para temas complexos.

```python
# Carregando QSS de um arquivo
if __name__ == '__main__':
    app = QApplication(sys.argv)

    try:
        with open('tema.qss', 'r') as f:
            qss = f.read()
            app.setStyleSheet(qss)
    except FileNotFoundError:
        print("Arquivo de tema 'tema.qss' não encontrado.")

    # ... resto do código
```

Dominar QSS é o que diferencia uma aplicação funcional de uma aplicação com visual polido e profissional. Experimente com diferentes propriedades e seletores para ver o que é possível.
