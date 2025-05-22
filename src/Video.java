public class Video implements AcoesVideo{
	
	private String titulo;
	private double avaliacao;
	private int views;
	private int gostei;
	private int naoGostei;
	private boolean reproduzindo;
	
	
	
	public Video(String titulo) {
		this.titulo = titulo;
		this.avaliacao = 0;
		this.views = 0;
		this.gostei = 0;
		this.naoGostei = 0;
		this.reproduzindo = false;
	}
	
	@Override
	public String toString() {
		return "-------------------------" + "\n"
			+ "Titulo: " + titulo + "\n"
			+ "Avaliação: " + avaliacao + "\n"
			+ "Visualização: " + views + "\n"
			+ "Likes: " + gostei + "\n"
			+ "Dislikes: " + naoGostei + "\n";
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public double getAvaliacao() {
		return avaliacao;
	}
	
	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public int getViews() {
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public int getGostei() {
		return gostei;
	}
	
	public void setGostei(int gostei) {
		this.gostei = gostei;
	}
	
	public int getNaoGostei() {
		return naoGostei;
	}
	
	protected void setNaoGostei(int naoGostei) {
		this.naoGostei = naoGostei;
	}
	
	protected boolean getReproduzindo() {
		return reproduzindo;
	}
	
	protected void setReproduzindo(boolean reproduzindo) {
		this.reproduzindo = reproduzindo;
	}
	
	/*
	 * Método para calcular a media das avaliações
	 * pega a avalição que já possui e soma com uma nova e divide pela quatidade de avaliações
	 */
	public void receberAvaliacao(int avaliacao) {
		this.avaliacao = avaliacaoMedia(avaliacao);
	}
	
	int totAvaliacao; //Contabiliza a quantidade de avaliações para obter-se a média de avaliação
	private double avaliacaoMedia(int avaliacao) {
		totAvaliacao++;
		return (this.avaliacao + avaliacao) / totAvaliacao;
	}
	
	@Override
	public void play() {
		this.reproduzindo = true;
	}
	@Override
	public void pause() {
		this.reproduzindo = false;
	}
	@Override
	public void like() {
		this.gostei++;
	}
	@Override
	public void dislike() {
		this.naoGostei++;
	}
}
