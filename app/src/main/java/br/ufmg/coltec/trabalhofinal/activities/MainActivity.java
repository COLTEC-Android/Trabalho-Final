package br.ufmg.coltec.trabalhofinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.ufmg.coltec.trabalhofinal.R;

public class MainActivity extends AppCompatActivity {

    private static final String APP_PREF_THEME_ID = "SP_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = this.getSharedPreferences(APP_PREF_THEME_ID, 0);
        boolean theme = preferences.getBoolean("theme", true);
        if (theme){
            this.setTheme(R.style.Theme_TrabalhoFinal);
        } else {
            this.setTheme(R.style.Theme_TrabalhoFinalGold);
        }
        setContentView(R.layout.activity_main);

        Button btnThemePurple = findViewById(R.id.btn_theme_purple);
        btnThemePurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(APP_PREF_THEME_ID, 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("theme", true);
                editor.commit();

                MainActivity.this.finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        Button btnThemeGold = findViewById(R.id.btn_theme_gold);
        btnThemeGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(APP_PREF_THEME_ID, 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("theme", false);
                editor.commit();

                MainActivity.this.finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        Button btnMain = findViewById(R.id.btn_main);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

}