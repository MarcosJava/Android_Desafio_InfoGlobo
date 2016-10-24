package br.com.mfelipesp.desafioinfoglobo.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by markFelipe on 22/10/16.
 */

public class Filme implements Parcelable {

    private String nome;
    private String sinopse;
    private String duracao;
    private String genero;
    private String pais;
    private String classificacao;
    private String urlFoto;

    public Filme(){

    }

    public Filme(String nome, String urlFoto) {
        this.nome = nome;
        this.urlFoto = urlFoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }


    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", duracao='" + duracao + '\'' +
                ", genero='" + genero + '\'' +
                ", pais='" + pais + '\'' +
                ", classificacao='" + classificacao + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }


    public Filme(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.sinopse);
        dest.writeString(this.duracao);
        dest.writeString(this.genero);
        dest.writeString(this.pais);
        dest.writeString(this.classificacao);
        dest.writeString(this.urlFoto);

    }

    private void readFromParcel(Parcel in) {
        this.nome  = in.readString();
        this.sinopse = in.readString();
        this.duracao = in.readString();
        this.genero = in.readString();
        this.pais = in.readString();
        this.classificacao = in.readString();
        this.urlFoto = in.readString();
    }

    /****
     * Realizado para Passar Lista de Parcelable
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Filme>() {
        public Filme createFromParcel(Parcel in) {
            return new Filme(in);
        }

        public Filme[] newArray(int size) {
            return new Filme[size];
        }
    };
}
