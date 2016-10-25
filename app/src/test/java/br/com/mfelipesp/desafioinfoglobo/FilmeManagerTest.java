package br.com.mfelipesp.desafioinfoglobo;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
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
    public void validarManager(){
        assertEquals(4, 2 + 2);
    }


}
