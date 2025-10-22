import pygame

class Cano(pygame.sprite.Sprite):
  
  def __init__(self, px, py, _largura, _altura, vx, caminhoSprite):
    super().__init__()
    
    self.sprite = pygame.image.load(caminhoSprite).convert_alpha()
    
    self._posicaoX = px
    self._posicaoY = py
    self._velocidadeX = vx
    self._largura = _largura
    self._altura = _altura
    
    self.rect = self.sprite.get_rect(bottomleft=(self.posicaoX, self.posicaoY))
    self.sprite = pygame.transform.scale(self.sprite, (self._largura, self._altura))

  
  def get_rect(self):
    return self.rect
  
  def movimentar(self):
    self._posicaoX += self._velocidadeX
  
  def verificarReset(self, valor, valorAbertura, ALT_TELA):
    if (self._posicaoX + self._largura) <= 0:
      self._posicaoX += valor
      self._posicaoY = ALT_TELA/2 + valorAbertura
  
  def desenhar(self, surface, angulo):
    sprite_rotate = pygame.transform.rotate(self.sprite, angulo)
    self.rect = sprite_rotate.get_rect(bottomleft=(int(self._posicaoX), int(self._posicaoY)))
    if angulo == 0:
      surface.blit(sprite_rotate, self.rect)
    else:
      surface.blit(sprite_rotate, self.rect.topleft)
      
  @property
  def posicaoX(self):
    return self._posicaoX
  
  @property
  def posicaoY(self):
    return self._posicaoY
  
  @property
  def largura(self):
    return self._largura
  
  @property
  def altura(self):
    return self._altura
  
  @property  
  def velocidadeX(self):
    return self._velocidadeX
  
  @velocidadeX.setter
  def velocidadeX(self, value):
    self._velocidadeX = value