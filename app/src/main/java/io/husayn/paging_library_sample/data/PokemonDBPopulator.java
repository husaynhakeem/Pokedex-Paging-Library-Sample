package io.husayn.paging_library_sample.data;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PokemonDBPopulator {

    private static final String TAG = PokemonDBPopulator.class.getName();
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

    private Context context;

    public PokemonDBPopulator(Context context) {
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
        for (int i = 0; i < POKEMON_DATA.length; i++) {
            pokemons.add(new Pokemon(i + 1, POKEMON_DATA[i]));
        }
        return pokemons.toArray(new Pokemon[pokemons.size()]);
    }

    private void onDBPopulationSuccess() {
        Log.d(TAG, "Pokemons inserted successfully");
    }

    private void onDBPopulationFailure(Throwable t) {
        Log.e(TAG, "Pokemons failed to be inserted, error: " + t.getMessage());
    }
}
