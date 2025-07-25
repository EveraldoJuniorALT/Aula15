package com.meuprojeto.model;

import com.meuprojeto.service.AcoesVideo;

public class Video implements AcoesVideo {

    private final String titulo;
    private double avaliacao;
    private int views;
    private int gostei;
    private int naoGostei;
    private int totAvaliacao;

    public Video(String titulo) {
        this.titulo = titulo;
        this.avaliacao = 0;
        this.views = 0;
        this.gostei = 0;
        this.naoGostei = 0;
        this.totAvaliacao = 0;
    }

    // Constructor for the DataBase
    public Video(String titulo, double avaliacao, int views, int gostei, int naoGostei, int totAvaliacao) {
        this.titulo = titulo;
        this.avaliacao = avaliacao;
        this.views = views;
        this.gostei = gostei;
        this.naoGostei = naoGostei;
        this.totAvaliacao = totAvaliacao;
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

    public void receberNovaAvaliacao(Double novaNota) {
        Double somaTotalDasNotas = this.avaliacao * this.totAvaliacao;
        this.totAvaliacao++;
        this.avaliacao = (somaTotalDasNotas + novaNota) / this.totAvaliacao;
    }

    public void atualizarAvaliacao(Double notaAntiga, Double novaNota) {
        if (this.totAvaliacao == 0) {
            return;
        }
        double somaTotalDasNotas = this.avaliacao * this.totAvaliacao;
        somaTotalDasNotas = (somaTotalDasNotas - notaAntiga) + novaNota;
        this.avaliacao = somaTotalDasNotas / this.totAvaliacao;
    }

    @Override
    public void like() {
        this.gostei++;
    }

    public void removerLike() {
        this.gostei--;
    }

    @Override
    public void dislike() {
        this.naoGostei++;
    }

    public void removerDislike() {
        this.naoGostei--;
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

    public int getTotAvaliacao() {
        return totAvaliacao;
    }
}
