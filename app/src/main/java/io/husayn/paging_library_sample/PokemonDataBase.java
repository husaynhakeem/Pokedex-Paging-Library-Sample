package io.husayn.paging_library_sample;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokemonDataBase extends RoomDatabase {

    private static final String TAG = PokemonDataBase.class.getSimpleName();
    public static final String POKEMON_DB = "pokemon.db";
    private static final String[] POKEMON_DATA = {"\"Bulbasaur\", \"Ivysaur\", \"Venusaur\", \"Charmander\", \"Charmeleon\", \"Charizard\", \"Squirtle\", \"Wartortle\", " +
            "\"Blastoise\", \"Caterpie\", \"Metapod\", \"Butterfree\", \"Weedle\", \"Kakuna\", \"Beedrill\", \"Pidgey\", \"Pidgeotto\", \"Pidgeot\", \"Rattata\", " +
            "\"Raticate\", \"Spearow\", \"Fearow\", \"Ekans\", \"Arbok\", \"Pikachu\", \"Raichu\", \"Sandshrew\", \"Sandslash\", \"Nidoran♀\", \"Nidorina\", " +
            "\"Nidoqueen\", \"Nidoran♂\", \"Nidorino\", \"Nidoking\", \"Clefairy\", \"Clefable\", \"Vulpix\", \"Ninetales\", \"Jigglypuff\", \"Wigglytuff\", " +
            "\"Zubat\", \"Golbat\", \"Oddish\", \"Gloom\", \"Vileplume\", \"Paras\", \"Parasect\", \"Venonat\", \"Venomoth\", \"Diglett\", \"Dugtrio\", \"Meowth\", " +
            "\"Persian\", \"Psyduck\", \"Golduck\", \"Mankey\", \"Primeape\", \"Growlithe\", \"Arcanine\", \"Poliwag\", \"Poliwhirl\", \"Poliwrath\", \"Abra\", " +
            "\"Kadabra\", \"Alakazam\", \"Machop\", \"Machoke\", \"Machamp\", \"Bellsprout\", \"Weepinbell\", \"Victreebel\", \"Tentacool\", \"Tentacruel\", \"Geodude\", " +
            "\"Graveler\", \"Golem\", \"Ponyta\", \"Rapidash\", \"Slowpoke\", \"Slowbro\", \"Magnemite\", \"Magneton\", \"Farfetch'd\", \"Doduo\", \"Dodrio\", \"Seel\", " +
            "\"Dewgong\", \"Grimer\", \"Muk\", \"Shellder\", \"Cloyster\", \"Gastly\", \"Haunter\", \"Gengar\", \"Onix\", \"Drowzee\", \"Hypno\", \"Krabby\", \"Kingler\", " +
            "\"Voltorb\", \"Electrode\", \"Exeggcute\", \"Exeggutor\", \"Cubone\", \"Marowak\", \"Hitmonlee\", \"Hitmonchan\", \"Lickitung\", \"Koffing\", \"Weezing\", " +
            "\"Rhyhorn\", \"Rhydon\", \"Chansey\", \"Tangela\", \"Kangaskhan\", \"Horsea\", \"Seadra\", \"Goldeen\", \"Seaking\", \"Staryu\", \"Starmie\", \"Mr.\", \"Mime\", " +
            "\"Scyther\", \"Jynx\", \"Electabuzz\", \"Magmar\", \"Pinsir\", \"Tauros\", \"Magikarp\", \"Gyarados\", \"Lapras\", \"Ditto\", \"Eevee\", \"Vaporeon\", \"Jolteon\", " +
            "\"Flareon\", \"Porygon\", \"Omanyte\", \"Omastar\", \"Kabuto\", \"Kabutops\", \"Aerodactyl\", \"Snorlax\", \"Articuno\", \"Zapdos\", \"Moltres\", \"Dratini\", " +
            "\"Dragonair\", \"Dragonite\", \"Mewtwo\", \"Mew\""};

    public abstract PokemonDao pokemonDao();

    private static PokemonDataBase pokemonDB;

    public static PokemonDataBase getInstance(Context context) {
        if (pokemonDB == null) {

            Callback cb = new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    fillDB(context);
                }

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };

            pokemonDB = Room.databaseBuilder(context.getApplicationContext(), PokemonDataBase.class,
                    POKEMON_DB)
                    .addCallback(cb)
                    .build();
        }
        return pokemonDB;
    }

    private static void fillDB(Context context) {
        Completable.fromAction(() -> getInstance(context).pokemonDao().insert(pokemonList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "Pokemons inserted successfully"), t -> Log.e(TAG,
                        "Pokemons failed to be inserted, error: " + t.getMessage()));
    }

    private static Pokemon[] pokemonList() {
        List<Pokemon> pokemons = new ArrayList<>();
        int index = 0;
        for (String pokemon : POKEMON_DATA) {
            pokemons.add(new Pokemon(index++, pokemon));
        }
        return pokemons.toArray(new Pokemon[pokemons.size()]);
    }
}
