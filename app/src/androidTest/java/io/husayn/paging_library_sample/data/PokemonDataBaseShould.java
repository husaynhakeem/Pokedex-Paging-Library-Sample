package io.husayn.paging_library_sample.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by husaynhakeem on 9/26/17.
 */
public class PokemonDataBaseShould {

    private Context context = InstrumentationRegistry.getTargetContext();
    private PokemonDao dao;

    @Before
    public void setUp() throws Exception {
        dao = PokemonDataBase.getInstance(context).pokemonDao();
        dao.deleteAll();
    }

    @Test
    public void returnZero_whenDatabaseIsEmpty() throws Exception {
        int dbSize = dao.pokemonsCount();
        assertEquals(0, dbSize);
    }

    @Test
    public void returnCorrectSize_whenDatabaseIsFull() throws Exception {
        int dbSize = dao.pokemonsCount();
        assertEquals(0, dbSize);
    }

    @After
    public void tearDown() throws Exception {
        if (dao != null)
            dao.deleteAll();
    }
}