package br.com.mfelipesp.desafioinfoglobo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.adapters.ListFilmeAdapter;
import br.com.mfelipesp.desafioinfoglobo.adapters.RecyclerFilmeAdapter;
import br.com.mfelipesp.desafioinfoglobo.adapters.RecyclerFilmesHolders;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieServiceImpl;

public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    public final static String EXTRA_FILME = "filme";

    private FilmeManager filmeManager;
    private List<Filme> filmes;

    //private ListView listView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private ProgressDialog progress;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmeManager = new FilmeManagerImpl(new TheMovieServiceImpl());
        addItemOnView();

        setSupportActionBar(toolbar);
        this.swipeRefreshLayout.setOnRefreshListener(this);


        //filmes = getListFilme();
        initImagem();

    }

    private void addItemOnView() {
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        //this.listView = (ListView) findViewById(R.id.listView);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler);
        this.imageView = (ImageView) findViewById(R.id.opening);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    private void initImagem (){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reloadTableView();
            }
        }, 3000);
    }

    /***
     * Verifica se contem acesso a internet
     * @return
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        netInfo.isRoaming();
        netInfo.isConnectedOrConnecting();
        return netInfo != null && netInfo.isConnected();
    }


    /***
     * Realiza as consultas para montar a tableView
     */
    public void reloadTableView() {

        if(isNetworkConnected()){
            //this.swipeRefreshLayout.setRefreshing(true);
            iniciaProgress();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    settingRecyclerFilmes();
                    //swipeRefreshLayout.setRefreshing(false);
                    cancelaProgress();

                }
            }, 1000);
        }
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
     * Acoes realizadas depois que consulta e monta a tableView
     */
    public void settingRecyclerFilmes(){

        if (filmes == null || filmes.isEmpty()) {
            filmes = getListFilme();
        }

        if(filmes.isEmpty()){
            filmes = new ArrayList<>();
        }

        //LinearLayoutManager , StaggeredGridLayoutManager , GridLayoutManager
        gridLayout = new GridLayoutManager(MainActivity.this, 3);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(new RecyclerFilmeAdapter(this, filmes));

        recyclerView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
    }

    /***
     * Incia o loading da ListView
     */
    private void iniciaProgress(){
        boolean podeCancelar = true;
        boolean indeterminado = true;
        String titulo = "Aguarde carregando lista";
        String mensagem = "Nao travamos, aguarde !";
        progress = ProgressDialog.show(this, titulo, mensagem, indeterminado, podeCancelar);

    }

    /***
     * Cancela o loadind da ListView
     */
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
            this.filmes = null;
            reloadTableView();
        }

        return super.onOptionsItemSelected(item);
    }


    /***
     * Realiza o Swipe Down para atualizacao
     */
    @Override
    public void onRefresh() {
        this.filmes = null;
        this.swipeRefreshLayout.setRefreshing(true);
        reloadTableView();
        this.swipeRefreshLayout.setRefreshing(false);
    }
}
