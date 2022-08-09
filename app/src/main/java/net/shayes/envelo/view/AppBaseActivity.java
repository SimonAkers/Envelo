package net.shayes.envelo.view;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.shayes.envelo.controller.App;

/**
 * A base class extending AppCompatActivity, providing some common functionality between activities.
 */
public class AppBaseActivity extends AppCompatActivity {
    protected App app;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        app = (App) getApplication();
    }
}
