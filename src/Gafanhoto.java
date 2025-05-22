public class Gafanhoto extends Pessoa{
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

	protected String getLogin() {
		return login;
	}

	protected void setLogin(String login) {
		this.login = login;
	}

	protected int getTotAssistido() {
		return totAssistido;
	}

	protected void setTotAssistido(int totAssistido) {
		this.totAssistido = totAssistido;
	}
	
	public void viuMaisUm() {
		ganharXP();
		this.totAssistido++;
	}
	
}
