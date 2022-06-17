package io.github.simonakers.envelo.database;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Category {
    @PrimaryKey
    @NonNull
    public String name;

    public int type;

    public float amount;

    public Category(@NonNull String name, int type, float amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }
}