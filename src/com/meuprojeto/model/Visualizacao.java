package com.meuprojeto.model;

public class Visualizacao {
	private Gafanhoto espectador;
	private Video videos;
	
	public Visualizacao(Gafanhoto espectador, Video videos) {
		this.espectador = espectador;
		this.videos = videos;
		this.espectador.viuMaisUm();
		this.videos.updateViews(this.videos.getViews() + 1);
	}
	
	public void avaliar() {
		this.videos.receberAvaliacao(5);
	}

	public void avaliar(double nota) {
		this.videos.receberAvaliacao(nota);
	}

	public void darLike() {
		this.videos.like();
	}

	public void darDisLike() {
		this.videos.dislike();
	}
}
