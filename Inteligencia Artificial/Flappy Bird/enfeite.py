import pygame

class Enfeite(pygame.sprite.Sprite):
  
  def __init__(self, px, py, largura, altura, vx, vy, caminhoSprite):
    super().__init__()
    
    self.sprite = pygame.image.load(caminhoSprite).convert_alpha()
    
    self.posicaoX = px
    self.posicaoY = py
    self.velocidadeX = vx
    self.velocidadeY = vy
    self.largura = largura
    self.altura = altura
    
    self.sprite = pygame.transform.scale(self.sprite, (self.largura, self.altura))
  
  def movimentar(self):
    self.posicaoX += self.velocidadeX
  
  def verificarReset(self, valor):
    if (self.posicaoX + self.largura) <= 0:
      self.posicaoX += valor
  
  def desenhar(self, surface):
    surface.blit(self.sprite, (int(self.posicaoX), int(self.posicaoY)))