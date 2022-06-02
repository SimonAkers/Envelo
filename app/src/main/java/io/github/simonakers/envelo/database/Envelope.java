package io.github.simonakers.envelo.database;

import androidx.room.*;

@Entity
public class Envelope {
    @PrimaryKey
    public String name;

    public float amount;

    public Envelope(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }
}