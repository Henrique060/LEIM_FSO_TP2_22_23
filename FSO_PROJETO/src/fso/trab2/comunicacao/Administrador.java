package fso.trab2.comunicacao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fso.trab2.comunicacao.comportamentos.Comportamento;
import fso.trab2.comunicacao.comportamentos.Evitar;
import fso.trab2.comunicacao.comportamentos.Fugir;
import fso.trab2.comunicacao.comportamentos.Vaguear;
import robot.RobotLegoEV3;

/**
 * Create the application.
 */
public class Administrador implements ActionListener, KeyListener {

	private JTextArea textAreaConsola;
	public JFrame frmGuiDoAdmin;
	private JTextField textNome;
	private JTextField textDist;
	private JTextField textAng;
	private JTextField textRaio;
	private JRadioButton rdbtnOnOff;
	private JButton btnFrente;
	private JButton btnParar;
	private JButton btnRetaguarda;
	private JButton btnDireita;
	private JButton btnEsquerda;
	private JCheckBox chckbxDebug;
	public JCheckBox chckbxVaguear;
	public JCheckBox chckbxEvitar;
	public JCheckBox chckbxFugir;

	// thread handling
	private Thread thVaguear;
	public boolean vagueando;

	private Thread thEvitar;
	public boolean evitando;

	private Thread thFugir;
	public boolean fugindo;

	public RobotLegoEV3 r = new RobotLegoEV3();

	public Administrador() {
		initialize();
		frmGuiDoAdmin.addKeyListener(this);
		frmGuiDoAdmin.setResizable(false);
		frmGuiDoAdmin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// idea- enves de estar a trabalhar com esta variável aqui, ir buscar o estado
		// com um getEstado ao comportamento

		vagueando = false;
		evitando = false;
		fugindo = false;

		// Launch das threads
		thVaguear = new Vaguear(this);
		thVaguear.start();

		thEvitar = new Evitar(this);
		thEvitar.start();

		thFugir = new Fugir(this);
		thFugir.start();

		frmGuiDoAdmin = new JFrame();
		frmGuiDoAdmin.setTitle("GUI do SERVIDOR");
		frmGuiDoAdmin.setBounds(100, 170, 449, 417);
		frmGuiDoAdmin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGuiDoAdmin.getContentPane().setLayout(null);

		frmGuiDoAdmin.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (r.OpenEV3(textNome.getText()) == true) {
					r.Parar(true);
					try {
						Thread.sleep(200);
					} catch (InterruptedException f) {
						f.printStackTrace();
					}
					r.CloseEV3();
				}
				System.exit(0);
			}
		});

		textNome = new JTextField();
		textNome.setBounds(64, 10, 198, 19);
		textNome.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textAreaConsola.append("\r" + textNome.getText() + "\n");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		frmGuiDoAdmin.getContentPane().add(textNome);
		textNome.setColumns(10);

		rdbtnOnOff = new JRadioButton("On/Off");
		rdbtnOnOff.setBounds(319, 9, 103, 21);
		rdbtnOnOff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				r.OpenEV3(textNome.getText());
				if (r.OpenEV3(textNome.getText()) == false) {

					textAreaConsola.append("\r" + "Robot  not ON" + "\n");
				} else {
					r.Parar(true);
					try {
						Thread.sleep(200);
					} catch (InterruptedException f) {
						f.printStackTrace();
					}
					// r.CloseEV3();
					textAreaConsola.append("\r" + "Robot OFF" + "\n");
				}
			}

		});

		frmGuiDoAdmin.getContentPane().add(rdbtnOnOff);

		textDist = new JTextField();
		textDist.setBounds(368, 36, 54, 19);
		textDist.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textAreaConsola.append("\r" + "Distancia: " + textDist.getText() + "\n");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frmGuiDoAdmin.getContentPane().add(textDist);
		textDist.setColumns(10);

		textAng = new JTextField();
		textAng.setColumns(10);
		textAng.setBounds(208, 39, 54, 19);
		textAng.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textAreaConsola.append("\r" + "Angulo: " + textAng.getText() + "\n");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frmGuiDoAdmin.getContentPane().add(textAng);

		textRaio = new JTextField();
		textRaio.setColumns(10);
		textRaio.setBounds(64, 39, 54, 19);
		textRaio.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textAreaConsola.append("\r" + "Raio: " + textRaio.getText() + "\n");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frmGuiDoAdmin.getContentPane().add(textRaio);

		btnFrente = new JButton("Frente");
		btnFrente.setBounds(159, 68, 103, 35);
		btnFrente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaConsola.append("\r" + "Frente..." + "\n");
				textAreaConsola.append("\r" + "Distancia: " + textDist.getText() + "\n");
				r.Reta(Integer.parseInt(textDist.getText()));
				r.Parar(false);

			}
		});
		frmGuiDoAdmin.getContentPane().add(btnFrente);

		btnParar = new JButton("Parar");
		btnParar.setBounds(159, 107, 103, 35);
		btnParar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaConsola.append("\r" + "Parar..." + "\n");
				r.Parar(true);
				textAreaConsola.append("\r" + "Parado!!!" + "\n");
			}
		});
		frmGuiDoAdmin.getContentPane().add(btnParar);

		btnRetaguarda = new JButton("Retaguarda");
		btnRetaguarda.setBounds(159, 146, 103, 35);
		btnRetaguarda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaConsola.append("\r" + "Retaguarda..." + "\n");
				textAreaConsola.append("\r" + "Distancia: " + "-" + textDist.getText() + "\n");
				r.Reta(-Integer.parseInt(textDist.getText()));
				r.Parar(false);
			}
		});
		frmGuiDoAdmin.getContentPane().add(btnRetaguarda);

		btnDireita = new JButton("Direita");
		btnDireita.setBounds(266, 107, 103, 35);
		btnDireita.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaConsola.append("\r" + "Direita..." + "\n");
				textAreaConsola.append("\r" + "Raio: " + textRaio.getText() + "\n");
				textAreaConsola.append("\r" + "Angulo: " + textAng.getText() + "\n");
				r.CurvarDireita(Double.parseDouble(textRaio.getText()), Integer.parseInt(textAng.getText()));
				r.Parar(false);

			}
		});
		frmGuiDoAdmin.getContentPane().add(btnDireita);

		btnEsquerda = new JButton("Esquerda");
		btnEsquerda.setBounds(53, 107, 103, 35);
		btnEsquerda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaConsola.append("\r" + "Esquerda..." + "\n");
				textAreaConsola.append("\r" + "Raio: " + textRaio.getText() + "\n");
				textAreaConsola.append("\r" + "Angulo: " + textAng.getText() + "\n");
				r.CurvarEsquerda(Double.parseDouble(textRaio.getText()), Integer.parseInt(textAng.getText()));
				r.Parar(false);
			}
		});
		frmGuiDoAdmin.getContentPane().add(btnEsquerda);

		chckbxDebug = new JCheckBox("Debug");
		chckbxDebug.setBounds(6, 153, 93, 21);
		frmGuiDoAdmin.getContentPane().add(chckbxDebug);

		chckbxVaguear = new JCheckBox("Vaguear");
		chckbxVaguear.setBounds(337, 146, 93, 21);
		chckbxVaguear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vagueando) {
					textAreaConsola.append("\r" + "Parou de Vaguear" + "\n");
					((Vaguear) thVaguear).pause();
					r.Parar(true);
					vagueando = false;
				} else {
					((Vaguear) thVaguear).play();
					textAreaConsola.append("\r" + "A Vaguear..." + "\n");
					vagueando = true;
				}

			}
		});
		frmGuiDoAdmin.getContentPane().add(chckbxVaguear);

		chckbxEvitar = new JCheckBox("Evitar");
		chckbxEvitar.setBounds(337, 166, 93, 21);
		chckbxEvitar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (evitando) {
					textAreaConsola.append("\r" + "A parar de Evitar..." + "\n");
					((Evitar) thEvitar).pause();
					r.Parar(true);
					evitando = false;
				} else {
					((Evitar) thEvitar).play();
					textAreaConsola.append("\r" + "A Evitar..." + "\n");
					evitando = true;
				}
			}
		});
		frmGuiDoAdmin.getContentPane().add(chckbxEvitar);

		chckbxFugir = new JCheckBox("Fugir");
		chckbxFugir.setBounds(337, 186, 93, 21);
		chckbxFugir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fugindo) {
					textAreaConsola.append("\r" + "A parar de Fugir..." + "\n");
					//((Fugir) thFugir).pause();
					//r.Parar(true);
					try {
						inFugir(); //testar se funciona
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					fugindo = false;
				} else {
					//((Fugir) thFugir).play();
					outFugir();
					textAreaConsola.append("\r" + "A Fugir..." + "\n");
					fugindo = true;
				}
			}
		});
		frmGuiDoAdmin.getContentPane().add(chckbxFugir);

		JLabel lblNewLabel = new JLabel("Robot");
		lblNewLabel.setBounds(6, 13, 62, 13);
		frmGuiDoAdmin.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Consola");
		lblNewLabel_1.setBounds(6, 192, 62, 13);
		frmGuiDoAdmin.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Raio");
		lblNewLabel_2.setBounds(9, 42, 45, 13);
		frmGuiDoAdmin.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Angulo");
		lblNewLabel_2_1.setBounds(153, 42, 45, 13);
		frmGuiDoAdmin.getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Distancia");
		lblNewLabel_2_1_1.setBounds(295, 39, 63, 13);
		frmGuiDoAdmin.getContentPane().add(lblNewLabel_2_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 228, 402, 131);
		frmGuiDoAdmin.getContentPane().add(scrollPane);

		textAreaConsola = new JTextArea();
		scrollPane.setViewportView(textAreaConsola);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrador window = new Administrador();
					window.frmGuiDoAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


	
/**
 * teste ideia synchronized
 */
	
	public boolean ocupado;
	
	public synchronized void inEvitar() throws InterruptedException {
		while(((Evitar) thEvitar).estaEvitar == true) {
			thVaguear.wait();
			thFugir.wait();
		}
		this.ocupado = true;
	}
	
	public synchronized void outEvitar() {
		this.ocupado = false;
		this.notifyAll();
	}
	
	public synchronized void inFugir() throws InterruptedException {
	
		while(((Fugir)thFugir).estaFugir == true) {
			thVaguear.wait();
		}
		this.ocupado = true;
	}
	
	public synchronized void outFugir() {
		this.ocupado = false;
		this.notifyAll();
	}
}
