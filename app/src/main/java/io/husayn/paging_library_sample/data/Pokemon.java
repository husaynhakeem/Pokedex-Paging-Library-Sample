package io.husayn.paging_library_sample.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffCallback;

@Entity
public class Pokemon {

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
