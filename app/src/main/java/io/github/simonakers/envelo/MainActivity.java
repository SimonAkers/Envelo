package io.github.simonakers.envelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set status and nav bar color
        int color = getResources().getColor(R.color.dk_toolbar);
        getWindow().setStatusBarColor(color);
        getWindow().setNavigationBarColor(color);
    }
}