package fso.trab2.comunicacao.comportamentos;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fso.trab2.comunicacao.Administrador;
import robot.RobotLegoEV3;

public class Fugir extends Comportamento {
	JTextArea textAreaConsola;
	private Random rnd = new Random();
	public boolean estaFugir;
	
	public Fugir(Administrador Admin) {
		super(Admin);
		this.setName("Fugir");
		gui_fugir();
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
	
	public void work() {
		textAreaConsola.append("\r" + "a fugir" + "\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int rand_int = rnd.nextInt(2);

		if (admin.r.SensorUS(RobotLegoEV3.S_2) <= 50) {
			estaFugir = true;
			/*if(admin.vagueando) {
				admin.pauseVaguear();
			}*/
			
			
			
//			for (int i = 0; i < 2; i++) {
//
//				switch (i) {
//				case 0:
//					admin.r.SetVelocidade(80);
//					textAreaConsola.append("\r" + "Velocidade aumentada para 80" + "\n");
//					admin.r.Reta(50);
//					textAreaConsola.append("\r" + "Reta com distância: 50" + "\n");
//					break;
//				case 1:
//					if (rand_int == 1) {
//						admin.r.CurvarDireita(0, 90);
//						textAreaConsola.append("\r" + "Curva Direita com raio: 0" + " e ângulo: 90" + "\n");
//					} else {
//						admin.r.CurvarEsquerda(0, 90);
//						textAreaConsola.append("\r" + "Curva Esquerda com raio: 0" + " e ângulo: 90" + "\n");
//					}
//					admin.r.SetVelocidade(50);
//					textAreaConsola.append("\r" + "Velocidade reduzida para 50" + "\n");
//					break;
//				}
//			}
			admin.r.SetVelocidade(70);
			textAreaConsola.append("\r" + "Velocidade aumentada para 80" + "\n");
			admin.r.Reta(50);
			textAreaConsola.append("\r" + "Reta com distância: 50" + "\n");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (rand_int == 1 || rand_int == 0) {
				admin.r.CurvarDireita(0, 90);
				textAreaConsola.append("\r" + "Curva Direita com raio: 0" + " e ângulo: 90" + "\n");
			} //else {
//				admin.r.CurvarEsquerda(0, 90);
//				textAreaConsola.append("\r" + "Curva Esquerda com raio: 0" + " e ângulo: 90" + "\n");
//			}
			admin.r.SetVelocidade(50);
			admin.r.Reta(1);
			admin.r.Parar(false);
			textAreaConsola.append("\r" + "Velocidade reduzida para 50" + "\n");
			
			/*if(admin.vagueando) {
				admin.playVaguear();
			}*/
		}
		estaFugir = false;
	}


}
