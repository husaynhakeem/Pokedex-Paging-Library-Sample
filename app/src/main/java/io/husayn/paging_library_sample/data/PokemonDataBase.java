package io.husayn.paging_library_sample.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokemonDataBase extends RoomDatabase {

    private static final String POKEMON_DB = "pokemon.db";

    public abstract PokemonDao pokemonDao();

    private static PokemonDataBase pokemonDB;

    public static PokemonDataBase getInstance(Context context) {
        if (pokemonDB == null) {
            pokemonDB = Room.databaseBuilder(context.getApplicationContext(), PokemonDataBase.class, POKEMON_DB).build();
        }
        return pokemonDB;
    }
}
