package br.ufmg.coltec.tp.database.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sprites implements Serializable {

    // The default depiction of this Pokémon from the front in battle.
    @SerializedName("front_default")
    private String frontDefaultUrl;

    // The shiny depiction of this Pokémon from the front in battle.
    @SerializedName("front_shiny")
    private String frontShinyUrl;

    // The female depiction of this Pokémon from the front in battle.
    @SerializedName("front_female")
    private String frontFemaleUrl;

    // The shiny female depiction of this Pokémon from the front in battle.
    @SerializedName("front_shiny_female")
    private String frontShinyFemaleUrl;

    // The default depiction of this Pokémon from the back in battle.
    @SerializedName("back_default")
    private String backDefaultUrl;

    // The shiny depiction of this Pokémon from the back in battle.
    @SerializedName("back_shiny")
    private String backShinyUrl;

    // The female depiction of this Pokémon from the back in battle.
    @SerializedName("back_female")
    private String backFemaleUrl;

    // The shiny female depiction of this Pokémon from the back in battle.
    @SerializedName("back_shiny_female")
    private String backShinyFemaleUrl;

    public String getFrontDefaultUrl() {
        return frontDefaultUrl;
    }

    public void setFrontDefaultUrl(String frontDefaultUrl) {
        this.frontDefaultUrl = frontDefaultUrl;
    }

    public String getFrontShinyUrl() {
        return frontShinyUrl;
    }

    public void setFrontShinyUrl(String frontShinyUrl) {
        this.frontShinyUrl = frontShinyUrl;
    }

    public String getFrontFemaleUrl() {
        return frontFemaleUrl;
    }

    public void setFrontFemaleUrl(String frontFemaleUrl) {
        this.frontFemaleUrl = frontFemaleUrl;
    }

    public String getFrontShinyFemaleUrl() {
        return frontShinyFemaleUrl;
    }

    public void setFrontShinyFemaleUrl(String frontShinyFemaleUrl) {
        this.frontShinyFemaleUrl = frontShinyFemaleUrl;
    }

    public String getBackDefaultUrl() {
        return backDefaultUrl;
    }

    public void setBackDefaultUrl(String backDefaultUrl) {
        this.backDefaultUrl = backDefaultUrl;
    }

    public String getBackShinyUrl() {
        return backShinyUrl;
    }

    public void setBackShinyUrl(String backShinyUrl) {
        this.backShinyUrl = backShinyUrl;
    }

    public String getBackFemaleUrl() {
        return backFemaleUrl;
    }

    public void setBackFemaleUrl(String backFemaleUrl) {
        this.backFemaleUrl = backFemaleUrl;
    }

    public String getBackShinyFemaleUrl() {
        return backShinyFemaleUrl;
    }

    public void setBackShinyFemaleUrl(String backShinyFemaleUrl) {
        this.backShinyFemaleUrl = backShinyFemaleUrl;
    }
}
