package fso.trab2.comunicacao.comportamentos;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Fugir extends Comportamento {
	JTextArea textAreaConsola;
	private Random rnd;

	public void executar() {
		gui_fugir();
		for (;;) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int rand_int = rnd.nextInt(2);

			if (r.SensorUS(RobotLegoEV3.S_2) <= 50) {
				for (int i = 0; i < 2; i++) {

					switch (i) {
					case 0:
						r.SetVelocidade(80);
						textAreaConsola.append("\r" + "Velocidade aumentada para 80" + "\n");
						r.Reta(50);
						textAreaConsola.append("\r" + "Reta com distância: 50" + "\n");
						break;
					case 1:
						if (rand_int == 1) {
							r.CurvarDireita(0, 90);
							textAreaConsola.append("\r" + "Curva Direita com raio: 0" + " e ângulo: 90" + "\n");
						} else {
							r.CurvarEsquerda(0, 90);
							textAreaConsola.append("\r" + "Curva Esquerda com raio: 0" + " e ângulo: 90" + "\n");
						}
						r.SetVelocidade(50);
						textAreaConsola.append("\r" + "Velocidade reduzida para 50" + "\n");
						break;
					}
				}
			}
		}
	}

	public void gui_fugir() {

		JFrame frmGuiDoFugir = new JFrame();
		frmGuiDoFugir.setTitle("GUI do Fugir");
		frmGuiDoFugir.setBounds(550, 400, 449, 290);
		frmGuiDoFugir.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGuiDoFugir.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 20, 402, 200);
		frmGuiDoFugir.getContentPane().add(scrollPane);

		textAreaConsola = new JTextArea();
		scrollPane.setViewportView(textAreaConsola);

		frmGuiDoFugir.setResizable(false);
		frmGuiDoFugir.setVisible(true);

	}

	public static void main(String[] args) {
		Fugir f = new Fugir();
		f.executar();
	}

}
