package fso.trab2.comunicacao.comportamentos;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public abstract class Comportamento extends Thread {

	public RobotLegoEV3 r;
	protected Estado estado = Estado.Paused;
	protected final Object MONITOR = new Object();
	
	//protected JTextArea textAreaConsola;

	@Override
	public void run() {
		for (;;) {
			
			switch (estado) {
			case Working:
				this.work();

				break;
			case Paused:
				synchronized (MONITOR) {
					try {
						//textAreaConsola.append("\r" + "waiting" + "\n");
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
	
	public void work() {
		//implementar no comportamento especifico
	}
	
	
	public void play() {
		synchronized (MONITOR) {
			estado = Estado.Working;
			MONITOR.notify();
		}
		
	}

	public void pause() {
		this.estado = Estado.Paused;
	}
}
