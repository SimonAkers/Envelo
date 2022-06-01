package io.github.simonakers.envelo.controller;

import android.app.Application;

import androidx.room.Room;

import io.github.simonakers.envelo.database.BudgetDatabase;

public class App extends Application {
    BudgetDatabase budget;

    @Override
    public void onCreate() {
        super.onCreate();

        budget = Room.databaseBuilder(this, BudgetDatabase.class, "budget").build();
    }

    public BudgetDatabase getBudget() {
        return budget;
    }
}
