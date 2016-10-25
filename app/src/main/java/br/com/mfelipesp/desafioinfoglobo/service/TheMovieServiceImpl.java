package br.com.mfelipesp.desafioinfoglobo.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import br.com.mfelipesp.desafioinfoglobo.util.HttpUrlHelper;

/**
 * Created by markFelipe on 22/10/16.
 */

public class TheMovieServiceImpl implements TheMovieService{

    private final static String URL = "http://api.themoviedb.org/3/movie/popular?api_key=29071fdc23e0c8789241655254da5d2b";

    @Override
    public String getJsonPopular() {
        try {
            String retorno = new PopularTheMovie().execute(URL).get();
            Log.i("getJsonPopular()", retorno);
            return retorno;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bitmap getImagemPelaUrlETamanho(String urlFoto, String valor) {
        try {

            StringBuilder sb = new StringBuilder();
            sb.append("http://image.tmdb.org/t/p/");
            sb.append(valor);
            sb.append(urlFoto);

            Bitmap bitmap = new DownloadImageTask().execute(sb.toString())
                                          .get();

            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateImageView(String urlFoto, String valor, ImageView imageView) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http://image.tmdb.org/t/p/");
            sb.append(valor);
            sb.append(urlFoto);

            new RealTimeImage(imageView).execute(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getInformacaoDoPopular(String idPopular){

        String url = "https://api.themoviedb.org/3/movie/" + idPopular + "?api_key=29071fdc23e0c8789241655254da5d2b";
        try {
            String retorno = new InformationTheMovie().execute(url).get();
            Log.i("getInformacaoDoPopular", retorno);
            return retorno;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }



    /******
     *
     * Classes realizada para assincronizar os servicos
     *
     */

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {


        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
    }


    private class PopularTheMovie extends AsyncTask<String, Void, String> {
      protected String doInBackground(String... urls) {
          HttpUrlHelper urlHelper = new HttpUrlHelper();
          return urlHelper.makeRequest(urls[0]);
      }

   }
    private class InformationTheMovie extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            HttpUrlHelper urlHelper = new HttpUrlHelper();
            return urlHelper.makeRequest(urls[0]);
        }

    }

    private class RealTimeImage extends AsyncTask<String, Void, Bitmap> {

        private  ImageView imageView;
        public RealTimeImage(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }




}