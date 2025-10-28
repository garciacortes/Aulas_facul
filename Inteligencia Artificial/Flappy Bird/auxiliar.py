from redeNeural import RedeNeural

class Auxiliar():
  def __init__(self):
    pass
  
  def AlocarPassaro(self, passaro, QUANTIDADE_INPUT, QUANTIDADE_HIDE, QUANTIDADE_OUTPUT, QUANTIDADE_LAYERS):
    passaro.cerebro = RedeNeural(QUANTIDADE_INPUT,
                               QUANTIDADE_HIDE,
                               QUANTIDADE_OUTPUT,
                               QUANTIDADE_LAYERS)