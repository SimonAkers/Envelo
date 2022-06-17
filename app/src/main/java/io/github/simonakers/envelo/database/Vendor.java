package io.github.simonakers.envelo.database;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Vendor {
    @PrimaryKey
    @NonNull
    public String name;

    public Vendor(@NonNull String name) {
        this.name = name;
    }
}