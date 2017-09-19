package io.husayn.paging_library_sample;

import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    LivePagedListProvider<Integer, Pokemon> pokemons();


    @Insert
    void insert(Pokemon... pokemons);
}
