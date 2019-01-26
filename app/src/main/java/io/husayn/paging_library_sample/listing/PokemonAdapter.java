package io.husayn.paging_library_sample.listing;


import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.husayn.paging_library_sample.R;
import io.husayn.paging_library_sample.data.Pokemon;

class PokemonAdapter extends PagedListAdapter<Pokemon, PokemonViewHolder> {


    PokemonAdapter() {
        super(Pokemon.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);

        if (pokemon != null) {
            holder.bindTo(pokemon);
        } else {
            holder.clear();
        }
    }
}
