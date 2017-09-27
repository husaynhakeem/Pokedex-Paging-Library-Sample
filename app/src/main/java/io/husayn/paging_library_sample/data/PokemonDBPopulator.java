package io.husayn.paging_library_sample.data;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.husayn.paging_library_sample.R;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PokemonDBPopulator {

    private static final String TAG = PokemonDBPopulator.class.getName();

    private Context context;

    private PokemonDBPopulator(Context context) {
        this.context = context;
    }

    public static PokemonDBPopulator with(Context context) {
        return new PokemonDBPopulator(context);
    }

    public void populateDB() {
        Completable.fromAction(() -> PokemonDataBase.getInstance(context).pokemonDao().insert(pokemonList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDBPopulationSuccess, this::onDBPopulationFailure);
    }

    private Pokemon[] pokemonList() {
        List<Pokemon> pokemons = new ArrayList<>();
        String[] pokemonNames = context.getResources().getStringArray(R.array.pokemon_names);
        for (int i = 0; i < pokemonNames.length; i++)
            pokemons.add(new Pokemon(i + 1, pokemonNames[i]));
        return pokemons.toArray(new Pokemon[pokemons.size()]);
    }

    private void onDBPopulationSuccess() {
        Log.d(TAG, "Pokemons inserted successfully");
    }

    private void onDBPopulationFailure(Throwable t) {
        Log.e(TAG, "Pokemons failed to be inserted, error:" + t.getMessage());
    }
}
