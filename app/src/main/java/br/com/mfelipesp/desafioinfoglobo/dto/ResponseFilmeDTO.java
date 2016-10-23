package br.com.mfelipesp.desafioinfoglobo.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by markFelipe on 22/10/16.
 */

public class ResponseFilmeDTO {

    @SerializedName("page")
    private Long page;

    @SerializedName("results")
    private List<ResultFilmeDTO> results;

    public ResponseFilmeDTO(Long page, List<ResultFilmeDTO> results) {
        this.page = page;
        this.results = results;
    }

    public ResponseFilmeDTO() {
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<ResultFilmeDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultFilmeDTO> results) {
        this.results = results;
    }
}
