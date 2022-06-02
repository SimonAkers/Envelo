package io.github.simonakers.envelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set status bar color
        TypedValue value = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
        getWindow().setStatusBarColor(value.data);
    }
}