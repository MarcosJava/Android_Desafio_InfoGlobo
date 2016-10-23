package br.com.mfelipesp.desafioinfoglobo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieServiceImpl;

public class DetalheFilmeActivity extends AppCompatActivity {

    private ImageView imageView;
    private FilmeManager filmeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        filmeManager = new FilmeManagerImpl(this, new TheMovieServiceImpl());

        getExtras();



    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        Filme filme = bundle.getParcelable(MainActivity.EXTRA_FILME);

        imageView = (ImageView) findViewById(R.id.opening);
        filmeManager.updateImageView(filme.getUrlFoto(), TamanhoImagem.GRANDE, imageView);

        Log.i("VAMO VER DETALHE", filme.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                Intent mIntent = new Intent(this, MainActivity.class);

                startActivity(mIntent);

                finish();

                break;

            default:break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
