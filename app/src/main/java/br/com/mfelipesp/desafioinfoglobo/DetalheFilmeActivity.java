package br.com.mfelipesp.desafioinfoglobo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieServiceImpl;

public class DetalheFilmeActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtDescricao, txtNome, txtAno, txtGenero, txtDuracao, txtPais;
    private RatingBar rbClassificacao;

    private FilmeManager filmeManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        addItemNaView();
        filmeManager = new FilmeManagerImpl(new TheMovieServiceImpl());

        Filme filme = getExtras();

        if(filme != null)
            popularView(filme);
    }

    /***
     * Realiza as conexoes dos itens entre a Activity e a View
     */
    private void addItemNaView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView = (ImageView) findViewById(R.id.opening);
        rbClassificacao = (RatingBar) findViewById(R.id.classificacao);
        txtDescricao = (TextView) findViewById(R.id.descricao);
        txtNome = (TextView) findViewById(R.id.nome);
        txtAno = (TextView) findViewById(R.id.ano);
        txtGenero = (TextView) findViewById(R.id.genero);
        txtDuracao = (TextView) findViewById(R.id.duracao);
        txtPais = (TextView) findViewById(R.id.pais);
    }

    /***
     * Popula a View de Detalhes de acordo com o filme passado como @param filme
     * @param filme
     */
    private void popularView(Filme filme) {
        filmeManager.updateImageView(filme.getUrlFoto(), TamanhoImagem.GRANDE, imageView);

        if (!isNullOrVazio(filme.getNome())){
            txtNome.setText(filme.getNome());
        }

        if(!isNullOrVazio(filme.getDuracao())){
            txtDuracao.setText(filme.getDuracao() + " min.");
        }

        if(!isNullOrVazio(filme.getGenero())){
            txtGenero.setText(filme.getGenero());
        }

        if(!isNullOrVazio(filme.getPais())){
            txtPais.setText(filme.getPais());
        }

        if(!isNullOrVazio(filme.getSinopse())) {
            txtDescricao.setText(filme.getSinopse());
        }

        if(!isNullOrVazio(filme.getClassificacao())){
            float classificacao = converteEmString(filme.getClassificacao());
            rbClassificacao.setRating(classificacao);
        }

        if(!isNullOrVazio(filme.getAno())){
            txtAno.setText(filme.getAno());
        }

    }

    /***
     * Retorna um Filme, sendo preenchido ou sendo nullo
     * @return
     */
    public Filme getExtras() {
        Bundle bundle = getIntent().getExtras();
        Filme filme = bundle.getParcelable(MainActivity.EXTRA_FILME);
        if (filme != null)
            return filme;

        return null;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
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


    /***
     * valida a String se ela nao contem valores vazios ou nullos
     * @param valor
     * @return
     */
    private boolean isNullOrVazio(String valor){
        if(valor == null || valor.isEmpty() || "".equals(valor.trim())){
            return true;
        }
        return false;
    }

    /***
     * Converte um valor @param valor em Float @return
     * Caso encontre uma letra, não ocorre erro, o @return recebe valor de 1.0 em Float
     * @param valor
     * @return
     */
    private Float converteEmString(String valor){
        Float classificacao = new Float(0F);
        try{
            classificacao = Float.parseFloat(valor);
        } catch (Exception e){
            classificacao = 1F;
        }
        return classificacao;
    }

}
