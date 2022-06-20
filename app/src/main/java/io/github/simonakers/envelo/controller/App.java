package io.github.simonakers.envelo.controller;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import androidx.room.Room;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import io.github.simonakers.envelo.database.BudgetDatabase;

public class App extends Application {
    GoogleSignInClient signInClient;
    BudgetDatabase budget;
    SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();

        budget = Room.databaseBuilder(this, BudgetDatabase.class, "budget")
            .allowMainThreadQueries() // TODO: Put queries on own thread (use LiveData?)
            .build();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
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

    /**
     * Gets the app's default shared preferences.
     *
     * @return the app's default shared preferences
     */
    public SharedPreferences prefs() {
        return prefs;
    }
}
