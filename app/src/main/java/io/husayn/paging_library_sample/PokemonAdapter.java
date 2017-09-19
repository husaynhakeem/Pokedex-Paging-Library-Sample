package io.husayn.paging_library_sample;


import android.arch.paging.PagedListAdapter;
import android.view.ViewGroup;

class PokemonAdapter extends PagedListAdapter<Pokemon, PokemonViewHolder> {


    public PokemonAdapter() {
        super(Pokemon.DIFF_CALLBACK);
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
