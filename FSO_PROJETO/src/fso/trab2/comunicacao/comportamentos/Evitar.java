package fso.trab2.comunicacao.comportamentos;

import java.time.Instant;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fso.trab2.comunicacao.Administrador;
import fso.trab2.comunicacao.Monitor;
import fso.trab2.comunicacao.Monitorplay;
import robot.RobotLegoEV3;

public class Evitar extends Comportamento {
	JTextArea textAreaConsola;
	
	//mudei monP de Object para Monitorplay
	public Evitar(Administrador Admin, Monitor monR) {
		super(Admin, monR);
		this.setName("Evitar");
		gui_evitar();
		
		textAreaConsola.append("\r" + "Evitar inicializado " + "\n");
	}


	public void gui_evitar() {

		JFrame frmGui = new JFrame();
		frmGui.setTitle("GUI do Evitar");
		frmGui.setBounds(550, 100, 449, 290);
		frmGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGui.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 20, 402, 200);
		frmGui.getContentPane().add(scrollPane);

		textAreaConsola = new JTextArea();
		scrollPane.setViewportView(textAreaConsola);

		frmGui.setResizable(false);
		frmGui.setVisible(true);

	}
	
	
	
	public void work() throws InterruptedException {
		textAreaConsola.append("\r" + "A evitar..." + "\n");
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized(MONITORrobot) {
			MONITORrobot.entrarEvitar();
			Instant now = Instant.now();
			 System.out.println("Evitar: Doing some task... at " + now);
			if (admin.r.SensorToque(RobotLegoEV3.S_1) == 1) {
				
				admin.r.Parar(true);
				textAreaConsola.append("\r" + "Robot parado" + "\n");
				
				admin.r.Reta(-15);
				textAreaConsola.append("\r" + "Reta com distância: -15" + "\n");
				
				admin.r.CurvarEsquerda(0, 90);
				textAreaConsola.append("\r" + "Curva Direita com raio: 0" + " e ângulo: 90" + "\n");
				
				admin.r.Parar(false);
				textAreaConsola.append("\r" + "Robot parado" + "\n");
				
				
				
			}else {
				textAreaConsola.append("\r" + "Nao ha toque" + "\n");
			}
			MONITORrobot.sair();
		}
		

			

	}
	
	
	
}
