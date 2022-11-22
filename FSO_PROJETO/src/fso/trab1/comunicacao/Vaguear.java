package fso.trab1.comunicacao;

import java.util.Random;

import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Vaguear extends Administrador {
	private Random rnd;
	JTextArea textAreaConsola;
	private RobotLegoEV3 r;

	public void vaguear() {
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
			break;
		case 1:
			r.CurvarDireita(rand_raio, rand_angulo);
			break;
		case 2:
			r.CurvarEsquerda(rand_raio, rand_angulo);
			break;
		}

	}
}
