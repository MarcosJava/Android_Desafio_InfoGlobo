package br.com.mfelipesp.desafioinfoglobo.servicesMocks;

import android.graphics.Bitmap;
import android.widget.ImageView;

import br.com.mfelipesp.desafioinfoglobo.service.TheMovieService;

/**
 * Created by markFelipe on 23/10/16.
 */

public class TheMovieServiceMock implements TheMovieService{
    @Override
    public String getInformacaoDoPopular(String idPopular) {
        return null;
    }

    @Override
    public String getJsonPopular() {
        return null;
    }

    @Override
    public Bitmap getImagemPelaUrlETamanho(String urlFoto, String valor) {
        return null;
    }

    @Override
    public void updateImageView(String urlFoto, String valor, ImageView imageView) {

    }
}
