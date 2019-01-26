package io.husayn.paging_library_sample.data;

import androidx.paging.LivePagedListProvider;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.annotation.VisibleForTesting;

import static java.lang.reflect.Modifier.PRIVATE;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    LivePagedListProvider<Integer, Pokemon> pokemons();

    @VisibleForTesting(otherwise = PRIVATE)
    @Query("SELECT COUNT(*) FROM pokemon")
    Integer pokemonsCount();

    @Insert
    void insert(Pokemon... pokemons);

    @VisibleForTesting(otherwise = PRIVATE)
    @Query("DELETE FROM pokemon")
    void deleteAll();
}
