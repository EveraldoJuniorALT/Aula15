package com.meuprojeto.model;

import com.meuprojeto.service.AcoesVideo;

public class Video implements AcoesVideo {

    private final String titulo;
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

    public Video(String titulo, double avaliacao, int views, int gostei, int naoGostei, boolean reproduzindo) {
        this.titulo = titulo;
        this.avaliacao = avaliacao;
        this.views = views;
        this.gostei = gostei;
        this.naoGostei = naoGostei;
        this.reproduzindo = reproduzindo;
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

    public double getAvaliacao() {
        return avaliacao;
    }

    public int getViews() {
        return views;
    }

    public void updateViews(int views) {
        this.views = views;
    }

    public int getGostei() {
        return gostei;
    }

    public int getNaoGostei() {
        return naoGostei;
    }

    public boolean getReproduzindo() {
        return reproduzindo;
    }

    /*
     * Método para calcular a media das avaliações
     * pega a avalição que já possui e soma com uma nova e divide pela quatidade de avaliações
     */
    public void receberAvaliacao(double avaliacao) {
        this.avaliacao = avaliacaoMedia(avaliacao);
    }

    int totAvaliacao; //Contabiliza a quantidade de avaliações para obter-se a média de avaliação
    private double avaliacaoMedia(double avaliacao) {
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
