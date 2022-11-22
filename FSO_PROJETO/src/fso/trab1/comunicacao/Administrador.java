package fso.trab1.comunicacao;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Administrador {
	public Administrador() {
		
	}
	
	public void run() {
		gui_admin();
	}
	
	public void parar() {}
	
	public static void gui_admin() {

		JFrame frmGuiDoVaguear = new JFrame();
		frmGuiDoVaguear.setTitle("GUI do Administrador");
		frmGuiDoVaguear.setBounds(550, 100, 449, 290);
		frmGuiDoVaguear.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGuiDoVaguear.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 20, 402, 200);
		frmGuiDoVaguear.getContentPane().add(scrollPane);

		JTextArea textAreaConsola = new JTextArea();
		scrollPane.setViewportView(textAreaConsola);

		frmGuiDoVaguear.setResizable(false);
		frmGuiDoVaguear.setVisible(true);

	}
}
