package br.com.mfelipesp.desafioinfoglobo.servicesMocks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.mfelipesp.desafioinfoglobo.R;
import br.com.mfelipesp.desafioinfoglobo.dto.InformationGenreDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ProductionCountriesInformationDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ResponseFilmeDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ResponseInformationDTO;
import br.com.mfelipesp.desafioinfoglobo.dto.ResultFilmeDTO;
import br.com.mfelipesp.desafioinfoglobo.service.TheMovieService;

/**
 * Created by markFelipe on 23/10/16.
 */

public class TheMovieServiceMock implements TheMovieService{


    public static final String URL_IMAGEM_TESTE = "/ic_launcher.png";

    private ResultFilmeDTO criaResult(Long id){

        ResultFilmeDTO resultFilmeDTO = new ResultFilmeDTO();
        resultFilmeDTO.setAdult(false);
        resultFilmeDTO.setBackdropPath("Teeste");
        resultFilmeDTO.setTitle("Filme ");
        resultFilmeDTO.setId(id);
        resultFilmeDTO.setReleaseDate("2012-02-22");
        resultFilmeDTO.setOriginalLanguage("PT");
        resultFilmeDTO.setOverview("O Filme eh um teste");
        resultFilmeDTO.setOriginalTitle("Teste O filme");
        resultFilmeDTO.setPopularity(3.0);
        resultFilmeDTO.setVoteAverage(4.4);
        resultFilmeDTO.setGenreIds(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});

        return resultFilmeDTO;
    }

    private ResponseFilmeDTO criarResponse(){
        ResponseFilmeDTO responseFilmeDTO = new ResponseFilmeDTO();
        responseFilmeDTO.setPage(1L);
        List<ResultFilmeDTO> resultFilmeDTOs = new ArrayList<>();

        for (long i=0; i < 10; i++){
            resultFilmeDTOs.add(criaResult(i));
        }
        resultFilmeDTOs.add(null);
        responseFilmeDTO.setResults(resultFilmeDTOs);
        return responseFilmeDTO;
    }

    private List<ProductionCountriesInformationDTO> criarProdutionCountries(){
        ProductionCountriesInformationDTO productionCountriesInformationDTO = new ProductionCountriesInformationDTO();
        productionCountriesInformationDTO.setIso("iso-221");
        productionCountriesInformationDTO.setName("Brazil");
        List<ProductionCountriesInformationDTO> productionCountriesInformationDTOList = new ArrayList<>();
        productionCountriesInformationDTOList.add(productionCountriesInformationDTO);
        return productionCountriesInformationDTOList;
    }

    private ResponseInformationDTO criarResponseInformation(){

        ResponseInformationDTO informationDTO = new ResponseInformationDTO();
        informationDTO.setRuntime(1);

        List<InformationGenreDTO> genres = new ArrayList<>();
        genres.add(new InformationGenreDTO(1L, "Acao"));

        informationDTO.setGenres(genres);

        List<ProductionCountriesInformationDTO> productionCountriesInformationDTOs = criarProdutionCountries();
        informationDTO.setProductionCountries(productionCountriesInformationDTOs);

        return informationDTO;

    }

    @Override
    public String getInformacaoDoPopular(String idPopular) {
        if("5".equals(idPopular)){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(criarResponseInformation());
    }

    @Override
    public String getJsonPopular() {
        Gson gson = new Gson();
        String retorno = gson.toJson(criarResponse());
        return retorno;
    }

    @Override
    public Bitmap getImagemPelaUrlETamanho(String urlFoto, String valor) {

        Bitmap bitmap = null;

        if(URL_IMAGEM_TESTE.equals(urlFoto)){

            return bitmap;
        }

        return bitmap;
    }

    @Override
    public void updateImageView(String urlFoto, String valor, ImageView imageView) {
        imageView = null;
    }
}
