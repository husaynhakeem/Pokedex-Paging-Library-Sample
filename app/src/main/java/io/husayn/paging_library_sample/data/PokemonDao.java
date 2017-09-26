package io.husayn.paging_library_sample.data;

import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.VisibleForTesting;

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
