import pygame

from flappy import FlappyBird

pygame.init()


tela = pygame.display.set_mode((1066, 668))
clock = pygame.time.Clock()
pygame.display.set_caption("Flappy Bird")

FPS = 100 

flappy = FlappyBird()

rodando = True
while rodando:
  for event in pygame.event.get():
    if event.type == pygame.QUIT:
      rodando = False
      
    if event.type == pygame.KEYDOWN:
      if event.key == pygame.K_SPACE:
        flappy.verificarPulo()
    
    if event.type == pygame.KEYDOWN:
      if event.key == pygame.K_RETURN:
        flappy.reiniciarPartida()
        
  
  flappy.atualizar()
  
  tela.fill((0, 0, 0))
  
  flappy.desenhar(tela)
  
  pygame.display.update()
  clock.tick(FPS)
  
  
pygame.quit()