import pygame
from enfeite import Enfeite

QUANTIDADE_CHAO = 8
QUANTIDADE_ARVORES = 3
QUANTIDADE_PREDIOS = 3
QUANTIDADE_NUVENS = 3
LARG_TELA = 866
ALT_TELA = 668

pygame.init()
tela = pygame.display.set_mode((LARG_TELA, ALT_TELA))
clock = pygame.time.Clock()
pygame.display.set_caption("Flappy Bird")

FPS = 100
velocidadeJogo = -1

fundo = Enfeite(0, 0, LARG_TELA, ALT_TELA, 0, 0, "./imagens/fundo.png")

chao = []
for i in range(QUANTIDADE_CHAO):
  chao.append(Enfeite(209*i, ALT_TELA-75, 209, 75, velocidadeJogo, 0, "./imagens/chao.png"))
  
arvores = []
for i in range(QUANTIDADE_ARVORES):
  arvores.append(Enfeite(959*i, (ALT_TELA-52)-75, 959, 52, 0.5*velocidadeJogo, 0, "./imagens/arvores.png"))
  
predios = []
for i in range(QUANTIDADE_PREDIOS):
  predios.append(Enfeite(960*i, (ALT_TELA-54)-105, 960, 54, 0.15*velocidadeJogo, 0, "./imagens/predios.png"))
  
nuvens = []
for i in range(QUANTIDADE_NUVENS):
  nuvens.append(Enfeite(959*i, (ALT_TELA-114)-105, 959, 114, 0.05*velocidadeJogo, 0, "./imagens/nuvens.png"))
  

rodando = True
while rodando:
  for event in pygame.event.get():
    if event.type == pygame.QUIT:
      rodando = False
      
  for i in range(QUANTIDADE_CHAO):
    chao[i].movimentar()
    chao[i].verificarReset(QUANTIDADE_CHAO * 209)
    
  for i in range(QUANTIDADE_ARVORES):
    arvores[i].movimentar()
    arvores[i].verificarReset(QUANTIDADE_ARVORES * 959)
    
  for i in range(QUANTIDADE_PREDIOS):
    predios[i].movimentar()
    predios[i].verificarReset(QUANTIDADE_PREDIOS * 960)
    
  for i in range(QUANTIDADE_ARVORES):
    nuvens[i].movimentar()
    nuvens[i].verificarReset(QUANTIDADE_NUVENS * 959)
  
  tela.fill((0, 0, 0))
  
  fundo.desenhar(tela)
  
  for i in range(QUANTIDADE_NUVENS):
    nuvens[i].desenhar(tela)
    
  for i in range(QUANTIDADE_PREDIOS):
    predios[i].desenhar(tela)
    
  for i in range(QUANTIDADE_ARVORES):
    arvores[i].desenhar(tela)
    
  for i in range(QUANTIDADE_CHAO):
    chao[i].desenhar(tela)
  
  
  pygame.display.update()
  clock.tick(FPS)
  
  
pygame.quit()