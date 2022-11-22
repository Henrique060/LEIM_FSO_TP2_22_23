package fso.trab1.comunicacao;

import java.util.Random;

import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Fugir extends Administrador {
	JTextArea textAreaConsola;
	private Random rnd;
	private RobotLegoEV3 r;

	public void executar() {
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
					r.Reta(50);
					break;
				case 1:
					if (rand_int == 1) {
						r.CurvarDireita(0, 90);
					} else {
						r.CurvarEsquerda(0, 90);
					}
					r.SetVelocidade(50);
					break;
				}
			}
		}
	}

}
