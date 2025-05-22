public class Visualizacao {
	private Gafanhoto espectador;
	private Video videos;
	
	public Visualizacao(Gafanhoto espectador, Video videos) {
		this.espectador = espectador;
		this.videos = videos;
		this.espectador.viuMaisUm();
		this.videos.setViews(this.videos.getViews() + 1);
	}
	
	protected void avaliar() {
		this.videos.receberAvaliacao(5);
	}
	
	protected void avaliar(int nota) {
		this.videos.receberAvaliacao(nota);
	}
	
	protected void darLike() {
		this.videos.like();
	}
	
	protected void darDisLike() {
		this.videos.dislike();
	}
}
