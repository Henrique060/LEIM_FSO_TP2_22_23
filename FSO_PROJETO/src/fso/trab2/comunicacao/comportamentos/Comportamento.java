package fso.trab2.comunicacao.comportamentos;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fso.trab2.comunicacao.Administrador;
import fso.trab2.comunicacao.Monitor;
import fso.trab2.comunicacao.Monitorplay;

public abstract class Comportamento extends Thread {

	public Administrador admin;
	protected Estado estado = Estado.Paused;
	//protected Object MONITORplay;
	protected Monitorplay MONITORplay;
	protected Monitor MONITORrobot;
	
	//protected JTextArea textAreaConsola;
	
	public Comportamento(Administrador Admin, Monitorplay monP, Monitor monR) {
		this.admin=Admin;
		this.MONITORplay = monP;
		this.MONITORrobot = monR;
	}

	//evitar-1  fugir-2   vaguear-3 
	
	//cada vez que evitar começa, os outros deixam
	//se fugir for ativo, o vaguear cala-se
	//o vaguear nao tem importancia
	
	//dois monitores para comportamentos
	
	@Override
	public void run() {
			for (;;) {
				
				switch (estado) {
				case Working:
					try {
						this.work();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case Paused:
					//MONITOR.entrar();
					admin.r.Parar(true);
					//MONITOR.sair();
					
					
					synchronized (MONITORplay) {
						try {
							MONITORplay.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				}

			}
		
	}
	
	public void work() throws InterruptedException {
		//implementar no comportamento especifico
	}
	
	//22h04 adicionei synch igual abaixo 
	public synchronized void play() {
		System.out.println("Played");
		
		synchronized (this.MONITORplay) {
			estado = Estado.Working;
			MONITORplay.notify();
		}
		 
	}
	
	//22h03 adicionei synch no metodo e removi o comentado MONITORplay
	public synchronized void pause() throws InterruptedException {
		System.out.println("Paused");
		//synchronized (MONITORplay) {
			this.estado = Estado.Paused;
			this.interrupt();
		//}
		
	}
	
	// TODO - como as UIs são todas iguais devia dar para serem parte do super, mas dá erro
	
//	public void gui(String title) {
//		JFrame frmGui = new JFrame();
//		frmGui.setTitle(title);
//		frmGui.setBounds(550, 100, 449, 290);
//		frmGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		frmGui.getContentPane().setLayout(null);
//
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(15, 20, 402, 200);
//		frmGui.getContentPane().add(scrollPane);
//
//		textAreaConsola = new JTextArea();
//		scrollPane.setViewportView(textAreaConsola);
//
//		frmGui.setResizable(false);
//		frmGui.setVisible(true);
//	}
	
}
