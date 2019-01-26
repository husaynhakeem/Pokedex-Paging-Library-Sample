package io.husayn.paging_library_sample.listing;


import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.squareup.picasso.Picasso;

import io.husayn.paging_library_sample.R;
import io.husayn.paging_library_sample.data.Pokemon;

import static io.husayn.paging_library_sample.utilities.TextUtility.toThreeDigitNumber;

class PokemonViewHolder extends RecyclerView.ViewHolder {

    private static final String POKE_IMAGE_URL_PREFIX = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private static final String POKE_IMAGE_URL_SUFFIX = ".png";

    private TextView pokemonIdTextView;
    private ImageView pokemonSpriteImageView;
    private TextView pokemonNameTextView;

    PokemonViewHolder(View itemView) {
        super(itemView);
        pokemonIdTextView = (TextView) itemView.findViewById(R.id.tv_pokemon_id);
        pokemonNameTextView = (TextView) itemView.findViewById(R.id.tv_pokemon);
        pokemonSpriteImageView = (ImageView) itemView.findViewById(R.id.iv_pokemon);
    }

    void bindTo(Pokemon pokemon) {
        itemView.setTag(pokemon.id);
        pokemonIdTextView.setText(itemView.getContext().getString(R.string.pokemon_id, toThreeDigitNumber(pokemon.id)));
        pokemonNameTextView.setText(pokemon.name);
        Picasso.with(itemView.getContext())
                .load(pokemonSpriteUrl(pokemon.id))
                .into(pokemonSpriteImageView, picassoPaletteCallBack(pokemonSpriteUrl(pokemon.id)));
    }

    private String pokemonSpriteUrl(int id) {
        return POKE_IMAGE_URL_PREFIX + id + POKE_IMAGE_URL_SUFFIX;
    }

    private PicassoPalette picassoPaletteCallBack(String spriteUrl) {
        return PicassoPalette.with(spriteUrl, pokemonSpriteImageView).use(PicassoPalette.Profile.MUTED).intoBackground(itemView);
    }

    void clear() {
        itemView.invalidate();
        pokemonIdTextView.invalidate();
        pokemonNameTextView.invalidate();
        pokemonSpriteImageView.invalidate();
    }
}
