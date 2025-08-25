# PyQt6 - Tutorial Completo: 0. Introdução e Instalação

Bem-vindo ao guia prático de PyQt6! Este tutorial foi projetado para levar você dos conceitos mais básicos até a criação de aplicações de desktop funcionais e com aparência profissional.

## O que é PyQt6?

PyQt6 é um _binding_ (uma ponte) para o framework de UI Qt, escrito em C++. Em termos simples, ele permite que você use todo o poder da biblioteca Qt para criar interfaces gráficas (GUIs) usando a linguagem Python, que é muito mais simples e produtiva. É uma das bibliotecas mais populares e robustas para essa finalidade.

## Por que usar PyQt6?

- **Multiplataforma:** Seu código funciona no Windows, macOS e Linux sem alterações significativas.
- **Completo e Maduro:** O Qt é um framework que existe há décadas. Ele tem uma vasta gama de widgets (botões, campos de texto, tabelas, etc.) e funcionalidades prontas para uso.
- **Performance:** Como o PyQt6 é baseado em C++, a performance da interface gráfica é excelente.
- **Aparência Nativa:** As aplicações Qt geralmente se integram bem com a aparência do sistema operacional em que estão rodando.
- **Licenciamento:** PyQt6 é licenciado sob a GPL v3 e uma licença comercial. Para projetos de código aberto, a licença GPL é suficiente.

## Pré-requisitos

A única coisa que você precisa é ter o **Python 3.6+** instalado no seu sistema. Você pode verificar sua versão abrindo o terminal (Prompt de Comando, PowerShell ou Terminal) e digitando:

```bash
python --version
# ou em alguns sistemas
python3 --version
```

## Instalação

A instalação do PyQt6 é feita facilmente através do `pip`, o gerenciador de pacotes do Python.

1.  **Abra seu terminal.**
2.  **Digite o seguinte comando:**

    ```bash
    pip install PyQt6
    ```

3.  **(Opcional mas recomendado) Instale as ferramentas de desenvolvimento:** Elas incluem o Qt Designer (para desenhar interfaces visualmente) e outras utilidades.

    ```bash
    pip install pyqt6-tools
    ```

Para verificar se a instalação foi bem-sucedida, você pode abrir um interpretador Python e tentar importar a biblioteca:

```bash
python
>>> from PyQt6.QtWidgets import QApplication
>>> # Se nenhum erro aparecer, está tudo certo!
>>> exit()
```

## "Olá, Mundo!" - Sua Primeira Aplicação

Vamos criar a aplicação mais simples possível para garantir que tudo está funcionando.

Crie um arquivo chamado `ola_mundo.py` e cole o seguinte código:

```python
# 1. Importações necessárias
import sys
from PyQt6.QtWidgets import QApplication, QWidget, QLabel

# 2. Cria uma instância da aplicação
# sys.argv permite passar argumentos da linha de comando para a aplicação
app = QApplication(sys.argv)

# 3. Cria a janela principal (um QWidget básico neste caso)
janela = QWidget()
janela.setWindowTitle('Minha Primeira Aplicação PyQt6')
janela.setGeometry(200, 200, 300, 150) # Posição X, Posição Y, Largura, Altura

# 4. Cria um widget "filho" dentro da janela
# Um QLabel é usado para exibir texto
hello_msg = QLabel('<h1>Olá, Mundo!</h1>', parent=janela)
hello_msg.move(80, 60) # Posição X, Posição Y dentro da janela

# 5. Mostra a janela
janela.show()

# 6. Inicia o loop de eventos da aplicação
# A aplicação ficará rodando até que a janela seja fechada
sys.exit(app.exec())
```

Para rodar, salve o arquivo e execute no terminal:

```bash
python ola_mundo.py
```

Você deverá ver uma pequena janela com o texto "Olá, Mundo!".

No próximo arquivo, vamos explorar a estrutura básica de uma aplicação de forma mais organizada, usando classes.
