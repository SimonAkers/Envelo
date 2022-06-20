package io.github.simonakers.envelo.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    public static final int TYPE_ENVELOPE = 0;
    public static final int TYPE_ACCOUNT = 1;

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

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}