package fso.trab2.comunicacao;

public class Monitor{
	Administrador admin;
	
	public Monitor(Administrador admin) {
		this.admin=admin;
	}

	public synchronized void entrar() throws InterruptedException {
		while ( admin.ocupado ) {
			this.wait();
		}
		admin.ocupado = true;
	}

	public synchronized void sair() throws InterruptedException {
		admin.ocupado = false;
		notifyAll();
	}
	
	public synchronized void entrarEvitar() throws InterruptedException {
		admin.EvitarEspera = true;
		while (admin.ocupado) {
			
			this.wait();
		}
		
		admin.ocupado = true;
		admin.EvitarEspera = false;
		
	}
	
	public synchronized void entrarFugir() throws InterruptedException {
		admin.FugirEspera = true;
		while ( admin.ocupado || admin.EvitarEspera ) {
			
			this.wait();
		}
		
		admin.ocupado = true;
		admin.FugirEspera = false;
		
	}
	
	public synchronized void entrarVaguear() throws InterruptedException {
		while (admin.ocupado || admin.EvitarEspera || admin.FugirEspera) {
			this.wait();
		}
		admin.ocupado = true;

	}
}
