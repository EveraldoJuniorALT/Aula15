package com.meuprojeto.repository;

import com.meuprojeto.model.Gafanhoto;
import com.meuprojeto.model.Video;

public interface InRepositorio {

    // Métodos para Gafanhotos
    Gafanhoto getGafanhotos(int index);

    void saveGafanhotoDB(Gafanhoto g);

    int getTotalGafanhotos();

    void requestGafanhoto(int idGafanhoto);

    void updateGafanhoto(int idGafanhoto);

    // Métodos para Vídeos
    Video getVideos(int index);

    void saveVideoDB(Video v);

    int getTotalVideos();

    void requestVideo(int idVideo);

    void updateVideo(int idVideo);
}
