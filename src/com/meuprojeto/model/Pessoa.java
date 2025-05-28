package com.meuprojeto.model;

public abstract class Pessoa {
	private String nome;
	private String sexo;
	private int idade;
	private int experiencia;

	public Pessoa(String nome, String sexo, int idade) {
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.experiencia = 0;
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
