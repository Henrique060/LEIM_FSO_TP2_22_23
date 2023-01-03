package fso.trab2.comunicacao.comportamentos;

public class prioridades {

	// thread handling
	private Thread thVaguear;
	public boolean vagueando;

	private Thread thEvitar;
	public boolean evitando;
	public Evitar evitar;

	private Thread thFugir;
	public boolean fugindo;
	public Fugir fugir;
	
	public boolean ocupado;
	
	public synchronized void inEvitar() throws InterruptedException {
		while(evitar.estaEvitar == true) {
			thVaguear.wait();
			thFugir.wait();
		}
		this.ocupado = true;
	}
	
	public synchronized void outEvitar() {
		this.ocupado = false;
		this.notifyAll();
	}
	
	public synchronized void inFugir() throws InterruptedException {
		while(fugir.estaFugir == true) {
			thVaguear.wait();
		}
		this.ocupado = true;
	}
	
	public synchronized void outFugir() {
		this.ocupado = false;
		this.notifyAll();
	}

}
