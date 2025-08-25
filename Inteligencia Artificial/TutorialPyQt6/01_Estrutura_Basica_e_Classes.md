# PyQt6 - Tutorial Completo: 1. Estrutura Básica e Classes

No exemplo "Olá, Mundo!", escrevemos o código de forma linear. Para aplicações reais, a melhor abordagem é usar Programação Orientada a Objetos (POO) para organizar e reutilizar o código.

## Conceitos Fundamentais

Toda aplicação PyQt6 possui estes três elementos centrais:

1.  **`QApplication`**: É o coração da aplicação. Gerencia o fluxo de controle principal e as configurações. **Só pode haver uma instância de `QApplication` por aplicação.**
2.  **O Loop de Eventos (`app.exec()`):** Depois que a interface é criada e exibida, a aplicação entra em um loop infinito. Ela fica "escutando" por eventos como cliques de mouse, teclas pressionadas, etc. Quando um evento ocorre, o PyQt o direciona para o widget apropriado para ser tratado. O loop termina quando a aplicação é fechada (por exemplo, ao clicar no "X" da janela).
3.  **Widgets (`QWidget`, `QMainWindow`, etc.):** São os blocos de construção da sua interface: janelas, botões, caixas de texto. Um widget pode conter outros widgets, criando uma hierarquia.

## Estrutura com Classes (A Forma Correta)

Vamos refazer nosso primeiro exemplo, mas desta vez usando uma classe para representar nossa janela principal. Esta é a maneira padrão e mais organizada de construir aplicações em PyQt.

Crie um arquivo chamado `app_com_classe.py`:

```python
import sys
from PyQt6.QtWidgets import QApplication, QMainWindow, QLabel

# A nossa classe de janela principal herda de QMainWindow
# QMainWindow já vem com uma estrutura pronta para menus, barras de ferramentas, etc.
class JanelaPrincipal(QMainWindow):
    def __init__(self):
        # A primeira coisa a fazer é chamar o construtor da classe pai (QMainWindow)
        super().__init__()

        # Define o título da janela
        self.setWindowTitle('Aplicação com Classe')

        # Define um tamanho fixo para a janela
        self.setFixedSize(400, 200)

        # Cria um widget QLabel para exibir o texto
        self.label = QLabel('Conteúdo da minha janela!')

        # Define o alinhamento do texto no QLabel (opcional)
        # from PyQt6.QtCore import Qt
        # self.label.setAlignment(Qt.AlignmentFlag.AlignCenter)

        # Define o QLabel como o widget central da nossa QMainWindow
        # Apenas um widget pode ser o widget central
        self.setCentralWidget(self.label)

# O código abaixo só será executado se este script for o principal
if __name__ == '__main__':
    # Cria a instância da aplicação
    app = QApplication(sys.argv)

    # Cria uma instância da nossa classe de janela
    janela = JanelaPrincipal()

    # Mostra a janela
    janela.show()

    # Inicia o loop de eventos
    sys.exit(app.exec())
```

### Análise da Estrutura

1.  **`class JanelaPrincipal(QMainWindow):`**

    - Criamos uma classe chamada `JanelaPrincipal`.
    - Ela **herda** de `QMainWindow`. Isso significa que nossa classe automaticamente ganha todas as funcionalidades de uma janela principal padrão do Qt. Usar `QMainWindow` é melhor do que `QWidget` para a janela principal, pois ela já tem suporte embutido para menus, barras de ferramentas e uma barra de status.

2.  **`super().__init__()`**

    - É **crucial** chamar o construtor da classe pai (`QMainWindow`) dentro do `__init__` da nossa classe. Isso garante que a janela seja inicializada corretamente.

3.  **`self.setWindowTitle(...)` e `self.setFixedSize(...)`**

    - Estamos chamando métodos que nossa classe herdou de `QMainWindow` para configurar a janela. O prefixo `self` se refere à instância atual da classe.

4.  **`self.setCentralWidget(self.label)`**

    - Uma `QMainWindow` é projetada para ter um layout complexo (menus, docas, etc.) e uma área central principal. Este método define qual widget ocupará essa área central.

5.  **`if __name__ == '__main__':`**
    - Este é um padrão comum em Python. O código dentro deste bloco só roda quando o arquivo é executado diretamente (`python app_com_classe.py`). Se este arquivo for importado por outro script, o código não será executado. Isso é útil para garantir que a aplicação só seja iniciada quando for a intenção.

A partir de agora, usaremos esta estrutura baseada em classes para todos os nossos exemplos.
