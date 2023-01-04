package fso.trab2.comunicacao.comportamentos;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fso.trab2.comunicacao.Administrador;
import fso.trab2.comunicacao.Monitor;
import robot.RobotLegoEV3;

public abstract class Comportamento extends Thread {

	public Administrador admin;
	protected Estado estado = Estado.Paused;
	protected Monitor MONITOR;
	
	//protected JTextArea textAreaConsola;
	
	public Comportamento(Administrador Admin, Monitor mon) {
		this.admin=Admin;
		this.MONITOR = mon;
	}

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
					try {
						MONITOR.entrar();
						admin.r.Parar(true);
						MONITOR.sair();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					synchronized (MONITOR) {
						try {
							MONITOR.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				}

			}
		
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
	
	public void work() throws InterruptedException {
		//implementar no comportamento especifico
	}
	
	
	public void play() {
		synchronized (MONITOR) {
			estado = Estado.Working;
			MONITOR.notify();
		}
		 
	}

	public void pause() throws InterruptedException {
		synchronized (MONITOR) {
			this.estado = Estado.Paused;
			this.interrupt();
		}
		
	}
	
}
