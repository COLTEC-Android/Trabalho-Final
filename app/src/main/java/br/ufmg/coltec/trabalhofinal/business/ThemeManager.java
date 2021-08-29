package br.ufmg.coltec.trabalhofinal.business;

import android.content.Context;

import br.ufmg.coltec.data.ThemeData;
import br.ufmg.coltec.trabalhofinal.R;

public class ThemeManager {

    private int currentTheme;
    private ThemeData themeData;
    private Context context;

    public ThemeManager(Context context){
        this.context = context;
        themeData = new ThemeData(context);
    }

    public void setCurrentTheme(){
        themeData.setTheme();
    }

    public int getCurrentTheme(){
        if(themeData.getTheme()){
            return R.style.Theme_TrabalhoFinal;
        }else{
            return R.style.Theme_TrabalhoFinalDark;
        }
    }

}
