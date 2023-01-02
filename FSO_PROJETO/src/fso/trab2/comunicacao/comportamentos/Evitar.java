package fso.trab2.comunicacao.comportamentos;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Evitar extends Comportamento {
	JTextArea textAreaConsola;
	
	public Evitar() {
		this.setName("Evitar");
		gui_evitar();
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
	
	public void work() {
		textAreaConsola.append("\r" + "a evitar" + "\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		try {
//			Thread.sleep(250);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (r.SensorToque(RobotLegoEV3.S_1) == 1) {
//			for (int i = 0; i < 4; i++) {
//
//				switch (i) {
//				case 0:
//					r.Parar(true);
//					textAreaConsola.append("\r" + "Robot parado" + "\n");
//					break;
//				case 1:
//					r.Reta(-15);
//					textAreaConsola.append("\r" + "Reta com distância: -15" + "\n");
//					break;
//				case 2:
//					r.CurvarEsquerda(0, 90);
//					textAreaConsola.append("\r" + "Curva Direita com raio: 0" + " e ângulo: 90" + "\n");
//					break;
//				case 3:
//					r.Parar(true);
//					textAreaConsola.append("\r" + "Robot parado" + "\n");
//					break;
//				}
//			}
//		}
	}
}
