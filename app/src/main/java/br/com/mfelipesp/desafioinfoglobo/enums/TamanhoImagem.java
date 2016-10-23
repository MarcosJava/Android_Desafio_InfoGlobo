package br.com.mfelipesp.desafioinfoglobo.enums;

/**
 * Created by markFelipe on 22/10/16.
 */

public enum TamanhoImagem {

    PEQUENA,
    GRANDE
    ;

    public String getValor(){
        switch (this){
            case PEQUENA:
                return "w185";
            case GRANDE:
                return "w342";
            default:
                return null;
        }
    }
}
