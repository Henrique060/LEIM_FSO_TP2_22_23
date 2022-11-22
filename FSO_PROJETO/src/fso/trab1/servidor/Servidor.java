package fso.trab1.servidor;

import robot.RobotLegoEV3;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import fso.trab1.gui.Guiapp;

public class Servidor implements Runnable {
	private String nome;
	private int dist, ang;
	private double raio;
	private RobotLegoEV3 r;


	Process processoVaguear;
	Process processoEvitar;

	private final int estadoInicial = 0;
	private final int estadoProcesso = 1;
	private int estadoAtual;
	
	public Servidor() {
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

	public void run() {
		System.out.println("maquina de estados ativa");
		estadoAtual = estadoInicial;

	
		for (;;) {

			switch (estadoAtual) {
			case estadoInicial:
				break;

			case estadoProcesso:
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


	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		System.out.println("main chamado");
		Guiapp g = new Guiapp();
		EventQueue.invokeAndWait(g);
		System.out.println("antes de g run");
		g.getServidor().run();
		// g.run(); -> não será necesserário visto que o main chama o runnable
	}
}
