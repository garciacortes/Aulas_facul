import pygame
import secrets, random, time
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
  LARG_TELA = 1066
  ALT_TELA = 668
  DISTANCIA_ENTRE_CANOS = 200
  DISTANCIA_VERTICAL_CANOS = 100
  VALOR_RANDOM = 250
  
  def __init__(self):
    self.partidaEncerrada = False
    
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

    self.passarinho = Passaro(300, 100, 0, 54, 46, 0, "./imagens/flap1.png", "./imagens/flap2.png", "./imagens/flap3.png")
   
    
  def reiniciarPartida(self):
    self.velocidadeJogo = -2
    
    self.__init__()
    
    
  def encerrarPartida(self):
    self.partidaEncerrada = True
    self.velocidadeJogo = 0
    self.passarinho.velocidadeY = 0
    
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
    
  def aplicarColisao(self):
    rect_passaro = self.passarinho.get_rect()
    rect_passaro.inflate_ip(-6, -10) 
    
    for i in range(self.QUANTIDADE_CANOS):
      rect_cano_sup = self.canoSuperior[i].get_rect()
      rect_cano_inf = self.canoInferior[i].get_rect()

      if not self.partidaEncerrada and (rect_passaro.colliderect(rect_cano_sup) or rect_passaro.colliderect(rect_cano_inf) or self.passarinho.posicaoY - self.passarinho.altura <= 0 or self.passarinho.posicaoY >= self.ALT_TELA - 75):
        self.encerrarPartida()
        break
  
  def atualizar(self):
    if(self.passarinho.posicaoY < self.ALT_TELA - 60):
      self.passarinho.atualizar()
      
    
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
      
    self.passarinho.desenhar(tela)
    
    for i in range(self.QUANTIDADE_CANOS):
      self.canoSuperior[i].desenhar(tela, 0) 
      self.canoInferior[i].desenhar(tela, 180)   
      
    for i in range(self.QUANTIDADE_CHAO):
      self.chao[i].desenhar(tela) 
  
  def verificarPulo(self):
    if not self.partidaEncerrada:
      self.passarinho.aplicarPulo()
    
  def verificarColisao(self, x1, y1, largura1, altura1, x2, y2, largura2, altura2):
    if x1 + largura1 < x2:
      return False
    if x1 > x2 + largura2:
      return False
    if y1 + altura1 < y2:
      return False
    if y1 > y2 + altura2:
      return False
    
    return True
