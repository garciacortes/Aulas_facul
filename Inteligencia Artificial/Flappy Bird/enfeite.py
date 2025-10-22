import pygame

class Enfeite(pygame.sprite.Sprite):
  
  def __init__(self, px, py, largura, altura, vx, vy, caminhoSprite):
    super().__init__()
    
    self.sprite = pygame.image.load(caminhoSprite).convert_alpha()
    
    self.posicaoX = px
    self.posicaoY = py
    self._velocidadeX = vx
    self.velocidadeY = vy
    self.largura = largura
    self.altura = altura
    
    self.sprite = pygame.transform.scale(self.sprite, (self.largura, self.altura))
  
  def get_rect(self, ):
    return self.sprite.get_rect(bottomleft=(self.posicaoX, self.posicaoY))
  
  def movimentar(self):
    self.posicaoX += self._velocidadeX
  
  def verificarReset(self, valor):
    if (self.posicaoX + self.largura) <= 0:
      self.posicaoX += valor
  
  def desenhar(self, surface):
    surface.blit(self.sprite, (int(self.posicaoX), int(self.posicaoY)))
  
  @property  
  def velocidadeX(self):
    return self._velocidadeX
  
  @velocidadeX.setter
  def velocidadeX(self, value):
    self._velocidadeX = value