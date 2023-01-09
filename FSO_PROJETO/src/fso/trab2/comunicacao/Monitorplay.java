package fso.trab2.comunicacao;

public class Monitorplay {
	Administrador admin;
	public Monitorplay(Administrador admin){
		this.admin = admin;
	}
	
	public synchronized void entrar() throws InterruptedException {
		while ( admin.ocupado ) {
			this.wait();
		}
		admin.ocupado = true;
	}

	public synchronized void sair() throws InterruptedException {
		admin.ocupado = false;
		this.notifyAll();
	}
}
