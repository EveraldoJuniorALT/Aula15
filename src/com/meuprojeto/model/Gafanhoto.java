package com.meuprojeto.model;

public class Gafanhoto extends Pessoa {
	private String login;
	private int totAssistido;

	public Gafanhoto(String nome, String sexo, int idade, String login) {
		super(nome, sexo, idade);
		this.login = login;
		this.totAssistido = 0;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"login: " + login + "\n" 
				+ "Total Assistido: " + totAssistido + "\n"
				+ "-------------------------";
	}

	public String getLogin() {
		return login;
	}

	public int getTotAssistido() {
		return totAssistido;
	}

	public void updateTotAssistido(int totAssistido) {
		this.totAssistido = totAssistido;
	}
	
	public void viuMaisUm() {
		ganharXP();
		this.totAssistido++;
	}
	
}
