package io.husayn.paging_library_sample.listing;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.husayn.paging_library_sample.data.Pokemon;
import io.husayn.paging_library_sample.data.PokemonDao;
import io.husayn.paging_library_sample.data.PokemonDataBase;

public class MainViewModel extends AndroidViewModel {

    private static final int INITIAL_LOAD_KEY = 0;
    private static final int PAGE_SIZE = 20;
    private static final int PREFETCH_DISTANCE = 5;

    final LiveData<PagedList<Pokemon>> pokemonList;

    public MainViewModel(Application application) {
        super(application);
        PokemonDao pokemonDao = PokemonDataBase.getInstance(application).pokemonDao();
        DataSource.Factory<Integer, Pokemon> dataSourceFactory = pokemonDao.pokemons();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PREFETCH_DISTANCE)
                .build();
        pokemonList = new LivePagedListBuilder<>(dataSourceFactory, config).build();
    }
}
