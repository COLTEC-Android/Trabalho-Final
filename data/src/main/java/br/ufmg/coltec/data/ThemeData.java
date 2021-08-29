package br.ufmg.coltec.data;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeData {

    private static final String THEME_PREF_ID = "Theme";
    private Context context;
    private SharedPreferences preferences;

    public ThemeData(Context context){
        this.preferences = context.getSharedPreferences(THEME_PREF_ID, 0);
        this.context = context;
    }

    public void setTheme(){
        if(getTheme()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("themeKey", false);
            editor.commit();
        }
        else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("themeKey", true);
            editor.commit();
        }
    }

    public boolean getTheme(){
        SharedPreferences pref = this.context.getSharedPreferences(THEME_PREF_ID, 0);
        return pref.getBoolean("themeKey", true);
    }
}
