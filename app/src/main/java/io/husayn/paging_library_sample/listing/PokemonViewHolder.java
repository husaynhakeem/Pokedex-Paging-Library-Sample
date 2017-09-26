package io.husayn.paging_library_sample.listing;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.husayn.paging_library_sample.R;
import io.husayn.paging_library_sample.data.Pokemon;

class PokemonViewHolder extends RecyclerView.ViewHolder {

    private TextView pokemonIdTextView;
    private ImageView pokemonSpriteImageView;
    private TextView pokemonNameTextView;

    public PokemonViewHolder(View itemView) {
        super(itemView);

        pokemonSpriteImageView = (ImageView) itemView.findViewById(R.id.iv_pokemon);
        pokemonNameTextView = (TextView) itemView.findViewById(R.id.tv_pokemon);
    }

    public void bindTo(Pokemon pokemon) {
        itemView.setTag(pokemon.id);
        pokemonIdTextView.setText(itemView.getContext().getString(R.id.pokemon_id, pokemon.id));
        pokemonNameTextView.setText(pokemon.name);
    }

    public void clear() {
        itemView.invalidate();
        pokemonNameTextView.invalidate();
        pokemonSpriteImageView.invalidate();
    }
}
