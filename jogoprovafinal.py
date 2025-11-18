import pygame
import random
import math
import copy
import numpy as np
from collections import deque

# ---------- Configurações ----------
SCREEN_W = 800
SCREEN_H = 400
GROUND_Y = SCREEN_H - 60
FPS = 60

POPULATION = 40
ELITE_KEEP = 6
MUTATION_RATE = 0.1
MUTATION_SCALE = 0.5
CROSSOVER_RATE = 0.5

OBSTACLE_SPAWN_INTERVAL = 90  # frames
OBSTACLE_SPEED = 5

INPUT_SIZE = 4   # dist to next obstacle, obstacle width, obstacle speed, player vy
HIDDEN_SIZE = 8
OUTPUT_SIZE = 1  # jump probability / action

# ---------- Rede Neural (simples) ----------
def sigmoid(x):
    return 1.0 / (1.0 + np.exp(-x))

def dsigmoid(y):
    return y * (1.0 - y)

def tanh(x):
    return np.tanh(x)

def dtanh(y):
    return 1.0 - y*y

class SimpleNN:
    def __init__(self, input_size=INPUT_SIZE, hidden_size=HIDDEN_SIZE, output_size=OUTPUT_SIZE):
        # Pesos e biases
        self.w1 = np.random.randn(hidden_size, input_size) * 0.5
        self.b1 = np.zeros((hidden_size, 1))
        self.w2 = np.random.randn(output_size, hidden_size) * 0.5
        self.b2 = np.zeros((output_size, 1))

    def forward(self, x):
        # x: column vector (input_size,1)
        self.z1 = self.w1.dot(x) + self.b1
        self.a1 = tanh(self.z1)
        self.z2 = self.w2.dot(self.a1) + self.b2
        self.a2 = sigmoid(self.z2)  # saída entre 0 e 1
        return self.a2

    def copy(self):
        nn = SimpleNN()
        nn.w1 = self.w1.copy()
        nn.b1 = self.b1.copy()
        nn.w2 = self.w2.copy()
        nn.b2 = self.b2.copy()
        return nn

    def get_weights_flat(self):
        return np.concatenate([self.w1.flatten(), self.b1.flatten(), self.w2.flatten(), self.b2.flatten()])

    def set_weights_flat(self, flat):
        s1 = self.w1.size
        s2 = self.b1.size
        s3 = self.w2.size
        s4 = self.b2.size
        idx = 0
        self.w1 = flat[idx:idx+s1].reshape(self.w1.shape); idx += s1
        self.b1 = flat[idx:idx+s2].reshape(self.b1.shape); idx += s2
        self.w2 = flat[idx:idx+s3].reshape(self.w2.shape); idx += s3
        self.b2 = flat[idx:idx+s4].reshape(self.b2.shape)

    # Backprop com MSE e SGD (aplica atualização nos pesos)
    def train_online(self, x, target, lr=0.05):
        """
        x: column vector (input_size,1)
        target: scalar between 0 e 1 (column vector shape (1,1))
        """
        # forward (já computado por forward tipicamente, mas recalculamos para segurança)
        z1 = self.w1.dot(x) + self.b1
        a1 = tanh(z1)
        z2 = self.w2.dot(a1) + self.b2
        a2 = sigmoid(z2)

        # erro
        error = a2 - target  # (1,1)
        loss = 0.5 * (error**2)

        # grad saída
        delta2 = error * dsigmoid(a2)  # (1,1)
        # grad hidden
        delta1 = (self.w2.T.dot(delta2)) * dtanh(a1)  # (hidden,1)

        # grads
        dw2 = delta2.dot(a1.T)
        db2 = delta2
        dw1 = delta1.dot(x.T)
        db1 = delta1

        # update
        self.w2 -= lr * dw2
        self.b2 -= lr * db2
        self.w1 -= lr * dw1
        self.b1 -= lr * db1

        return float(loss)

# ---------- Genética ----------
def crossover(nn1: SimpleNN, nn2: SimpleNN):
    # crossover por mistura em vetor de pesos
    f1 = nn1.get_weights_flat()
    f2 = nn2.get_weights_flat()
    assert f1.shape == f2.shape
    mask = np.random.rand(*f1.shape) < 0.5
    child = SimpleNN()
    child_flat = f1.copy()
    child_flat[~mask] = f2[~mask]
    child.set_weights_flat(child_flat)
    return child

def mutate(nn: SimpleNN, rate=MUTATION_RATE, scale=MUTATION_SCALE):
    flat = nn.get_weights_flat()
    for i in range(flat.size):
        if random.random() < rate:
            flat[i] += np.random.randn() * scale
    nn.set_weights_flat(flat)

# ---------- Entidades do Jogo ----------
class Obstacle:
    def __init__(self, x):
        self.width = random.randint(20, 40)
        self.height = random.randint(30, 60)
        self.x = x
        self.y = GROUND_Y - self.height
        self.vx = -OBSTACLE_SPEED

    def update(self):
        self.x += self.vx

    def rect(self):
        return pygame.Rect(int(self.x), int(self.y), self.width, self.height)

class Agent:
    def __init__(self, nn=None):
        self.x = 80
        self.y = GROUND_Y - 40
        self.vy = 0.0
        self.on_ground = True
        self.width = 30
        self.height = 40
        self.alive = True
        self.score = 0.0
        self.fitness = 0.0
        self.nn = nn.copy() if nn else SimpleNN()
        self.age = 0
        self.color = (0, 150, 200, 50)

    def rect(self):
        return pygame.Rect(int(self.x), int(self.y), self.width, self.height)

    def jump(self):
        if self.on_ground:
            self.vy = -11
            self.on_ground = False

    def apply_physics(self):
        self.vy += 0.6  # gravity
        self.y += self.vy
        if self.y >= GROUND_Y - self.height:
            self.y = GROUND_Y - self.height
            self.vy = 0
            self.on_ground = True

    def decide_and_act(self, obstacles, do_backprop=True):
        """
        Inputs: normalized -> distance to next obstacle (0..1), obstacle width, obstacle speed, vy
        Output: a2 in [0,1] -> jump if > 0.5
        """
        # encontrar próximo obstáculo à frente
        next_obs = None
        min_dist = 9999
        for ob in obstacles:
            dist = ob.x + ob.width - (self.x + self.width)
            if dist >= -10 and dist < min_dist:
                min_dist = dist
                next_obs = ob

        # normalize inputs
        if next_obs is None:
            dist_norm = 1.0
            width_norm = 0.0
        else:
            dist_norm = max(0.0, min(1.0, min_dist / SCREEN_W))
            width_norm = next_obs.width / 100.0

        speed_norm = OBSTACLE_SPEED / 20.0
        vy_norm = (self.vy + 15.0) / 30.0

        x_in = np.array([[dist_norm], [width_norm], [speed_norm], [vy_norm]])
        out = self.nn.forward(x_in)
        action_prob = float(out[0,0])

        # Heurística de rótulo para backprop:
        # Se obstáculo estiver bem próximo (dist_norm < threshold) e agente no chão -> label = 1 (pular)
        # Senão label = 0
        label = 0.0
        if next_obs is not None:
            # threshold depende do obstacle width and speed
            threshold_px = 120  # se estiver dentro de 120 px -> deve pular
            if min_dist <= threshold_px and self.on_ground:
                label = 1.0
            else:
                label = 0.0
        else:
            label = 0.0

        # Aplica backprop online (treinamento durante o episódio)
        if do_backprop:
            self.nn.train_online(x_in, np.array([[label]]), lr=0.03)

        # decidir ação
        if action_prob > 0.5:
            self.jump()

    def update(self, obstacles):
        if not self.alive:
            return
        self.age += 1
        self.decide_and_act(obstacles, do_backprop=True)
        self.apply_physics()
        self.score += 1.0/FPS  # tempo vivo como métrica

    def check_collision(self, obstacles):
        r = self.rect()
        for ob in obstacles:
            if r.colliderect(ob.rect()):
                self.alive = False
                return True
        return False

# ---------- Simulação e Treinamento ----------
class Simulation:
    def __init__(self):
        pygame.init()
        pygame.display.set_caption("Jump GA + Backprop")
        self.screen = pygame.display.set_mode((SCREEN_W, SCREEN_H))
        self.clock = pygame.time.Clock()
        self.font = pygame.font.SysFont("Arial", 16)

        self.population = [Agent() for _ in range(POPULATION)]
        self.generation = 1
        self.best_agent = None

        self.obstacles = []
        self.frame_count = 0
        self.spawn_timer = 0

    def reset_environment(self):
        self.obstacles = []
        self.frame_count = 0
        self.spawn_timer = 0
        for a in self.population:
            a.x = 80
            a.y = GROUND_Y - 40
            a.vy = 0.0
            a.on_ground = True
            a.alive = True
            a.score = 0.0
            a.age = 0

    def spawn_obstacle(self):
        ob = Obstacle(SCREEN_W + random.randint(10, 80))
        self.obstacles.append(ob)

    def step(self):
        self.frame_count += 1
        # spawn
        self.spawn_timer += 1
        if self.spawn_timer >= OBSTACLE_SPAWN_INTERVAL:
            self.spawn_timer = 0
            self.spawn_obstacle()

        # update obstacles
        for ob in self.obstacles:
            ob.update()
        # remove off-screen obstacles
        self.obstacles = [o for o in self.obstacles if o.x + o.width > -50]

        alive_count = 0
        for agent in self.population:
            if agent.alive:
                alive_count += 1
                agent.update(self.obstacles)
                agent.check_collision(self.obstacles)

        # se todos morreram ou tempo máximo, termina geração
        return alive_count

    def evaluate_and_evolve(self):
        # compute fitness: baseado em score (tempo vivo) + distância percorrida (none aqui)
        for a in self.population:
            a.fitness = a.score + (0 if not a.alive else 0.0) + (a.age/100.0)

        # ordenar por fitness decrescente
        sorted_pop = sorted(self.population, key=lambda a: a.fitness, reverse=True)
        self.best_agent = sorted_pop[0].copy() if isinstance(sorted_pop[0], Agent) else None

        # imprimir estatísticas
        best_fit = sorted_pop[0].fitness
        avg_fit = sum(a.fitness for a in self.population) / len(self.population)
        print(f"Gen {self.generation} — best fitness: {best_fit:.2f}, avg: {avg_fit:.2f}")

        # seleção: keep elites
        new_pop = []
        for i in range(ELITE_KEEP):
            # mantemos cópias dos melhores
            elite_nn = sorted_pop[i].nn.copy()
            new_pop.append(Agent(nn=elite_nn))

        # restante via crossover e mutação
        while len(new_pop) < POPULATION:
            # torneio seleção
            p1 = tournament_select(sorted_pop)
            p2 = tournament_select(sorted_pop)
            if random.random() < CROSSOVER_RATE:
                child_nn = crossover(p1.nn, p2.nn)
            else:
                child_nn = p1.nn.copy()
            mutate(child_nn, rate=MUTATION_RATE, scale=MUTATION_SCALE)
            new_pop.append(Agent(nn=child_nn))

        self.population = new_pop
        self.generation += 1

    def draw(self):
        self.screen.fill((30,30,30))
        # chão
        pygame.draw.rect(self.screen, (100,100,100), (0, GROUND_Y, SCREEN_W, SCREEN_H-GROUND_Y))
        # obstaculos
        for ob in self.obstacles:
            pygame.draw.rect(self.screen, (200,50,50), ob.rect())

        # agentes (desenhar parcialmente transparente)
        for agent in self.population:
            c = (50, 200, 100) if agent.alive else (80,80,80)
            rect = agent.rect()
            pygame.draw.rect(self.screen, c, rect)
            # olhos pra diferenciar
            pygame.draw.circle(self.screen, (0,0,0), (rect.x+8, rect.y+10), 3)

        # HUD
        alive = sum(1 for a in self.population if a.alive)
        best_fit = max(a.fitness for a in self.population) if self.population else 0
        texts = [
            f"Generation: {self.generation}",
            f"Alive: {alive}/{len(self.population)}",
            f"Best fitness (est): {best_fit:.2f}",
            "Esc: quit"
        ]
        for i, t in enumerate(texts):
            surf = self.font.render(t, True, (255,255,255))
            self.screen.blit(surf, (10, 10 + i*18))

        pygame.display.flip()

    def run(self):
        running = True
        paused = False
        while running:
            self.clock.tick(FPS)
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        running = False
                    if event.key == pygame.K_p:
                        paused = not paused

            if not paused:
                alive = self.step()
                if alive == 0:
                    # evolução
                    self.evaluate_and_evolve()
                    self.reset_environment()

            self.draw()

        pygame.quit()

# ---------- Helpers ----------
def tournament_select(sorted_pop, k=5):
    # torneio: escolhe k aleatórios e retorna o melhor
    competitors = random.sample(sorted_pop, k)
    return max(competitors, key=lambda a: a.fitness)

# Add a copy method to Agent (we used earlier)
def agent_copy(self):
    new = Agent(nn=self.nn.copy())
    new.x = self.x
    new.y = self.y
    new.vy = self.vy
    new.on_ground = self.on_ground
    new.alive = self.alive
    new.score = self.score
    new.fitness = self.fitness
    new.age = self.age
    return new

Agent.copy = agent_copy

# ---------- Execução ----------
if __name__ == "__main__":
    sim = Simulation()
    sim.reset_environment()
    sim.run()
