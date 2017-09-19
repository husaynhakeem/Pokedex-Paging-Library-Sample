package io.husayn.paging_library_sample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

@Entity
class Pokemon {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    public Pokemon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static final DiffCallback<Pokemon> DIFF_CALLBACK = new DiffCallback<Pokemon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldPokemon, @NonNull Pokemon newPokemon) {
            return oldPokemon.id == newPokemon.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldPokemon, @NonNull Pokemon newPokemon) {
            return oldPokemon.name.equals(newPokemon.name);
        }
    };
}
