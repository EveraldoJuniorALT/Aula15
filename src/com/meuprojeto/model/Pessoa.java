package com.meuprojeto.model;

public abstract class Pessoa {
	private final String nome;
	private final String sexo;
	private final int idade;
	private int experiencia;

	public Pessoa(String nome, String sexo, int idade) {
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
	}

	// Constructor for the DataBase
	public Pessoa(String nome, String sexo, int idade, int experiencia) {
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.experiencia = experiencia;
	}

	@Override
	public String toString() {
		return "-------------------------" + "\n" 
				+ "Nome: " + nome + "\n" 
				+ "Sexo: " + sexo + "\n" 
				+ "Idade: " + idade + "\n"
				+ "Experiencia: " + experiencia + "\n";
	}

	public String getNome() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}

	public int getIdade() {
		return idade;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void ganharXP() {
		this.experiencia += 10;
	}
}
