package net.shayes.envelo.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    public static final int TYPE_ENVELOPE = 0;
    public static final int TYPE_ACCOUNT = 1;

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public int type;

    public float amount;

    public Category(String name, int type, float amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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