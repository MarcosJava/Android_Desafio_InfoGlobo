package br.com.mfelipesp.desafioinfoglobo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.DetalheFilmeActivity;
import br.com.mfelipesp.desafioinfoglobo.MainActivity;
import br.com.mfelipesp.desafioinfoglobo.R;
import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManagerImpl;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieServiceImpl;

/**
 * Created by markFelipe on 24/10/16.
 */

public class RecyclerFilmeAdapter extends RecyclerView.Adapter<RecyclerFilmesHolders> {


    private Context context;
    private List<Filme> filmes;
    private FilmeManager filmeManager;

    public RecyclerFilmeAdapter(Context context, List<Filme> filmes){
        this.context = context;
        this.filmes = filmes;
        this.filmeManager = new FilmeManagerImpl(new TheMovieServiceImpl());
    }

    @Override
    public RecyclerFilmesHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.adapter_itens_filmes, null);

        final RecyclerFilmesHolders holder = new RecyclerFilmesHolders(layoutView);

        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filme filme = filmes.get(holder.getPosition());
                goDetalheFilmeActivity(filme);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerFilmesHolders holder, int position) {
        RecyclerFilmesHolders viewHolder = (RecyclerFilmesHolders) holder;
        String urlFoto = filmes.get(position).getUrlFoto();
        filmeManager.updateImageView(urlFoto, TamanhoImagem.PEQUENA, viewHolder.getImageView());
    }


    @Override
    public int getItemCount() {
        return filmes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imagem);

        }

    }


    private void goDetalheFilmeActivity(Filme filme) {
        Intent it = new Intent(context, DetalheFilmeActivity.class);
        it.putExtra(MainActivity.EXTRA_FILME, filme);
        context.startActivity(it);
    }




}
