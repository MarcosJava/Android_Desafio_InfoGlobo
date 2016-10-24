package br.com.mfelipesp.desafioinfoglobo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.R;
import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.manager.FilmeManager;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;

/**
 * Created by markFelipe on 22/10/16.
 */

public class ListFilmeAdapter extends ArrayAdapter<Filme> {

    private List<Filme> filmes;
    private FilmeManager filmeManager;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListFilmeAdapter(Context context, int textViewResourceId, List<Filme> objects, FilmeManager filmeManager) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.filmes = objects;
        this.filmeManager = filmeManager;
    }

    private class ViewHolder {
        ImageView imagem;

    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Create viewHolder
        ViewHolder viewHolder = null;
        Filme filmeItem = this.getItem(position);

        if(this.layoutInflater == null){
            this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        

        // Case by exists layout
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.adapter_itens_filmes, null);
            viewHolder = new ViewHolder();
            viewHolder.imagem = (ImageView) convertView.findViewById(R.id.imagem);
            convertView.setTag(viewHolder);
        } else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        //viewHolder.imagem.setImageBitmap(filmeItem.getFoto());

        filmeManager.updateImageView(filmeItem.getUrlFoto(), TamanhoImagem.GRANDE, viewHolder.imagem);
        return convertView;
    }

}
