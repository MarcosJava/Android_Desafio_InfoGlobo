package br.com.mfelipesp.desafioinfoglobo.manager;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;

/**
 * Created by markFelipe on 22/10/16.
 *
 * GERENCIA O FILME , REQUISICOES PARA EXTERNO ,
 * FICA LOCALIZADA A REGRA DE NEGOCIO
 */

public interface FilmeManager {


    /***
     * Realiza toda a Caputa do Modelo Filmes com filmes Populares
     * @return
     */
    public List<Filme> getFilmes();

    /***
     * Coleta a imagemView de acordo com o filme indicado.
     * @param filme
     * @param tamanhoImagem
     * @return
     */
    public Bitmap getImagemCache(Filme filme, TamanhoImagem tamanhoImagem);


    /***
     * Realiza o loading da Imagem em Carregamento.
     * @param urlFoto
     * @param valor
     * @param imageView
     */
    void updateImageView(String urlFoto, TamanhoImagem valor, ImageView imageView);


}
