package Controller;

import javax.swing.JTextArea;

import Service.ThreadConsumidor;
import Service.ThreadProdutor;

public class InterfaceController {
	
	ThreadProdutor tProdutor;
	ThreadConsumidor tConsumidor;
	
	public InterfaceController() {
	}
	
	public void Iniciar(JTextArea listProducer, JTextArea listConsumer) {
		tProdutor = new ThreadProdutor(listProducer);
		tConsumidor = new ThreadConsumidor(listConsumer, tProdutor);
	}
	
	public void Parar() {
		tProdutor.encerrar = true;
		tConsumidor.encerrar = true;
	}
}
