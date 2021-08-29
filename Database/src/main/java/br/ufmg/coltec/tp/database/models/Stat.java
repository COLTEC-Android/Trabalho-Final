package br.ufmg.coltec.tp.database.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stat implements Serializable {
    @SerializedName("base_stat")
    private int statValue;

    @SerializedName("effort")
    private int effortValue;

    public int getStatValue() {
        return statValue;
    }

    public void setStatValue(int statValue) {
        this.statValue = statValue;
    }

    public int getEffortValue() {
        return effortValue;
    }

    public void setEffortValue(int effortValue) {
        this.effortValue = effortValue;
    }
}
