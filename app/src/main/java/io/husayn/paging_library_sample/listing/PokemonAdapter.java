package io.husayn.paging_library_sample.listing;


import android.arch.paging.PagedListAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.husayn.paging_library_sample.R;
import io.husayn.paging_library_sample.data.Pokemon;

import static io.husayn.paging_library_sample.PokemonApplication.getContext;

class PokemonAdapter extends PagedListAdapter<Pokemon, PokemonViewHolder> {


    public PokemonAdapter() {
        super(Pokemon.DIFF_CALLBACK);
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);

        if (pokemon != null) {
            holder.bindTo(pokemon);
        } else {
            holder.clear();
        }
    }
}
