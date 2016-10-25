package br.com.mfelipesp.desafioinfoglobo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.mfelipesp.desafioinfoglobo.R;

/**
 * Created by markFelipe on 24/10/16.
 */

public class RecyclerFilmesHolders extends RecyclerView.ViewHolder {


    private ImageView imageView;

    public RecyclerFilmesHolders(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.imagem);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

}
