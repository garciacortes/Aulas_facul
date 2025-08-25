package Service;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.JTextArea;

public class ThreadProdutor extends Thread {
	
	ArrayList<Integer> buffer = new ArrayList<Integer>(100);
	int valor = 0;
	public Semaphore mutex = new Semaphore(1);
	public 	boolean encerrar = false;
	JTextArea list;
	Thread objThreadProdutor;
	
	public ThreadProdutor(JTextArea list) {
		this.list = list;
		objThreadProdutor = new Thread(this);
		objThreadProdutor.start();
	}
	
	public void run() {
		while(!encerrar) {
			try {
				mutex.acquire();
				if(buffer.size() < 100) {
					buffer.add(valor);
					list.append(Integer.toString(valor) + "\n");
					valor++;
				}
				mutex.release();
				sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
