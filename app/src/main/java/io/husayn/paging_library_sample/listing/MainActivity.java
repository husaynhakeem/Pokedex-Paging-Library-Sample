package io.husayn.paging_library_sample.listing;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.husayn.paging_library_sample.R;

public class MainActivity extends AppCompatActivity {

    public static final int SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final PokemonAdapter adapter = new PokemonAdapter();
        viewModel.pokemonList.observe(this, adapter::setList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        recyclerView.setAdapter(adapter);
    }
}
