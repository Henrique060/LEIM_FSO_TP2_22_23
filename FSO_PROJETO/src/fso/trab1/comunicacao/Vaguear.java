package fso.trab1.comunicacao;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Vaguear extends Administrador {
	private Random rnd;
	JTextArea textAreaConsola;
	private RobotLegoEV3 r;

	public void executar() {
		gui_vaguear();
		// criar os random ints dentro do for para que
		// cada iteração tenha o seu numero proprio
		int rand_int = rnd.nextInt(50);
		int rand_angulo = rnd.nextInt(360);
		int rand_raio = rnd.nextInt(50);

		// random para decidir qual a usar
		int escolhida = rnd.nextInt(3);

		switch (escolhida) {

		case 0:
			r.Reta(rand_int);
			textAreaConsola.append("\r" +"Reta com distância: "+ rand_int + "\n");
			break;
		case 1:
			r.CurvarDireita(rand_raio, rand_angulo);
			textAreaConsola.append("\r" +"Curva Direita com raio: "+ rand_raio+ " e ângulo" + rand_angulo+ "\n");
			break;
		case 2:
			r.CurvarEsquerda(rand_raio, rand_angulo);
			textAreaConsola.append("\r" +"Curva Esquerda com raio: "+ rand_raio+ " e ângulo" + rand_angulo+ "\n");
			break;
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
}
