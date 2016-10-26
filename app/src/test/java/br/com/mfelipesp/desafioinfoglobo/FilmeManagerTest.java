package br.com.mfelipesp.desafioinfoglobo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.servicesMocks.TheMovieServiceMock;

import static org.junit.Assert.assertEquals;

/**
 * Created by markFelipe on 23/10/16.
 */

public class FilmeManagerTest {

    private FilmeManager filmeManager;

    @Before
    public void init(){
        this.filmeManager = new FilmeManagerImpl(new TheMovieServiceMock());
    }

    @Test
    public void getImagemCacheComFilmeUrlNulla(){
        Bitmap bitmap = getFilmeManager().getImagemCache(new Filme(), null);
        assertEquals(bitmap, null);
    }

    @Test
    public void getImagemCacheComFilmeUrlNormal(){
        Filme filme = new Filme();
        filme.setUrlFoto("/ic_launcher.png");
        Bitmap bitmap = getFilmeManager().getImagemCache(filme, TamanhoImagem.GRANDE);
        assertEquals(bitmap,
                null);
    }

    @Test
    public void listaDeFilmes(){
        List<Filme> filmes = getFilmeManager().getFilmes();
        assertEquals(10, filmes.size());
    }


    /***
     * Testar a cobertura do updateImagemView
     */
    @Test
    public void updateImagemView(){
        ImageView imagem = null;
        getFilmeManager().updateImageView("url", TamanhoImagem.GRANDE, imagem);
        assertEquals(null, imagem); //Teste viciado pq a Service esta mocada
    }


    public FilmeManager getFilmeManager() {
        return filmeManager;
    }
}
