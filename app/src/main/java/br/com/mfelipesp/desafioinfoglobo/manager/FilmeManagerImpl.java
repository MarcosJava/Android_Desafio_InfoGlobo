package br.com.mfelipesp.desafioinfoglobo.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.dto.InformationGenreDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ResponseFilmeDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ResponseInformationDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ResultFilmeDTO;
import br.com.mfelipesp.desafioinfoglobo.enums.TamanhoImagem;
import br.com.mfelipesp.desafioinfoglobo.model.Filme;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieService;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieServiceImpl;

/**
 * Created by markFelipe on 22/10/16.
 */

public class FilmeManagerImpl implements FilmeManager{

    private Context context;

    private TheMovieService movieService;

    public FilmeManagerImpl(Context context, TheMovieService movieService) {
        this.context = context;
        this.movieService = movieService;
    }

    @Override
    public List<Filme> getFilmes(){
        try {
            String filmePopular = getJsonDoServicoPopular();

            List<Filme> filmes = new ArrayList<>();
            if(filmePopular != null && !filmePopular.isEmpty()){

                //Transforma o Json do Servico no Objeto
                ResponseFilmeDTO responseFilme = new Gson().fromJson(filmePopular, ResponseFilmeDTO.class);

                //Cria a lista de Filmes
                filmes = tranformaDTOemObjeto(responseFilme);

            }
            return filmes;
        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
            return null;
        }
    }

    @Override
    public Bitmap getImagemCache(Filme filme, TamanhoImagem tamanhoImagem) {
        return this.movieService.getImagemPelaUrlETamanho(filme.getUrlFoto(), tamanhoImagem.getValor());

    }

    @Override
    public void updateImageView(String urlFoto, TamanhoImagem valor, ImageView imageView) {
        this.movieService.updateImageView(urlFoto, valor.getValor(), imageView);
    }


    private List<Filme> tranformaDTOemObjeto(ResponseFilmeDTO responseFilmeDTO){

        if(responseFilmeDTO.getResults() != null && !responseFilmeDTO.getResults().isEmpty()){

            try{
                List<Filme> filmes = new ArrayList<>();

                for(ResultFilmeDTO resultado: responseFilmeDTO.getResults()) {

                    Filme filme = new Filme();
                    filme.setNome(resultado.getTitle());
                    filme.setClassificacao(String.valueOf(resultado.getVoteAverage().intValue()));
                    filme.setSinopse(resultado.getOverview());
                    filme.setUrlFoto(resultado.getPostPath());

                    //Recupera o ID
                    String id = String.valueOf(resultado.getId());

                    //Recupera a Informacao pelo ID
                    ResponseInformationDTO informationDTO =
                            new Gson().fromJson(getJsonDoServicoInformation(id), ResponseInformationDTO.class);

                    filme.setDuracao(informationDTO.getRuntime().toString());

                    filme.setGenero(concatenaGenero(informationDTO.getGenres()));
                    filme.setPais(informationDTO.getProductionCountries().get(0).getName());

                    filme.setFoto(getImagemCache(filme, TamanhoImagem.GRANDE));

                    filmes.add(filme);
                }

                return  filmes;
            }catch (Exception e){
                e.printStackTrace();
                return  null;
            }
        } else {

            return null;
        }
    }

    @NonNull
    private String concatenaGenero(List<InformationGenreDTO> genres) {
        StringBuilder sb = new StringBuilder();
        for (InformationGenreDTO genre :
                genres) {
            sb.append(genre.getName() + "-");
        }
        return sb.toString();
    }

    @Nullable
    private String getJsonDoServicoPopular(){
        try {
            String retorno = "";
            retorno = this.movieService.getJsonPopular();

            if(retorno != null && !retorno.isEmpty()){
                return retorno;
            } else {
                retorno = "";
            }

            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private String getJsonDoServicoInformation(String id){
        try {
            String retorno = "";
            retorno = this.movieService.getInformacaoDoPopular(id);

            if(retorno != null && !retorno.isEmpty()){
                return retorno;
            } else {
                retorno = "";
            }

            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
