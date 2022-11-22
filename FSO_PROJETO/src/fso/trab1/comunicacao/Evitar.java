package fso.trab1.comunicacao;

import java.util.Random;

import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Evitar extends Administrador{
	private Random rnd;
	JTextArea textAreaConsola;
	private RobotLegoEV3 r;
	
	public void evitar() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r.SensorToque(RobotLegoEV3.S_1)==1) {
			for (int i = 0; i < 6; i++) {
				
				switch(i) {
				case 0:
					r.Parar(true);
					break;
				case 1:
					r.Reta(-15);
					break;
				case 2:
					r.CurvarEsquerda(0, 90);
					break;
				case 3:
					r.Parar(true);
					break;
				}
			}
		}
	}

}
