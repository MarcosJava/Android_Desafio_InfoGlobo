package br.com.mfelipesp.desafioinfoglobo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.adapters.ListFilmeAdapter;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieServiceImpl;

public class MainActivity extends AppCompatActivity  implements ListView.OnItemClickListener{

    public final static String EXTRA_FILME = "filme";

    private FilmeManager filmeManager;
    private List<Filme> filmes;

    private ListView listView;
    private ImageView imageView;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //iniciaProgress();

        filmeManager = new FilmeManagerImpl(this, new TheMovieServiceImpl());

        // Get listView
        listView = (ListView) findViewById(R.id.listView);

        imageView = (ImageView) findViewById(R.id.opening);
        listView.setVisibility(View.INVISIBLE);

        //cancelaProgress();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reloadTableView();
            }
        }, 3000);
    }

    /***
     * Retorna uma Lista de Filme pela manager.
     * @return
     */
    private List<Filme> getListFilme() {
        List<Filme> filmes = filmeManager.getFilmes();
        if(filmes != null && !filmes.isEmpty()) {
            return filmes;
        }
        return  null;
    }

    /***
     * Esculta o tap (clique) realizado em cima da foto.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goDetalheFilmeActivity(filmes.get(position));

    }

    /***
     * Inicia a Activity de Detalhe de Filme passando um Extra de dados.
     * @param filme
     */
    private void goDetalheFilmeActivity(Filme filme) {
        Intent it = new Intent(this, DetalheFilmeActivity.class);
        it.putExtra(EXTRA_FILME, filme);
        startActivity(it);
    }


    /***
     * Realiza as consultas para montar a tableView
     */
    public void reloadTableView() {

        iniciaProgress();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                carregaLista();
                cancelaProgress();
            }
        }, 2000);

    }

    public void carregaLista(){
        filmes = getListFilme();
        filmes = (filmes == null) ? new ArrayList<Filme>() : filmes;

        ListFilmeAdapter filmesAdapter =
                new ListFilmeAdapter(this, android.R.layout.simple_list_item_2, filmes, filmeManager);


        listView.setAdapter(filmesAdapter);
        listView.setOnItemClickListener(this);

        listView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
    }

    private void iniciaProgress(){
        boolean podeCancelar = true;
        boolean indeterminado = true;
        String titulo = "Aguarde carregando lista";
        progress = ProgressDialog.show(this, titulo, null, indeterminado, podeCancelar);

    }

    private void cancelaProgress(){
        progress.hide();
    }

    /***
     * Adiciona o menu setting
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /***
     * Recebe acao do menu setting
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reload) {
            iniciaProgress();
            reloadTableView();
            cancelaProgress();
        }

        return super.onOptionsItemSelected(item);
    }
}
