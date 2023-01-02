package fso.trab2.comunicacao.comportamentos;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//acho que não é necessario se estiver inicializado no super
//import robot.RobotLegoEV3;

public class Vaguear extends Comportamento {
	JTextArea textAreaConsola;
	private Random rnd = new Random();
	

	public Vaguear() {
		this.setName("Vaguear");
		gui_vaguear();
	}
	
	public void gui_vaguear() {

		JFrame frmGui = new JFrame();
		frmGui.setTitle("GUI do Vaguear");
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
		
		textAreaConsola.append("\r" + "a vaguear" + "\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			// criar os random ints dentro do for para que
//			// cada iteração tenha o seu numero proprio
//			int rand_int = rnd.nextInt(50);
//			int rand_angulo = rnd.nextInt(360);
//			int rand_raio = rnd.nextInt(50);
//
//			// random para decidir qual a usar
//			int escolhida = rnd.nextInt(3);
//
//			switch (escolhida) {
//
//			case 0:
//				r.Reta(rand_int);
//				textAreaConsola.append("\r" + "Reta com distância: " + rand_int + "\n");
//				break;
//			case 1:
//				r.CurvarDireita(rand_raio, rand_angulo);
//				textAreaConsola
//						.append("\r" + "Curva Direita com raio: " + rand_raio + " e ângulo: " + rand_angulo + "\n");
//				break;
//			case 2:
//				r.CurvarEsquerda(rand_raio, rand_angulo);
//				textAreaConsola
//						.append("\r" + "Curva Esquerda com raio: " + rand_raio + " e ângulo: " + rand_angulo + "\n");
//				break;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

		
	}

	

}
