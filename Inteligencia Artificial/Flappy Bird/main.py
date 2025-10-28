import pygame
import threading
from flappy import FlappyBird

# def thread_ia(flappy):
#     while rodando:
#       estado = flappy.get_state()
#       if estado[1] > -1:
#         flappy.verificarPulo()

pygame.init()

tela = pygame.display.set_mode((1066, 668))
clock = pygame.time.Clock()
pygame.display.set_caption("Flappy Bird")

FPS = 100 

flappy = FlappyBird()

rodando = True
# ia_thread = threading.Thread(target=thread_ia, args=(flappy, ))
# ia_thread.start()


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
        
  estados = flappy.get_state()
  for i, entrada in enumerate(estados):
    rede = flappy.passarinho[i].cerebro
    rede.copiar_para_entrada(entrada)
    rede.calcular_saida()
    if rede.copiar_saida()[0] > 0.0:
      flappy.verificarPulo(i)
  
  
  flappy.atualizar()
  
  tela.fill((0, 0, 0))
  
  flappy.desenhar(tela)
  
  pygame.display.update()
  clock.tick(FPS)
  
  
pygame.quit()