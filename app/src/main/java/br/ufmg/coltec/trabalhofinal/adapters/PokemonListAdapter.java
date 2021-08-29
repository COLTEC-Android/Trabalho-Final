package br.ufmg.coltec.trabalhofinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

import br.ufmg.coltec.tp.database.models.Pokemon;
import br.ufmg.coltec.tp.database.services.PokemonProxy;
import br.ufmg.coltec.trabalhofinal.R;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListHolder> {

    PokemonProxy pokemonProxy = PokemonProxy.getInstance();
    ArrayList<Pokemon> pokemons;
    Context context;
    boolean isGridView;

    public PokemonListAdapter(Context context, ArrayList<Pokemon> pokemons, boolean isGridView) {
        this.context = context;
        this.isGridView = isGridView;
        this.pokemons = pokemons;
    }

    public void setPokemonList( ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

    public boolean toggleItemViewType () {
        isGridView = !isGridView;
        return isGridView;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public PokemonListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(isGridView ? R.layout.fragment_pokemon_grid_tile : R.layout.fragment_pokemon_list_tile, parent, false);

        PokemonListHolder holder = new PokemonListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PokemonListHolder holder, int position) {
        // Definição dos componentes do layout
        Pokemon pokemon = pokemons.get(position);

        holder.name.setText(pokemon.getName());
        holder.id.setText(context.getResources().getString(R.string.text_id, String.format(Locale.getDefault(),"%03d", pokemon.getId())));
        holder.type1.setText(pokemon.getTypes().get(0).getType().getName());
        if (pokemon.getTypes().size() > 1) {
            holder.type2.setText(pokemon.getTypes().get(1).getType().getName());
        } else {
            holder.type2.setVisibility(View.GONE);
        }

        // Baixa a imagem pela url usando o Glide e define no ImageView
        if (pokemon.getSprites().getFrontDefaultUrl() != null) {
            Glide.with(this.context).load(pokemon.getSprites().getFrontDefaultUrl()).into(holder.defaultSprite);
        }

        holder.btnFavorite.setBackgroundTintList(ColorStateList.valueOf(pokemon.isFavorited() ? ContextCompat.getColor(context, R.color.gold) : ContextCompat.getColor(context, R.color.gray)));
        holder.btnFavorite.setOnClickListener(v -> {
            // Salva no sharedPreferences se nao for favorito, se for, tira de lá.
            SharedPreferences sharedPreferences = context.getSharedPreferences("Favorites", 0);
            if(pokemon.isFavorited()){
                sharedPreferences.edit().remove(String.valueOf(pokemon.getId())).apply();
                pokemon.setFavorited(false);
                pokemonProxy.removeFavoritedPokemon(pokemon);
            } else{
                sharedPreferences.edit().putInt(String.valueOf(pokemon.getId()), pokemon.getId()).apply();
                pokemon.setFavorited(true);
                pokemonProxy.addFavoritedPokemon(pokemon);
            }
            holder.btnFavorite.setBackgroundTintList(ColorStateList.valueOf(pokemon.isFavorited() ? ContextCompat.getColor(context, R.color.gold) : ContextCompat.getColor(context, R.color.gray)));
        });

        // Envia o broadcast ao apertar no item da lista
        holder.setItemClickListener((v, p) -> LocalBroadcastManager.getInstance(context)
                .sendBroadcast(new Intent("OpenDetails").putExtra("position", p)));

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    // Busca os componentes do layout e cria um objeto para acessá-las
    public class PokemonListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        TextView name;
        TextView id;
        TextView type1;
        TextView type2;
        ImageView defaultSprite;
        Button btnFavorite;

        public PokemonListHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_poke_name);
            id = itemView.findViewById(R.id.txt_poke_id);
            type1 = itemView.findViewById(R.id.txt_poke_type1);
            type2 = itemView.findViewById(R.id.txt_poke_type2);
            defaultSprite = itemView.findViewById(R.id.img_poke_cover);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}