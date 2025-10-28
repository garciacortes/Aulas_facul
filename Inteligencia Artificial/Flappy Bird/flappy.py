import copy
import pygame
import secrets, random, time
from auxiliar import Auxiliar
from cano import Cano
from enfeite import Enfeite
from passaro import Passaro

class FlappyBird():
  
  velocidadeJogo = -2
  QUANTIDADE_CHAO = 8
  QUANTIDADE_ARVORES = 3
  QUANTIDADE_PREDIOS = 3
  QUANTIDADE_NUVENS = 3
  QUANTIDADE_CANOS = 5
  QUANTIDADE_PASSARINHOS = 100
  
  #Dados Rede Neural
  QUANTIDADE_LAYERS = 1
  QUANTIDADE_INPUT = 4
  QUANTIDADE_HIDE = 6
  QUANTIDADE_OUTPUT = 1
  
  LARG_TELA = 1066
  ALT_TELA = 668
  DISTANCIA_ENTRE_CANOS = 200
  DISTANCIA_VERTICAL_CANOS = 100
  VALOR_RANDOM = 250
  
  def __init__(self):
    self.partidaEncerrada = False
    self.auxiliar = Auxiliar()
    self.geracao = 0 if not hasattr(self, "geracao") else self.geracao
    
    self.fundo = Enfeite(0, 0, self.LARG_TELA, self.ALT_TELA, 0, 0, "./imagens/fundo.png")

    self.chao = []
    for i in range(self.QUANTIDADE_CHAO):
      self.chao.append(Enfeite(209*i, self.ALT_TELA-75, 209, 75, self.velocidadeJogo, 0, "./imagens/chao.png"))
      
    self.arvores = []
    for i in range(self.QUANTIDADE_ARVORES):
      self.arvores.append(Enfeite(959*i, (self.ALT_TELA-52)-75, 959, 52, 0.5*self.velocidadeJogo, 0, "./imagens/arvores.png"))
      
    self.predios = []
    for i in range(self.QUANTIDADE_PREDIOS):
      self.predios.append(Enfeite(960*i, (self.ALT_TELA-54)-105, 960, 54, 0.15*self.velocidadeJogo, 0, "./imagens/predios.png"))
      
    self.nuvens = []
    for i in range(self.QUANTIDADE_NUVENS):
      self.nuvens.append(Enfeite(959*i, (self.ALT_TELA-114)-105, 959, 114, 0.05*self.velocidadeJogo, 0, "./imagens/nuvens.png"))
      
    self.canoSuperior = []   
    self.canoInferior = []
    for i in range(self.QUANTIDADE_CANOS):
      variacao = secrets.randbelow(self.VALOR_RANDOM + 1) - self.VALOR_RANDOM/2
      
      self.canoSuperior.append(Cano(0.8 * self.LARG_TELA + (self.DISTANCIA_ENTRE_CANOS + 86) * i, 
                              self.ALT_TELA / 2 - self.DISTANCIA_VERTICAL_CANOS + variacao, 
                              86, 836, self.velocidadeJogo, "./imagens/cano.png")) 
      self.canoInferior.append(Cano(0.8 * self.LARG_TELA + (self.DISTANCIA_ENTRE_CANOS + 86) * i, 
                              self.ALT_TELA / 2 + 836 + self.DISTANCIA_VERTICAL_CANOS + variacao, 
                              86, 836, self.velocidadeJogo, "./imagens/cano.png"))
    self.passarinho = []
    self.passarinhosMortos = []
    for i in range(self.QUANTIDADE_PASSARINHOS):
      self.passarinho.append(Passaro(300, 300, 0, 54, 46, 0, "./imagens/flap1.png", "./imagens/flap2.png", "./imagens/flap3.png"))
      self.auxiliar.AlocarPassaro(self.passarinho[i], self.QUANTIDADE_INPUT, self.QUANTIDADE_HIDE, self.QUANTIDADE_OUTPUT, self.QUANTIDADE_LAYERS)
  
  def selecionar_melhores(self, passaros, fitness): 
    # Ordena por fitness e retorna top 20% 
    combinados = list(zip(passaros, fitness)) 
    combinados.sort(key=lambda x: x[1], reverse=True) 
    top_20 = int(len(combinados) * 0.2) or 1 
    return [p[0] for p in combinados[:top_20]]
  
  def taxa_mutacao(self, fitness):
    if fitness >= 0:
        return 0.05   # muta pouco
    elif -4 <= fitness < 0:
        return 0.25    # muta mais
    else:  # fitness <= -5
        return 0.65    # muta bastante

  def clonar_rede(self, rede): # Retorna uma cópia da rede 
    return copy.deepcopy(rede)
  
  def mutar_rede(self, rede, fitness):
    taxa = self.taxa_mutacao(fitness)
    for camada in rede.camadas_ocultas + [rede.camada_saida]:
      for neuronio in camada.neuronios:
        for i in range(len(neuronio.pesos)):
          if random.random() < taxa:  # chance de mutação proporcional ao fitness
            neuronio.pesos[i] += random.uniform(-1, 1) * taxa

  def reiniciar_geracao(self):
    fitness = [passaro.fitness for passaro in self.passarinhosMortos]
    
    melhores = self.selecionar_melhores(self.passarinhosMortos, fitness)
    
    self.passarinhosMortos = []
    self.geracao += 1
    self.encerrarPartida()

    # Copia/combina as redes dos melhores para a nova geração
    for i, passaro in enumerate(self.passarinho):
        modelo_base = melhores[i % len(melhores)].cerebro
        passaro.cerebro = self.clonar_rede(modelo_base)
        # Aplica mutação adaptativa usando o fitness do modelo base
        base_fitness = melhores[i % len(melhores)].fitness
        self.mutar_rede(passaro.cerebro, base_fitness)

    print(f"Geracao {self.geracao} iniciada!")
   
  def reiniciarPartida(self):
    self.velocidadeJogo = -2
    
    self.__init__()
    
  def encerrarPartida(self):
    self.partidaEncerrada = True
    self.velocidadeJogo = 0
    
    for i in range(self.QUANTIDADE_CHAO):
      self.chao[i].velocidadeX = self.velocidadeJogo
      
    for i in range(self.QUANTIDADE_ARVORES):
      self.arvores[i].velocidadeX = self.velocidadeJogo
      
    for i in range(self.QUANTIDADE_PREDIOS):
      self.predios[i].velocidadeX = self.velocidadeJogo
      
    for i in range(self.QUANTIDADE_NUVENS):
      self.nuvens[i].velocidadeX = self.velocidadeJogo
      
    for i in range(self.QUANTIDADE_CANOS):
      self.canoSuperior[i].velocidadeX = self.velocidadeJogo 
      self.canoInferior[i].velocidadeX = self.velocidadeJogo
      
    if len(self.passarinho) == 0:
      self.reiniciarPartida()
    
  def aplicarColisao(self):
    for passaroAtual in self.passarinho:
      rect_passaro = passaroAtual.get_rect()
      rect_passaro.inflate_ip(-6, -10) 
      
      for i in range(self.QUANTIDADE_CANOS):
        rect_cano_sup = self.canoSuperior[i].get_rect()
        rect_cano_inf = self.canoInferior[i].get_rect()

        if not self.partidaEncerrada and (rect_passaro.colliderect(rect_cano_sup) or rect_passaro.colliderect(rect_cano_inf) or passaroAtual.posicaoY - passaroAtual.altura <= 0 or passaroAtual.posicaoY >= self.ALT_TELA - 75) and passaroAtual in self.passarinho:
          self.passarinho.remove(passaroAtual)
          self.passarinhosMortos.append(passaroAtual)
          
        if len(self.passarinho) == 0:
          self.reiniciar_geracao()
          break
        
  def atualizar(self):
    for passaroAtual in self.passarinho:
      if(passaroAtual.posicaoY < self.ALT_TELA - 60):
        passaroAtual.atualizar()
      
    
    for i in range(self.QUANTIDADE_CANOS):
      self.canoSuperior[i].movimentar()
      self.canoInferior[i].movimentar() 
      
      variacao = secrets.randbelow(self.VALOR_RANDOM+1) - self.VALOR_RANDOM/2
      
      self.canoSuperior[i].verificarReset((self.QUANTIDADE_CANOS*(86+self.DISTANCIA_ENTRE_CANOS)),
                                          -self.DISTANCIA_VERTICAL_CANOS+variacao, self.ALT_TELA)
      
      self.canoInferior[i].verificarReset((self.QUANTIDADE_CANOS*(86+self.DISTANCIA_ENTRE_CANOS)), 836+self.DISTANCIA_VERTICAL_CANOS+variacao, self.ALT_TELA) 
      
      self.aplicarColisao()
      
    for i in range(self.QUANTIDADE_CHAO):
      self.chao[i].movimentar()
      self.chao[i].verificarReset(self.QUANTIDADE_CHAO * 209)
      
    for i in range(self.QUANTIDADE_ARVORES):
      self.arvores[i].movimentar()
      self.arvores[i].verificarReset(self.QUANTIDADE_ARVORES * 959)
      
    for i in range(self.QUANTIDADE_PREDIOS):
      self.predios[i].movimentar()
      self.predios[i].verificarReset(self.QUANTIDADE_PREDIOS * 960)
      
    for i in range(self.QUANTIDADE_ARVORES):
      self.nuvens[i].movimentar()
      self.nuvens[i].verificarReset(self.QUANTIDADE_NUVENS * 959)
    
  def desenhar(self, tela):
    self.fundo.desenhar(tela)
    
    for i in range(self.QUANTIDADE_NUVENS):
      self.nuvens[i].desenhar(tela)
      
    for i in range(self.QUANTIDADE_PREDIOS):
      self.predios[i].desenhar(tela)
      
    for i in range(self.QUANTIDADE_ARVORES):
      self.arvores[i].desenhar(tela)
    
    for passaroAtual in self.passarinho:
      passaroAtual.desenhar(tela)
    
    for i in range(self.QUANTIDADE_CANOS):
      self.canoSuperior[i].desenhar(tela, 0) 
      self.canoInferior[i].desenhar(tela, 180)   
      
    for i in range(self.QUANTIDADE_CHAO):
      self.chao[i].desenhar(tela) 
  
  def verificarPulo(self, i):
    if not self.partidaEncerrada:
      self.passarinho[i].aplicarPulo()
  
  def get_state(self):
    states = []
    for passaroAtual in self.passarinho:
      cano_next = None
      menor_dist = float("inf")

      # Encontra o próximo cano à frente do pássaro
      for cano in self.canoSuperior:
        if cano.posicaoX + cano.largura > passaroAtual.posicaoX:
          dist = cano.posicaoX - passaroAtual.posicaoX
          if dist < menor_dist:
            menor_dist = dist
            cano_next = cano

      if cano_next:
        idx = self.canoSuperior.index(cano_next)
        y_top = self.canoSuperior[idx].posicaoY + self.canoSuperior[idx].altura
        y_bottom = self.canoInferior[idx].posicaoY
        abertura = y_bottom - y_top
        abertura_centro = (y_top + y_bottom) / 2
        dist_vertical = passaroAtual.posicaoY - abertura_centro
        velocidade_cano = self.canoSuperior[idx].velocidadeX  # supondo que tenha velocidade horizontal
      else:
        dist_vertical = 0
        menor_dist = 0
        abertura = 0
        velocidade_cano = 0

      if passaroAtual.posicaoX > self.canoSuperior[idx].posicaoX + self.canoSuperior[idx].largura:
        passaroAtual.fitness = 1
        passaroAtual.canosConcluidos = 1
      elif passaroAtual.canosConcluidos == 0:
        passaroAtual.fitness = -5
      else:
        passaroAtual.fitness = -1
        
        
            
      # Normaliza os valores para a rede neural
      states.append([
        menor_dist / self.LARG_TELA,       # distância horizontal normalizada
        dist_vertical / self.ALT_TELA,     # distância vertical normalizada
        abertura / self.ALT_TELA,          # abertura do cano normalizada
        velocidade_cano / 10                # velocidade do cano normalizada
      ])

      

  
    return states
  def calcular_recompensa(self):
    if self.partidaEncerrada:
      return -100
    else:
      return 1