package io.husayn.paging_library_sample;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class PokemonViewHolder extends RecyclerView.ViewHolder {

    ImageView pokemonSpriteImageView;
    TextView pokemonNameTextView;

    public PokemonViewHolder(View itemView) {
        super(itemView);

        pokemonSpriteImageView = (ImageView) itemView.findViewById(R.id.iv_pokemon);
        pokemonNameTextView = (TextView) itemView.findViewById(R.id.tv_pokemon);
    }

    public void bindTo(Pokemon pokemon) {
        itemView.setTag(pokemon.id);
        pokemonNameTextView.setText(pokemon.name);
    }

    public void clear() {
        itemView.invalidate();
        pokemonNameTextView.invalidate();
        pokemonSpriteImageView.invalidate();
    }
}
