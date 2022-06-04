package io.github.simonakers.envelo.controller;

import android.app.Application;

import androidx.room.Room;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import io.github.simonakers.envelo.database.BudgetDatabase;

public class App extends Application {
    GoogleSignInClient signInClient;
    BudgetDatabase budget;

    @Override
    public void onCreate() {
        super.onCreate();

        budget = Room.databaseBuilder(this, BudgetDatabase.class, "budget").build();
    }

    public GoogleSignInClient getSignInClient() {
        return signInClient;
    }

    public void setSignInClient(GoogleSignInClient signInClient) {
        this.signInClient = signInClient;
    }

    public BudgetDatabase getBudget() {
        return budget;
    }
}
