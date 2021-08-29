package br.ufmg.coltec.trabalhofinal.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDelegate;

import br.ufmg.coltec.trabalhofinal.R;

public class MainActivity extends AppCompatActivity {
    // Cuida do tema
    boolean isNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fica em modo paisagem se for tablet
        if(getResources().getBoolean(R.bool.isTablet)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        // Carrega o tema escolhido pelo usuário, salvo com o SharedPreferences
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
        isNightMode = sharedPreferences.getBoolean("NightMode", false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Inicialização da actionBar
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btnPokemonList = findViewById(R.id.btn_pokemon_list);
        btnPokemonList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PokemonListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();

        // Definições das opções do menu da actionBar
        // A action theme_change altera o tema entre night mode e light mode, e salva a escolha no SharedPreferences
        if (itemID == R.id.action_theme_change) {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
            if (isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sharedPreferences.edit().putBoolean("NightMode", false).apply();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sharedPreferences.edit().putBoolean("NightMode", true).apply();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Muda o ícone do botão de mudar o tema com base no tema escolhido
        MenuItem themeItem = menu.findItem(R.id.action_theme_change);
        themeItem.setIcon(isNightMode ? R.drawable.outline_wb_sunny_24 : R.drawable.outline_dark_mode_24);
        return super.onCreateOptionsMenu(menu);
    }
}