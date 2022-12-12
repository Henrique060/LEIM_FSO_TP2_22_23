package fso.trab2.comunicacao.comportamentos;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Vaguear extends Comportamento {
	JTextArea textAreaConsola;
	private Estado estado = Estado.Working;

	public Vaguear() {
		this.setName("Vaguear");
		gui_vaguear();
	}

	@Override
	public void run() {
		for (;;) {

			switch (this.estado) {
			case Working:
				this.work();

				break;
			case Paused:
				synchronized (this) {
					try {
						textAreaConsola.append("\r" + "a ir fazer wait" + "\n");
						this.wait();
					} catch (InterruptedException e) {
						System.out.println("wait vaguear interrompido");
						e.printStackTrace();
					}
				}
				break;
			}

		}
	}

	public void gui_vaguear() {

		JFrame frmGuiDoVaguear = new JFrame();
		frmGuiDoVaguear.setTitle("GUI do Vaguear");
		frmGuiDoVaguear.setBounds(550, 100, 449, 290);
		frmGuiDoVaguear.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGuiDoVaguear.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 20, 402, 200);
		frmGuiDoVaguear.getContentPane().add(scrollPane);

		textAreaConsola = new JTextArea();
		scrollPane.setViewportView(textAreaConsola);

		frmGuiDoVaguear.setResizable(false);
		frmGuiDoVaguear.setVisible(true);

	}

	@Override
	public void work() {
		try {

			textAreaConsola.append("\r" + "vagueando" + "\n");

			Thread.sleep( 1000 );
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// criar os random ints dentro do for para que
		// cada iteração tenha o seu numero proprio
//					int rand_int = rnd.nextInt(50);
//					int rand_angulo = rnd.nextInt(360);
//					int rand_raio = rnd.nextInt(50);
		//
//					// random para decidir qual a usar
//					int escolhida = rnd.nextInt(3);
		//
//					switch (escolhida) {
		//
//					case 0:
//						r.Reta(rand_int);
//						textAreaConsola.append("\r" + "Reta com distância: " + rand_int + "\n");
//						break;
//					case 1:
//						r.CurvarDireita(rand_raio, rand_angulo);
//						textAreaConsola
//								.append("\r" + "Curva Direita com raio: " + rand_raio + " e ângulo: " + rand_angulo + "\n");
//						break;
//					case 2:
//						r.CurvarEsquerda(rand_raio, rand_angulo);
//						textAreaConsola
//								.append("\r" + "Curva Esquerda com raio: " + rand_raio + " e ângulo: " + rand_angulo + "\n");
//						break;
//					}
	}

	public void goWork() {
		this.estado = Estado.Working;
	}

	public void takeABreak() {
		this.interrupt();
		this.estado = Estado.Paused;
	}

}
