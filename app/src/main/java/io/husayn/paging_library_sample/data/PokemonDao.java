package io.husayn.paging_library_sample.data;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.annotation.VisibleForTesting;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    DataSource.Factory<Integer, Pokemon> pokemons();

    @VisibleForTesting
    @Query("SELECT COUNT(*) FROM pokemon")
    Integer pokemonsCount();

    @Insert
    void insert(Pokemon... pokemons);

    @VisibleForTesting
    @Query("DELETE FROM pokemon")
    void deleteAll();
}
