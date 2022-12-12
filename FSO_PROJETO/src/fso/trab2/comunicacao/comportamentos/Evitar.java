package fso.trab2.comunicacao.comportamentos;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Evitar extends Comportamento {
	JTextArea textAreaConsola;

	public void executar() {
		gui_evitar();

		for (;;) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (r.SensorToque(RobotLegoEV3.S_1) == 1) {
				for (int i = 0; i < 4; i++) {

					switch (i) {
					case 0:
						r.Parar(true);
						textAreaConsola.append("\r" + "Robot parado" + "\n");
						break;
					case 1:
						r.Reta(-15);
						textAreaConsola.append("\r" + "Reta com distância: -15" + "\n");
						break;
					case 2:
						r.CurvarEsquerda(0, 90);
						textAreaConsola.append("\r" + "Curva Direita com raio: 0" + " e ângulo: 90" + "\n");
						break;
					case 3:
						r.Parar(true);
						textAreaConsola.append("\r" + "Robot parado" + "\n");
						break;
					}
				}
			}
		}
	}

	public void gui_evitar() {

		JFrame frmGuiDoEvitar = new JFrame();
		frmGuiDoEvitar.setTitle("GUI do Evitar");
		frmGuiDoEvitar.setBounds(550, 400, 449, 290);
		frmGuiDoEvitar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGuiDoEvitar.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 20, 402, 200);
		frmGuiDoEvitar.getContentPane().add(scrollPane);

		textAreaConsola = new JTextArea();
		scrollPane.setViewportView(textAreaConsola);

		frmGuiDoEvitar.setResizable(false);
		frmGuiDoEvitar.setVisible(true);
	}
	
	public static void main(String[] args) {
		Evitar e =new Evitar();
		e.executar();
	}
}
