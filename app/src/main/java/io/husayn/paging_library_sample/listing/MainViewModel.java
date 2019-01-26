package io.husayn.paging_library_sample.listing;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import io.husayn.paging_library_sample.PokemonApplication;
import io.husayn.paging_library_sample.data.Pokemon;
import io.husayn.paging_library_sample.data.PokemonDao;
import io.husayn.paging_library_sample.data.PokemonDataBase;

public class MainViewModel extends ViewModel {

    private static final int INITIAL_LOAD_KEY = 0;
    private static final int PAGE_SIZE = 20;
    private static final int PREFETCH_DISTANCE = 5;

    final LiveData<PagedList<Pokemon>> pokemonList;

    public MainViewModel() {
        PokemonDao pokemonDao = PokemonDataBase.getInstance(PokemonApplication.getContext()).pokemonDao();
        pokemonList = pokemonDao.pokemons().create(INITIAL_LOAD_KEY, new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PREFETCH_DISTANCE)
                .setEnablePlaceholders(true)
                .build()
        );
    }
}
