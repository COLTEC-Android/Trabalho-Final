package br.ufmg.coltec.tp.database.models.basics;

import java.io.Serializable;

import br.ufmg.coltec.tp.database.models.Type;

public class TypeBasic implements Serializable {
    private int slot;
    private Type type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
