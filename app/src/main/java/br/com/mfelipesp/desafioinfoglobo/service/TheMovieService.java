package br.com.mfelipesp.desafioinfoglobo.service;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by markFelipe on 22/10/16.
 *
 * Local que acessa dados do The Movie
 * Serve para conectar com a API e trazer as informacoes
 */

public interface TheMovieService {

    /***
     * Colheta toda informacao que tem dentro do link popular
     * @param idPopular
     * @return
     */
    public String getInformacaoDoPopular(String idPopular);

    /***
     * Coleta todos os filmes mais populares
     * @return
     */
    public String getJsonPopular();

    /***
     * Altera a imagem pela url e concatenando com seu tamnaho
     * @param urlFoto
     * @param valor
     */
    Bitmap getImagemPelaUrlETamanho(String urlFoto, String valor);

    void updateImageView(String urlFoto, String valor, ImageView imageView);
}
