# PyQt6 - Tutorial Completo: 7. Tópicos Avançados

Esta seção aborda conceitos que são cruciais para o desenvolvimento de aplicações robustas e complexas no dia a dia.

## 1. Threads: Mantendo a Interface Responsiva

**O Problema:** A interface gráfica (GUI) roda em um único _thread_ (o "thread principal"). Se você executar uma tarefa demorada (ex: baixar um arquivo grande, processar dados pesados) diretamente em uma função conectada a um botão, a GUI inteira irá **congelar** até que a tarefa termine. O usuário não conseguirá clicar em nada e o sistema operacional poderá marcar sua aplicação como "Não respondendo".

**A Solução:** Mover tarefas demoradas para um _thread_ secundário. O Qt fornece uma maneira segura e organizada de fazer isso usando `QThread` e o mecanismo de sinais e slots.

**A Abordagem Correta (Worker-Controller):**

1.  **Crie uma classe `Worker` que herda de `QObject`**. Esta classe conterá a lógica da sua tarefa demorada.
2.  **Defina sinais na classe `Worker`** para comunicar o progresso (`progress(int)`) e a finalização (`finished()`) da tarefa.
3.  **Na sua classe de janela principal**, crie uma instância de `QThread` e uma instância do seu `Worker`.
4.  **Mova o `Worker` para o `QThread`** usando `worker.moveToThread(thread)`.
5.  **Conecte os sinais e slots**:
    - Conecte o sinal `started` do thread ao método que executa a tarefa no worker.
    - Conecte os sinais de `progress` e `finished` do worker aos slots na sua janela principal para atualizar a UI (ex: uma barra de progresso).
    - Conecte o sinal `finished` do worker ao slot `quit` do thread para que ele possa ser encerrado corretamente.
6.  **Inicie o thread** com `thread.start()`.

### Exemplo de `QThread`

```python
import sys
import time
from PyQt6.QtWidgets import (QApplication, QMainWindow, QPushButton, QVBoxLayout, QWidget, QProgressBar)
from PyQt6.QtCore import QObject, QThread, pyqtSignal

# 1. Classe Worker que fará o trabalho pesado
class Worker(QObject):
    finished = pyqtSignal()
    progress = pyqtSignal(int)

    def run(self):
        """Tarefa demorada"""
        for i in range(1, 101):
            time.sleep(0.05) # Simula trabalho
            self.progress.emit(i)
        self.finished.emit()

# Janela Principal
class JanelaComThread(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo de QThread")

        self.button = QPushButton("Iniciar Tarefa Pesada")
        self.button.clicked.connect(self.iniciar_tarefa)

        self.progress_bar = QProgressBar()
        self.progress_bar.setValue(0)

        layout = QVBoxLayout()
        layout.addWidget(self.button)
        layout.addWidget(self.progress_bar)

        container = QWidget()
        container.setLayout(layout)
        self.setCentralWidget(container)

    def iniciar_tarefa(self):
        self.button.setEnabled(False) # Desabilita o botão durante a execução

        # 2. Cria o Thread e o Worker
        self.thread = QThread()
        self.worker = Worker()

        # 3. Move o worker para o thread
        self.worker.moveToThread(self.thread)

        # 4. Conecta os sinais e slots
        self.thread.started.connect(self.worker.run)
        self.worker.finished.connect(self.thread.quit)
        self.worker.finished.connect(self.worker.deleteLater)
        self.thread.finished.connect(self.thread.deleteLater)
        self.worker.progress.connect(self.progress_bar.setValue)

        # Limpeza e reativação do botão quando tudo terminar
        self.thread.finished.connect(lambda: self.button.setEnabled(True))
        self.thread.finished.connect(lambda: self.progress_bar.setValue(100))

        # 5. Inicia o thread
        self.thread.start()

if __name__ == '__main__':
    app = QApplication(sys.argv)
    janela = JanelaComThread()
    janela.show()
    sys.exit(app.exec())
```

## 2. Model/View/Controller (MVC) para Dados

Para exibir grandes volumes de dados (em listas, tabelas ou árvores), é ineficiente criar milhares de widgets. O Qt usa uma arquitetura Model-View muito poderosa.

- **Model:** Contém os dados e a lógica de negócio. Ele não sabe nada sobre como os dados são exibidos.
- **View:** Exibe os dados do modelo. `QListView`, `QTableView`, `QTreeView` são exemplos de Views. Ela pede os dados ao modelo quando precisa desenhá-los.
- **Delegate (Opcional):** Controla como os dados são desenhados e editados na View.

Esta arquitetura desacopla os dados da sua apresentação, tornando o código mais limpo e a aplicação muito mais escalável e performática. A implementação detalhada de modelos customizados (`QAbstractTableModel`) é um tópico extenso, mas essencial para aplicações que manipulam grandes conjuntos de dados.

## 3. Empacotando a Aplicação para Distribuição

Depois de desenvolver sua aplicação, você vai querer distribuí-la como um executável (`.exe` no Windows, `.app` no macOS) para que os usuários não precisem instalar Python ou PyQt.

A ferramenta mais popular para isso é o **PyInstaller**.

1.  **Instale o PyInstaller:**
    ```bash
    pip install pyinstaller
    ```
2.  **Navegue até o diretório do seu script principal** (ex: `main.py`) no terminal.

3.  **Execute o PyInstaller:**

    - **Para uma aplicação simples:**

      ```bash
      pyinstaller main.py
      ```

    - **Comandos úteis e recomendados:**
      ```bash
      pyinstaller --name "MeuApp" --windowed --onefile --icon="app.ico" main.py
      ```
      - `--name "MeuApp"`: Define o nome do executável.
      - `--windowed` ou `-w`: Impede que um console/terminal preto apareça ao executar a aplicação GUI. **Essencial para aplicações PyQt.**
      - `--onefile` ou `-F`: Cria um único arquivo executável, em vez de uma pasta com vários arquivos. É mais conveniente para o usuário.
      - `--icon="app.ico"`: Define um ícone para o seu executável.

Após a execução, o PyInstaller criará uma pasta `dist`. Dentro dela, você encontrará seu executável pronto para ser distribuído.

---

Este tutorial cobriu desde a instalação e conceitos básicos até a criação de interfaces complexas, estilizadas, responsivas e prontas para distribuição. O próximo passo é praticar, construir seus próprios projetos e consultar a excelente documentação oficial do PyQt para explorar os inúmeros outros widgets e funcionalidades disponíveis.
