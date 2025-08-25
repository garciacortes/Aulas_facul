# PyQt6 - Tutorial Completo: 2. Widgets Essenciais

Widgets são os elementos visuais com os quais o usuário interage. PyQt6 oferece uma coleção imensa deles. Vamos cobrir os mais comuns e essenciais para o dia a dia.

Para usar um widget, você precisa:

1.  Importá-lo de `PyQt6.QtWidgets`.
2.  Criar uma instância dele.
3.  Adicioná-lo a um layout ou posicioná-lo manualmente em uma janela. (Veremos layouts no próximo arquivo, por enquanto usaremos `setCentralWidget`).

## 1. `QLabel`: Exibindo Texto e Imagens

Já o usamos para texto. Ele também pode exibir imagens.

```python
# ... (estrutura básica da classe JanelaPrincipal)
from PyQt6.QtGui import QPixmap

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo de QLabel")

        widget = QLabel("Este QLabel suporta <b>HTML</b> básico.")

        # Para usar uma imagem, crie um QPixmap
        # pixmap = QPixmap('caminho/para/sua/imagem.png')
        # widget.setPixmap(pixmap)

        self.setCentralWidget(widget)
```

## 2. `QPushButton`: Botões

O widget mais fundamental para interação. Sua principal característica é o sinal `clicked`, que é emitido quando o botão é pressionado.

```python
# ...
from PyQt6.QtWidgets import QPushButton

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo de QPushButton")

        self.button = QPushButton("Clique em mim!")
        # Conecta o sinal 'clicked' a um método (slot)
        self.button.clicked.connect(self.ao_clicar_no_botao)

        # Adiciona um contador para ver o efeito
        self.contador_cliques = 0

        self.setCentralWidget(self.button)

    # Este é o nosso "slot"
    def ao_clicar_no_botao(self):
        self.contador_cliques += 1
        print(f"Botão clicado {self.contador_cliques} vez(es)!")
        self.button.setText(f"Clicado {self.contador_cliques} vez(es)!")
```

- **Sinal e Slot:** Este é o conceito mais importante do Qt. Um **sinal** é emitido por um widget quando algo acontece (ex: `clicked`). Um **slot** é uma função que é executada em resposta a um sinal. A linha `self.button.clicked.connect(...)` faz essa conexão.

## 3. `QLineEdit`: Entrada de Texto de Linha Única

Usado para obter pequenas entradas de texto do usuário, como nomes, senhas, etc.

```python
# ...
from PyQt6.QtWidgets import QLineEdit

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo de QLineEdit")

        self.input_field = QLineEdit()
        self.input_field.setPlaceholderText("Digite seu nome aqui")

        # Sinais úteis:
        # textChanged é emitido toda vez que o texto muda
        self.input_field.textChanged.connect(self.texto_mudou)
        # returnPressed é emitido quando o usuário aperta Enter
        self.input_field.returnPressed.connect(self.enter_pressionado)

        self.setCentralWidget(self.input_field)

    def texto_mudou(self, texto):
        print(f"Texto atual: {texto}")

    def enter_pressionado(self):
        texto_final = self.input_field.text()
        print(f"Texto final submetido: {texto_final}")
        # Você pode querer limpar o campo após o envio
        # self.input_field.clear()
```

## 4. `QCheckBox`: Caixa de Seleção

Permite ao usuário marcar ou desmarcar uma opção (estado booleano: ligado/desligado).

```python
# ...
from PyQt6.QtWidgets import QCheckBox
from PyQt6.QtCore import Qt

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo de QCheckBox")

        self.checkbox = QCheckBox("Aceito os termos e condições")
        # stateChanged é emitido quando o estado muda
        # Ele envia o novo estado (2 para marcado, 0 para desmarcado)
        self.checkbox.stateChanged.connect(self.estado_mudou)

        self.setCentralWidget(self.checkbox)

    def estado_mudou(self, estado):
        # Qt.CheckState.Checked.value == 2
        # Qt.CheckState.Unchecked.value == 0
        if estado == Qt.CheckState.Checked.value:
            print("Checkbox MARCADO")
        else:
            print("Checkbox DESMARCADO")

        # Para obter o estado a qualquer momento: self.checkbox.isChecked() -> retorna True/False
```

## 5. `QComboBox`: Caixa de Seleção (Dropdown)

Permite ao usuário escolher uma opção de uma lista.

```python
# ...
from PyQt6.QtWidgets import QComboBox

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo de QComboBox")

        self.combo = QComboBox()
        # Adicionando itens
        self.combo.addItems(["Windows", "macOS", "Linux", "Android", "iOS"])

        # Sinais úteis:
        # currentTextChanged é emitido quando a seleção muda
        self.combo.currentTextChanged.connect(self.selecao_mudou)

        self.setCentralWidget(self.combo)

    def selecao_mudou(self, texto_selecionado):
        print(f"Opção selecionada: {texto_selecionado}")

        # Para obter o item selecionado a qualquer momento: self.combo.currentText()
        # Para obter o índice do item: self.combo.currentIndex()
```

Estes são os widgets básicos. Em aplicações reais, você não usará `setCentralWidget` para um único widget. Em vez disso, você os combinará usando **Layouts**, que é o tópico do nosso próximo arquivo.
