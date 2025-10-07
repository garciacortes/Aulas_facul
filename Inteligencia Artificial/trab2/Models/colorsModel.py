import numpy as np

class NeuralNetworkModel:
    def __init__(self, positions, lines, layer_indices):
        self.positions = positions
        self.lines = lines
        self.layer_indices = layer_indices
        self.colors = np.tile([0.7,0.7,0.7,1.0], (len(positions),1))

    def set_neuron_color(self, layer, neuron_idx, color):
        global_idx = self.layer_indices[layer][neuron_idx]
        self.colors[global_idx] = color

    def reset_colors(self, color=[0.7,0.7,0.7,1.0]):
        self.colors[:] = color
