package fso.trab1.servidor;

import robot.RobotLegoEV3;
import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import fso.trab1.canal.CanalComunicacaoMensagens;
import fso.trab1.canal.mensagens.Mensagem;
import fso.trab1.canal.mensagens.MensagemCurvaDir;
import fso.trab1.canal.mensagens.MensagemCurvaEsq;
import fso.trab1.canal.mensagens.MensagemParar;
import fso.trab1.canal.mensagens.MensagemReta;
import fso.trab1.canal.mensagens.MensagemToque;
import fso.trab1.gui.GuiApp;

public class Servidor implements Runnable {
	private String nome;
	private int dist, ang;
	private double raio;
	private RobotLegoEV3 r;

	private Mensagem msg;

	Process processoVaguear;
	Process processoEvitar;

	private final int estadoInicial = 0;
	private final int estadoProcesso = 1;
	private int estadoAtual;

	private CanalComunicacaoMensagens c;
	private CanalComunicacaoMensagens cEvi;

	private boolean emWorkflow = false;
	private int id = 0;

	public Servidor() {
		this.c = new CanalComunicacaoMensagens();
		System.out.println("servidor criado");
		this.r = new RobotLegoEV3();
	}

	/*
	 * 1- getters e setters. 2- Ligar, desligar e verificar ligação. 3- Métodos para
	 * os botões da GUI usando os getters.
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	public double getRaio() {
		return raio;
	}

	public void setAngulo(int ang) {
		this.ang = ang;
	}

	public int getAngulo() {
		return ang;
	}

	public void setDistancia(int dist) {
		this.dist = dist;
	}

	public int getDistancia() {
		return dist;
	}

	public void ligarRobo() {
		r.OpenEV3(getNome());
	}

	public void fecharRobo() {
		if (processoVaguear != null) {
			destroyVaguear();
		}

		if (processoEvitar != null) {
			destroyEvitar();
		}

		if (processoEvitar == null && processoVaguear == null) {
			r.Parar(true);
		}

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.CloseEV3();
	}

	public boolean esta_ligado() {
		if (r.OpenEV3(getNome()) == true) {
			return true;
		}
		return false;
	}

	public void frente() {
		r.Reta(getDistancia());
		r.Parar(false);
	}

	public void esquerda() {
		r.CurvarEsquerda(getRaio(), getAngulo());
		r.Parar(false);
	}

	public void direita() {
		r.CurvarDireita(getRaio(), getAngulo());
		r.Parar(false);
	}

	public void retaguarda() {
		r.Reta(getDistancia());
		r.Parar(false);
	}

	public void parar() {
		r.Parar(true);
	}

	public void createVaguear() {
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "..\\Ficheiros\\vaguearExec.jar",
				"..\\Ficheiros\\canal.txt");
		System.out.println("criado Vaguear");
		try {
			processoVaguear = pb.start();
			if (estadoAtual == estadoInicial) {
				estadoAtual = estadoProcesso;
				emWorkflow = false;
				System.out.println("estado: processo");
			}
			System.out.println("começado");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("falhado");
		}
	}

	public void destroyVaguear() {
		System.out.println("processo destruido");
		System.out.println("robot parado");
		processoVaguear.destroy();
		if (estadoAtual == estadoProcesso) {
			r.Parar(true);
			estadoAtual = estadoInicial;
			System.out.println("estado: inicial");
		}
	}

	public void createEvitar() {
		this.cEvi = new CanalComunicacaoMensagens();
		cEvi.abrirCanal("..\\Ficheiros\\canalEvitar.txt");
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "..\\Ficheiros\\evitarExec.jar",
				"..\\Ficheiros\\canal.txt", "..\\Ficheiros\\canalEvitar.txt");
		System.out.println("criado evitar");
		try {
			processoEvitar = pb.start();

			if (estadoAtual == estadoInicial) {
				estadoAtual = estadoProcesso;
				emWorkflow = true;
				System.out.println("estado: processo");
			}
			System.out.println("começado");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("falhado");
		}
	}

	public void destroyEvitar() {
		System.out.println("processo destruido");
		System.out.println("robot parado");

		processoEvitar.destroy();
		if (estadoAtual == estadoProcesso) {
			r.Parar(true);
			estadoAtual = estadoInicial;
			System.out.println("estado: inicial");
		}
	}

	public void run() {
		System.out.println("maquina de estados ativa");
		estadoAtual = estadoInicial;

		c.abrirCanal("..\\Ficheiros\\canal.txt");

		for (;;) {

			switch (estadoAtual) {
			case estadoInicial:
				break;

			case estadoProcesso:
				msg = c.receberMensagemM();
				if (msg != null) {
					if (emWorkflow) {
						lerWorkflow(id);
					} else {
						ler();
					}
				}
				break;
			}

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void ler() {
		try {
			switch (msg.getTipo()) {
			case Mensagem.tipoReta:
				MensagemReta msgReta = (MensagemReta) msg;
				r.Reta(msgReta.getDistancia());
				System.out.println("Tipo Reta");
				break;

			case Mensagem.tipoCurvaDir:
				MensagemCurvaDir msgDireita = (MensagemCurvaDir) msg;
				r.CurvarDireita(msgDireita.getRaio(), msgDireita.getAngulo());
				System.out.println("Tipo Curva Direita");
				break;

			case Mensagem.tipoCurvaEsq:
				MensagemCurvaEsq msgEsquerda = (MensagemCurvaEsq) msg;
				r.CurvarEsquerda(msgEsquerda.getRaio(), msgEsquerda.getAngulo());
				System.out.println("Tipo Curva Esquerda");
				break;

			case Mensagem.tipoParar:
				MensagemParar msgPara = (MensagemParar) msg;
				System.out.println("Tipo Parar");
				r.Parar(msgPara.getEstado());
				break;
			case Mensagem.tipoToque:
				// msg = new MensagemToque((short) 1, (short) r.SensorToque(0), (short) 0);
				msg = new MensagemToque((short) 1, (short) 1, (short) 0);
				// escrever(msg);
				cEvi.enviarMensagem(msg);
				System.out.println("Tipo Toque");
				break;
			case Mensagem.tipoIniciarWorkflow:
				emWorkflow = true;
				id = msg.getid();
				System.out.println("INICIO WORKFLOW " + id);
				break;
			case Mensagem.tipoFimWorkflow:
				emWorkflow = false;
				id = msg.getid();
				System.out.println("Fim WORKFLOW " + id);
				break;

			}
		} catch (Exception e) {

		}
	}

	public void lerWorkflow(int id) {
		if (msg.getid() == id) {
			ler();
		}
	}

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		System.out.println("main chamado");
		GuiApp g = new GuiApp();
		EventQueue.invokeAndWait(g);
		System.out.println("antes de g run");
		g.getServidor().run();
		// g.run(); -> não será necesserário visto que o main chama o runnable
	}

	public void escrever(Mensagem m) throws InterruptedException {
		do {
			Thread.sleep(100);
		} while (c.enviarMensagem(m));
	}

}