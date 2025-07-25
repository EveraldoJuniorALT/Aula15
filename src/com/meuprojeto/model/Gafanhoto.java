package com.meuprojeto.model;

public class Gafanhoto extends Pessoa {
	private final String login;
	private int totAssistido;
	private double notaDaAvalicao;
	private int estadoLike;

	public Gafanhoto(String nome, String sexo, int idade, String login) {
		super(nome, sexo, idade);
		this.login = login;
		this.totAssistido = 0;
	}

	// Constructor for the DataBase
	public Gafanhoto(String nome, String sexo, int idade, int experiencia ,String login, int totAssistido) {
		super(nome, sexo, idade, experiencia);
		this.login = login;
		this.totAssistido = totAssistido;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"login: " + login + "\n" 
				+ "Total Assistido: " + totAssistido + "\n"
				+ "-------------------------";
	}

	public void guardarNotaDaAvalicao(double notaDaAvalicao) {
		this.notaDaAvalicao = notaDaAvalicao;
	}

	public void guardarEstadoLike(int estadoLike) {
		this.estadoLike = estadoLike;
	}

	public String getLogin() {
		return login;
	}

	public int getTotAssistido() {
		return totAssistido;
	}

	public double  getNotaDaAvalicao() {
		return notaDaAvalicao;
	}

	public int getEstadoLike() {
		return estadoLike;
	}
	
	public void viuMaisUm() {
		ganharXP();
		this.totAssistido++;
	}
	
}
