package br.ufmg.coltec.trabalhofinal.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import br.ufmg.coltec.tp.database.models.Pokemon;
import br.ufmg.coltec.tp.database.services.PokemonProxy;
import br.ufmg.coltec.trabalhofinal.R;

public class PokemonDetailsActivity extends AppCompatActivity {
    // Cuida do tema
    boolean isNightMode;

    // Carrega as preferências do usuário
    private SharedPreferences sharedPreferences;

    PokemonProxy pokemonProxy = PokemonProxy.getInstance();
    Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        // Recebe o pokemon selecionado na lista
        Intent intent = getIntent();
        pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        // Carrega o tema escolhido pelo usuário, salvo com o SharedPreferences
        sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
        isNightMode = sharedPreferences.getBoolean("NightMode", false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Inicialização da actionBar e definicão do título como o nome do pokemon selecionado na lista
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(pokemon.getName());

        // Inicialização e definição dos itens do layout
        Button btnFavorite = findViewById(R.id.btn_favorite);
        ImageView defaultSprite = findViewById(R.id.img_poke_cover);
        TextView name = findViewById(R.id.txt_poke_name);
        TextView id = findViewById(R.id.txt_poke_id);
        TextView type1 = findViewById(R.id.txt_poke_type1);
        TextView type2 = findViewById(R.id.txt_poke_type2);
        TextView height = findViewById(R.id.txt_poke_height);
        TextView weight = findViewById(R.id.txt_poke_weight);
        TextView ability0 = findViewById(R.id.txt_poke_ability0);
        TextView ability1 = findViewById(R.id.txt_poke_ability1);
        TextView ability2 = findViewById(R.id.txt_poke_ability2);
        TextView statHp = findViewById(R.id.txt_poke_hp);
        TextView statAtk = findViewById(R.id.txt_poke_atk);
        TextView statDef = findViewById(R.id.txt_poke_def);
        TextView statSpAtk = findViewById(R.id.txt_poke_spatk);
        TextView statSpDef = findViewById(R.id.txt_poke_spdef);
        TextView statSpeed = findViewById(R.id.txt_poke_speed);

        // TODO: construir uma maneira de mostrar todos os sprites
        if (pokemon.getSprites().getFrontDefaultUrl() != null) {
            Glide.with(this).load(pokemon.getSprites().getFrontDefaultUrl()).into(defaultSprite);
        }

        name.setText(pokemon.getName());
        id.setText(getString(R.string.text_id, String.format(Locale.getDefault(), "%03d", pokemon.getId())));
        type1.setText(pokemon.getTypes().get(0).getType().getName());
        if (pokemon.getTypes().size() > 1) {
            type2.setText(pokemon.getTypes().get(1).getType().getName());
        } else {
            type2.setVisibility(View.GONE);
        }

        height.setText(getString(R.string.text_height, String.format(Locale.getDefault(), "%.1f", ((double) pokemon.getHeight()) / 10)));
        weight.setText(getString(R.string.text_weight, String.format(Locale.getDefault(), "%.1f", ((double) pokemon.getWeight()) / 10)));
        ability0.setText(getString(R.string.text_h, pokemon.getAbilities().get(0).getAbility().getName()));

        if (pokemon.getAbilities().get(1).isHidden()) {
            ability1.setText(getString(R.string.text_ha, pokemon.getAbilities().get(1).getAbility().getName()));
        } else {
            ability1.setText(getString(R.string.text_h, pokemon.getAbilities().get(1).getAbility().getName()));
        }
        if (pokemon.getAbilities().size() > 2) {
            ability2.setText(getString(R.string.text_ha, pokemon.getAbilities().get(2).getAbility().getName()));
        } else {
            ability2.setVisibility(View.GONE);
        }

        statHp.setText(String.valueOf(pokemon.getStats().get(0).getStatValue()));
        statAtk.setText(String.valueOf(pokemon.getStats().get(1).getStatValue()));
        statDef.setText(String.valueOf(pokemon.getStats().get(2).getStatValue()));
        statSpAtk.setText(String.valueOf(pokemon.getStats().get(3).getStatValue()));
        statSpDef.setText(String.valueOf(pokemon.getStats().get(4).getStatValue()));
        statSpeed.setText(String.valueOf(pokemon.getStats().get(5).getStatValue()));

        btnFavorite.setBackgroundTintList(ColorStateList.valueOf(pokemon.isFavorited() ? getResources().getColor(R.color.gold) : getResources().getColor(R.color.gray)));
        btnFavorite.setOnClickListener(v -> {
            // Salva no sharedPreferences se nao for favorito, se for, tira de lá.
            SharedPreferences sharedPreferences = getSharedPreferences("Favorites", 0);
            if (pokemon.isFavorited()) {
                sharedPreferences.edit().remove(String.valueOf(pokemon.getId())).apply();
                pokemon.setFavorited(false);
                pokemonProxy.removeFavoritedPokemon(pokemon);
            } else {
                sharedPreferences.edit().putInt(String.valueOf(pokemon.getId()), pokemon.getId()).apply();
                pokemon.setFavorited(true);
                pokemonProxy.addFavoritedPokemon(pokemon);
            }
            btnFavorite.setBackgroundTintList(ColorStateList.valueOf(pokemon.isFavorited() ? getResources().getColor(R.color.gold) : getResources().getColor(R.color.gray)));
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();

        // Definições das opções do menu da actionBar
        // A action theme_change altera o tema entre night mode e light mode, e salva a escolha no SharedPreferences
        switch (itemID) {
            case R.id.action_theme_change:
                sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
                if (isNightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferences.edit().putBoolean("NightMode", false).apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferences.edit().putBoolean("NightMode", true).apply();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_details, menu);

        // Muda o ícone do botão de mudar o tema com base no tema escolhido
        MenuItem themeItem = menu.findItem(R.id.action_theme_change);
        themeItem.setIcon(isNightMode ? R.drawable.outline_wb_sunny_24 : R.drawable.outline_dark_mode_24);

        return super.onCreateOptionsMenu(menu);
    }
}