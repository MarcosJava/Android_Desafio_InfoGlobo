package br.com.mfelipesp.desafioinfoglobo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markFelipe on 22/10/16.
 */
public class ProductionCountriesInformationDTO {

    @SerializedName("iso_3166_1")
    private String iso;

    private String name;

    public ProductionCountriesInformationDTO() {
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
