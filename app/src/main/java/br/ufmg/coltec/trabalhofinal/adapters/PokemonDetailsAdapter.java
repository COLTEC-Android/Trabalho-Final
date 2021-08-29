package br.ufmg.coltec.trabalhofinal.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import br.ufmg.coltec.tp.database.models.Pokemon;
import br.ufmg.coltec.tp.database.services.PokemonProxy;
import br.ufmg.coltec.trabalhofinal.R;

public class PokemonDetailsAdapter extends RecyclerView.Adapter<PokemonDetailsAdapter.PokemonDetailsHolder> {

    PokemonProxy pokemonProxy = PokemonProxy.getInstance();
    Pokemon pokemon;
    Context context;

    public PokemonDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public PokemonDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_pokemon_details, parent, false);

        return new PokemonDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonDetailsHolder holder, int position) {
        // Definição dos componentes do layout
        if(pokemon != null){
            // TODO: construir uma maneira de mostrar todos os sprites
            if (pokemon.getSprites().getFrontDefaultUrl() != null) {
                Glide.with(context).load(pokemon.getSprites().getFrontDefaultUrl()).into(holder.defaultSprite);
            }

            holder.name.setText(pokemon.getName());
            holder.id.setText(context.getResources().getString(R.string.text_id, String.format(Locale.getDefault(), "%03d", pokemon.getId())));
            holder.type1.setText(pokemon.getTypes().get(0).getType().getName());
            if (pokemon.getTypes().size() > 1) {
                holder.type2.setText(pokemon.getTypes().get(1).getType().getName());
            } else {
                holder.type2.setVisibility(View.GONE);
            }

            holder.height.setText(context.getResources().getString(R.string.text_height, String.format(Locale.getDefault(), "%.1f", ((double) pokemon.getHeight()) / 10)));
            holder.weight.setText(context.getResources().getString(R.string.text_weight, String.format(Locale.getDefault(), "%.1f", ((double) pokemon.getWeight()) / 10)));
            holder.ability0.setText(context.getResources().getString(R.string.text_h, pokemon.getAbilities().get(0).getAbility().getName()));

            if (pokemon.getAbilities().get(1).isHidden()) {
                holder.ability1.setText(context.getResources().getString(R.string.text_ha, pokemon.getAbilities().get(1).getAbility().getName()));
            } else {
                holder.ability1.setText(context.getResources().getString(R.string.text_h, pokemon.getAbilities().get(1).getAbility().getName()));
            }
            if (pokemon.getAbilities().size() > 2) {
                holder.ability2.setText(context.getResources().getString(R.string.text_ha, pokemon.getAbilities().get(2).getAbility().getName()));
            } else {
                holder.ability2.setVisibility(View.GONE);
            }

            holder.statHp.setText(String.valueOf(pokemon.getStats().get(0).getStatValue()));
            holder.statAtk.setText(String.valueOf(pokemon.getStats().get(1).getStatValue()));
            holder.statDef.setText(String.valueOf(pokemon.getStats().get(2).getStatValue()));
            holder.statSpAtk.setText(String.valueOf(pokemon.getStats().get(3).getStatValue()));
            holder.statSpDef.setText(String.valueOf(pokemon.getStats().get(4).getStatValue()));
            holder.statSpeed.setText(String.valueOf(pokemon.getStats().get(5).getStatValue()));

            holder.btnFavorite.setBackgroundTintList(ColorStateList.valueOf(pokemon.isFavorited() ? context.getResources().getColor(R.color.gold) : context.getResources().getColor(R.color.gray)));
            holder.btnFavorite.setOnClickListener(v -> {
                // Salva no sharedPreferences se nao for favorito, se for, tira de lá.
                SharedPreferences sharedPreferences = context.getSharedPreferences("Favorites", 0);
                if (pokemon.isFavorited()) {
                    sharedPreferences.edit().remove(String.valueOf(pokemon.getId())).apply();
                    pokemon.setFavorited(false);
                    pokemonProxy.removeFavoritedPokemon(pokemon);
                } else {
                    sharedPreferences.edit().putInt(String.valueOf(pokemon.getId()), pokemon.getId()).apply();
                    pokemon.setFavorited(true);
                    pokemonProxy.addFavoritedPokemon(pokemon);
                }
                holder.btnFavorite.setBackgroundTintList(ColorStateList.valueOf(pokemon.isFavorited() ? context.getResources().getColor(R.color.gold) : context.getResources().getColor(R.color.gray)));
            });
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    // Busca os componentes do layout e cria um objeto para acessá-las
    public class PokemonDetailsHolder extends RecyclerView.ViewHolder {

        Button btnFavorite;
        ImageView defaultSprite;
        TextView name;
        TextView id;
        TextView type1;
        TextView type2;
        TextView height;
        TextView weight;
        TextView ability0;
        TextView ability1;
        TextView ability2;
        TextView statHp;
        TextView statAtk;
        TextView statDef;
        TextView statSpAtk;
        TextView statSpDef;
        TextView statSpeed;

        public PokemonDetailsHolder(View itemView) {
            super(itemView);

            btnFavorite = itemView.findViewById(R.id.btn_favorite);
            defaultSprite = itemView.findViewById(R.id.img_poke_cover);
            name = itemView.findViewById(R.id.txt_poke_name);
            id = itemView.findViewById(R.id.txt_poke_id);
            type1 = itemView.findViewById(R.id.txt_poke_type1);
            type2 = itemView.findViewById(R.id.txt_poke_type2);
            height = itemView.findViewById(R.id.txt_poke_height);
            weight = itemView.findViewById(R.id.txt_poke_weight);
            ability0 = itemView.findViewById(R.id.txt_poke_ability0);
            ability1 = itemView.findViewById(R.id.txt_poke_ability1);
            ability2 = itemView.findViewById(R.id.txt_poke_ability2);
            statHp = itemView.findViewById(R.id.txt_poke_hp);
            statAtk = itemView.findViewById(R.id.txt_poke_atk);
            statDef = itemView.findViewById(R.id.txt_poke_def);
            statSpAtk = itemView.findViewById(R.id.txt_poke_spatk);
            statSpDef = itemView.findViewById(R.id.txt_poke_spdef);
            statSpeed = itemView.findViewById(R.id.txt_poke_speed);
        }
    }
}