package Service;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class ThreadConsumidor extends Thread {
	
	ArrayList<Integer> buffer;
	Thread objThreadConsumidor;
	ThreadProdutor tProdutor;
	JTextArea txtConsumer;
	public boolean encerrar = false;
	
	public ThreadConsumidor(JTextArea txtConsumer, ThreadProdutor tProdutor) {
		this.tProdutor = tProdutor;
		this.txtConsumer = txtConsumer;
		objThreadConsumidor = new Thread(this);
		objThreadConsumidor.start();
	}
	
	public void run() {
		while(!encerrar) {
			try {
				tProdutor.mutex.acquire();
				
				if(tProdutor.buffer.size() > 0) {
					int temp = tProdutor.buffer.remove(0);
					int processamento = temp * 100;
					txtConsumer.append(Integer.toString(processamento) + "\n");
				}
				tProdutor.mutex.release();
				sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
