from vispy import scene
from vispy.scene import visuals

class NeuralNetworkCanvas(scene.SceneCanvas):
    def __init__(self, model, controller, neuron_size=20):
        super().__init__(keys='interactive', bgcolor="#f0f0f0", show=False)
        self.unfreeze()
        self.model = model
        self.controller = controller
        self.neuron_size = neuron_size

        self.view = self.central_widget.add_view()
        self.view.camera = scene.PanZoomCamera(interactive=False)

        self.lines = visuals.Line(pos=self.model.lines.reshape(-1,2),
                                  color='gray', connect='segments', parent=self.view.scene)

        self.neurons = visuals.Markers(parent=self.view.scene)
        self.neurons.set_data(self.model.positions, size=self.neuron_size,
                              face_color=self.model.colors, edge_color='black')

        self._adjust_camera()

        self.freeze()

    def _adjust_camera(self):
        x_min, y_min = self.model.positions.min(axis=0)
        x_max, y_max = self.model.positions.max(axis=0)
        padding_x = (x_max - x_min) 
        padding_y = (y_max - y_min)
        self.view.camera.rect = (x_min - padding_x, y_min - padding_y,
                                 (x_max - x_min) + 2*padding_x,
                                 (y_max - y_min) + 2*padding_y)

    def update_colors(self):
        self.neurons.set_data(self.model.positions, size=self.neuron_size,
                              face_color=self.model.colors, edge_color='black')