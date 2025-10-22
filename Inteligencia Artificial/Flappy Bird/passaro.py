import pygame

class Passaro(pygame.sprite.Sprite):
  
  sprite = []
  
  def __init__(self, px, py, vy, _largura, _altura, angulo, animacaoFrame1, animacaoFrame2, animacaoFrame3):
    super().__init__()
    
    self._posicaoX = px
    self._posicaoY = py
    self._velocidadeY = vy
    self.angulo = angulo
    self._largura = _largura
    self._altura = _altura
    
    self.contadorAnimacao = 0
    self.indiceAnimacao = 0
    
    self.sprite.append(pygame.image.load(animacaoFrame1).convert_alpha())
    self.sprite.append(pygame.image.load(animacaoFrame2).convert_alpha())
    self.sprite.append(pygame.image.load(animacaoFrame3).convert_alpha())
    self.sprite[0] = pygame.transform.scale(self.sprite[0], (self._largura, self._altura))
    self.sprite[1] = pygame.transform.scale(self.sprite[1], (self._largura, self._altura))
    self.sprite[2] = pygame.transform.scale(self.sprite[2], (self._largura, self._altura))
    
    self.rect = self.sprite[self.indiceAnimacao].get_rect(bottomleft=(self.posicaoX, self.posicaoY))
    self._aceleracaoGravidade = 0.1
    
  def get_rect(self):
    return self.rect
  
  def _atualizarAnimacao(self):
    self.contadorAnimacao += 1
    if self.contadorAnimacao > 10:
      self.contadorAnimacao = 0
      self.indiceAnimacao += 1
      if self.indiceAnimacao >= 3:
        self.indiceAnimacao = 0
        
  def _aplicarGravidade(self):
    self._velocidadeY = self._velocidadeY + self._aceleracaoGravidade
    self._posicaoY += self._velocidadeY
    
  def aplicarPulo(self):
    self.angulo = 20
    self._velocidadeY = -3
    
  def _atualizarAngulo(self):
    if self.angulo >= -90:
      self.angulo -= 0.8
    
  def atualizar(self):
    self._atualizarAnimacao()
    self._atualizarAngulo()
    self._aplicarGravidade()
    
  def desenhar(self, surface):
    sprite_rotate = pygame.transform.rotate(self.sprite[self.indiceAnimacao], self.angulo)
    self.rect = sprite_rotate.get_rect(bottomleft=(self._posicaoX, self._posicaoY))
    surface.blit(sprite_rotate, self.rect)
    
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
  def velocidadeY(self):
    return self._velocidadeY
  
  @velocidadeY.setter
  def velocidadeY(self, value):
    self._velocidadeY = value
  @property
  
  def aceleracaoGravidade(self):
    return self._aceleracaoGravidade
  
  @aceleracaoGravidade.setter
  def aceleracaoGravidade(self, value):
    self._aceleracaoGravidade = value