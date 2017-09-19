package io.husayn.paging_library_sample;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.husayn.paging_library_sample.data.PokemonDBPopulator;


public class PokemonApplication extends Application {

    public static final String KEY_IS_DB_POPULATED = "DB_IS_POPULATED";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        intiDB();
    }

    private void initContext() {
        context = this;
    }

    private void intiDB() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferences.getBoolean(KEY_IS_DB_POPULATED, false)) {
            PokemonDBPopulator.with(this).populateDB();
            preferences.edit().putBoolean(KEY_IS_DB_POPULATED, true).apply();
        }
    }

    public static Context getContext() {
        return context;
    }
}
