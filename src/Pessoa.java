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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	protected void ganharXP() {
		this.experiencia += 10;
	}
}
