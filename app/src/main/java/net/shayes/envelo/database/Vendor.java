package net.shayes.envelo.database;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Vendor {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public Vendor(@NonNull String name) {
        this.name = name;
    }
}