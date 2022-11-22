package fso.trab1.comunicacao;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.RobotLegoEV3;

public class Evitar extends Administrador{
	JTextArea textAreaConsola;
	private RobotLegoEV3 r;
	
	public void executar() {
		gui_evitar();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r.SensorToque(RobotLegoEV3.S_1)==1) {
			for (int i = 0; i < 4; i++) {
				
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
}
