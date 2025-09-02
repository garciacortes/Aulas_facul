import numpy as np
from vispy import scene, app
import time

def neural_calculate(layers, height=200, max_spacing=None, dx=10):
    x = np.repeat(np.arange(len(layers)), layers) * dx
    y = np.concatenate([
        np.linspace(-height/2 + (height/(layer + 1) if max_spacing is None else min(height / (layer + 1), max_spacing)), 
                    height/2 - (height/(layer + 1) if max_spacing is None else min(height / (layer + 1), max_spacing)), 
                    layer) if layer > 1 else np.array([0]) 
        for layer in layers
    ])
    y = -y
    positions = np.column_stack((x, y)) 
    
    layer_start = np.cumsum([0]+layers[:-1])
    layer_end = np.cumsum(layers)

    connections = np.concatenate([
        np.stack([
            np.repeat(positions[layer_start[i]:layer_end[i]], layers[i+1], axis=0),
            np.tile(positions[layer_start[i+1]:layer_end[i+1]], (layers[i],1))
        ], axis=1)
        for i in range(len(layers)-1)
    ], axis=0)

    lines = connections.reshape(-1,2)
    
    all_indices = np.arange(sum(layers))
    split_indices = np.cumsum(layers)[:-1]
    layer_indices = np.split(all_indices, split_indices)
    
    return positions, lines, layer_indices