class NeuralNetworkController:
    def __init__(self, model, canvas=None):
        self.model = model
        self.canvas = canvas  # opcional, será setado depois

    def set_neuron_color(self, layer, neuron_idx, color):
        self.model.set_neuron_color(layer, neuron_idx, color)
        if self.canvas:
            self.canvas.update_colors()

    def reset_colors(self, color=[0.7,0.7,0.7,1.0]):
        self.model.reset_colors(color)
        if self.canvas:
            self.canvas.update_colors()
