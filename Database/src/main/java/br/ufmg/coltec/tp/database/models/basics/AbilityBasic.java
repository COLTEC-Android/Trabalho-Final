package br.ufmg.coltec.tp.database.models.basics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import br.ufmg.coltec.tp.database.models.Ability;

public class AbilityBasic implements Serializable {
    private Ability ability;
    @SerializedName("is_hidden")
    private boolean isHidden;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
