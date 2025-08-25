# PyQt6 - Tutorial Completo: 4. Sinais, Slots e Diálogos

Já vimos o básico de Sinais e Slots com `QPushButton`. Este mecanismo é a espinha dorsal da comunicação entre objetos no Qt e é fundamental para criar aplicações interativas.

## Aprofundando em Sinais e Slots

- **Sinal (Signal):** Uma notificação que um objeto emite quando seu estado muda. Por exemplo, `QLineEdit` emite o sinal `textChanged(str)` sempre que o texto é alterado. Note que ele pode carregar dados (neste caso, o novo texto como uma string).
- **Slot:** Uma função (geralmente um método de uma classe) que é chamada em resposta a um sinal. O slot pode ou não aceitar os dados enviados pelo sinal.

### Exemplo Prático: Calculadora de IMC

Vamos construir uma pequena aplicação que calcula o Índice de Massa Corporal (IMC). Ela usará vários widgets e a comunicação entre eles será feita via sinais e slots.

```python
import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QLabel, QLineEdit, QPushButton,
    QVBoxLayout, QFormLayout, QMessageBox
)
from PyQt6.QtCore import Qt

class CalculadoraIMC(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Calculadora de IMC")

        # Layout principal
        layout_principal = QVBoxLayout()
        layout_principal.setAlignment(Qt.AlignmentFlag.AlignTop)

        # Layout de formulário para os campos de entrada
        layout_form = QFormLayout()

        self.input_peso = QLineEdit()
        self.input_peso.setPlaceholderText("Ex: 70.5")
        layout_form.addRow("Peso (kg):", self.input_peso)

        self.input_altura = QLineEdit()
        self.input_altura.setPlaceholderText("Ex: 1.75")
        layout_form.addRow("Altura (m):", self.input_altura)

        # Botão para calcular
        self.botao_calcular = QPushButton("Calcular IMC")
        self.botao_calcular.clicked.connect(self.calcular_imc) # Conexão SINAL -> SLOT

        # Label para mostrar o resultado
        self.label_resultado = QLabel("Por favor, insira seus dados.")
        self.label_resultado.setAlignment(Qt.AlignmentFlag.AlignCenter)

        # Adiciona os layouts e widgets ao layout principal
        layout_principal.addLayout(layout_form)
        layout_principal.addWidget(self.botao_calcular)
        layout_principal.addWidget(self.label_resultado)

        container = QWidget()
        container.setLayout(layout_principal)
        self.setCentralWidget(container)

    # Este é o nosso SLOT
    def calcular_imc(self):
        try:
            peso_str = self.input_peso.text().replace(',', '.')
            altura_str = self.input_altura.text().replace(',', '.')

            peso = float(peso_str)
            altura = float(altura_str)

            if peso <= 0 or altura <= 0:
                raise ValueError("Peso e altura devem ser positivos.")

            imc = peso / (altura * altura)

            resultado_texto = f"Seu IMC é: {imc:.2f}"
            self.label_resultado.setText(resultado_texto)

        except ValueError as e:
            # Exibe um diálogo de erro se a entrada for inválida
            self.exibir_dialogo_erro(f"Dados inválidos: {e}")
        except Exception as e:
            self.exibir_dialogo_erro(f"Ocorreu um erro inesperado: {e}")

    # Método para mostrar um diálogo de mensagem
    def exibir_dialogo_erro(self, mensagem):
        dialogo = QMessageBox(self)
        dialogo.setWindowTitle("Erro")
        dialogo.setText(mensagem)
        dialogo.setIcon(QMessageBox.Icon.Warning)
        dialogo.exec() # Mostra o diálogo e bloqueia a janela principal


if __name__ == '__main__':
    app = QApplication(sys.argv)
    janela = CalculadoraIMC()
    janela.show()
    sys.exit(app.exec())
```

## Diálogos (`QDialog`, `QMessageBox`)

Um diálogo é uma janela secundária que aparece para solicitar informações do usuário ou fornecer notificações.

### `QMessageBox`: Diálogos Padrão

O `QMessageBox` é uma classe pronta para criar diálogos comuns de forma muito fácil, como vimos no exemplo acima.

- **Tipos de Ícone:** `.Information`, `.Warning`, `.Critical`, `.Question`.
- **Botões Padrão:** Você pode adicionar botões como OK, Cancelar, Sim, Não.

Exemplo de um diálogo de confirmação:

```python
def confirmar_saida(self):
    dialogo = QMessageBox(self)
    dialogo.setWindowTitle("Confirmar Saída")
    dialogo.setText("Você tem certeza que deseja sair?")
    dialogo.setIcon(QMessageBox.Icon.Question)
    # Adiciona os botões padrão de Sim e Não
    dialogo.setStandardButtons(QMessageBox.StandardButton.Yes | QMessageBox.StandardButton.No)
    # Define o botão padrão (pressionado se o usuário teclar Enter)
    dialogo.setDefaultButton(QMessageBox.StandardButton.No)

    # .exec() retorna o botão que foi clicado
    resposta = dialogo.exec()

    if resposta == QMessageBox.StandardButton.Yes:
        print("Saindo...")
        QApplication.instance().quit() # Fecha a aplicação
    else:
        print("Cancelou a saída.")
```

### Outros Diálogos Comuns

PyQt vem com vários outros diálogos prontos para uso:

- **`QFileDialog`**: Para o usuário selecionar arquivos ou diretórios.

  ```python
  # Abrir um arquivo
  caminho_arquivo, _ = QFileDialog.getOpenFileName(self, "Abrir Arquivo", "", "Arquivos de Texto (*.txt);;Todos os Arquivos (*)")
  if caminho_arquivo:
      print("Arquivo selecionado:", caminho_arquivo)

  # Salvar um arquivo
  caminho_arquivo, _ = QFileDialog.getSaveFileName(self, "Salvar Arquivo", "", "Arquivos de Texto (*.txt)")
  ```

- **`QInputDialog`**: Para obter uma entrada simples do usuário (texto, número, etc.).
- **`QFontDialog`**: Para selecionar uma fonte.
- **`QColorDialog`**: Para selecionar uma cor.

Dominar sinais, slots e diálogos permite que você crie aplicações que respondem de forma inteligente e eficaz às ações do usuário.
