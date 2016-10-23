package br.com.mfelipesp.desafioinfoglobo.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by markFelipe on 22/10/16.
 */

public class ResponseInformationDTO {

    private Integer runtime;

    @SerializedName("genres")
    private List<InformationGenreDTO> genres;

    @SerializedName("production_countries")
    private List<ProductionCountriesInformationDTO> productionCountries;

    public ResponseInformationDTO() {
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<InformationGenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<InformationGenreDTO> genres) {
        this.genres = genres;
    }

    public List<ProductionCountriesInformationDTO> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountriesInformationDTO> productionCountries) {
        this.productionCountries = productionCountries;
    }
}
