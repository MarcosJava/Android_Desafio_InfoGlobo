package br.com.mfelipesp.desafioinfoglobo.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
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


    private TheMovieService movieService;

    public FilmeManagerImpl(TheMovieService movieService) {
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

        if(filme != null && filme.getUrlFoto() != null && !filme.getUrlFoto().isEmpty()){
            return this.movieService.getImagemPelaUrlETamanho(filme.getUrlFoto(), tamanhoImagem.getValor());
        } else {
            return null;
        }


    }

    @Override
    public void updateImageView(String urlFoto, TamanhoImagem valor, ImageView imageView) {
        this.movieService.updateImageView(urlFoto, valor.getValor(), imageView);
    }


    private List<Filme> tranformaDTOemObjeto(ResponseFilmeDTO responseFilmeDTO){

        if(responseFilmeDTO.getResults() != null && !responseFilmeDTO.getResults().isEmpty()){

            List<Filme> filmes = new ArrayList<>();

            for(ResultFilmeDTO resultado: responseFilmeDTO.getResults()) {

                if(resultado != null) {
                    Filme filme = new Filme();

                    validaResultado(filme, resultado);
                    //Recupera o ID
                    String id = String.valueOf(resultado.getId());

                    //Json do Response Information
                    String jsonResponseInformation = getJsonDoServicoInformation(id);

                    //Recupera a Informacao pelo ID
                    ResponseInformationDTO informationDTO =
                            new Gson().fromJson(jsonResponseInformation , ResponseInformationDTO.class);

                    if(informationDTO != null){
                        validaInformation(filme, informationDTO);
                    }

                    filmes.add(filme);
                }
            }
            return  filmes;
        }
        return null;
    }

    private void validaInformation(Filme filme, ResponseInformationDTO informationDTO) {

        if(informationDTO.getRuntime() != null){
            filme.setDuracao(informationDTO.getRuntime().toString());
        }

        if (informationDTO.getGenres() != null
                && !informationDTO.getGenres().isEmpty()){
            filme.setGenero(concatenaGenero(informationDTO.getGenres()));
        }

        if (informationDTO.getProductionCountries() != null
                && !informationDTO.getProductionCountries().isEmpty()){
            filme.setPais(informationDTO.getProductionCountries().get(0).getName());
        }

    }

    private void validaResultado(Filme filme, ResultFilmeDTO resultado) {

        if(resultado.getTitle() != null && !resultado.getTitle().isEmpty()){
            filme.setNome(resultado.getTitle());

        }

        if(resultado.getVoteAverage() != null &&
                !resultado.getVoteAverage().isNaN()){
            filme.setClassificacao(String.valueOf(resultado.getVoteAverage().intValue()));
        }

        if(resultado.getOverview() != null &&
                !resultado.getOverview().isEmpty()){
            filme.setSinopse(resultado.getOverview());
        }
        if (resultado.getPostPath() != null &&
                !resultado.getPostPath().isEmpty()) {
            filme.setUrlFoto(resultado.getPostPath());
        }

        if (resultado.getReleaseDate() != null &&
                !resultado.getReleaseDate().isEmpty()){

            String []data = resultado.getReleaseDate().split("-");
            if(!data[0].isEmpty())
                filme.setAno(data[0]);
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

        String retorno = "";
        retorno = this.movieService.getJsonPopular();

        if(retorno != null && !retorno.isEmpty()){
            return retorno;
        }

        return retorno;
    }

    @Nullable
    private String getJsonDoServicoInformation(String id){

        String retorno = "";
        retorno = this.movieService.getInformacaoDoPopular(id);

        if(retorno != null && !retorno.isEmpty()){
            return retorno;
        } else {
            retorno = "";
        }

        return retorno;
    }


}
