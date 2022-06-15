package io.github.simonakers.envelo.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import io.github.simonakers.envelo.R;
import io.github.simonakers.envelo.view.custom.NumberKeypad;

public class TransactionActivity extends AppCompatActivity {
    EditText edtAmount;
    NumberKeypad keypad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        setupToolbar();
        setupKeypad();
    }

    /**
     * Sets up the toolbar.
     */
    private void setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupKeypad() {
        edtAmount = findViewById(R.id.edt_amount);
        keypad = findViewById(R.id.keypad);

        edtAmount.setOnTouchListener((view, motionEvent) -> {
            edtAmount.requestFocus();
            view.performClick();
            return true;
        });

        keypad.connectEditText(edtAmount);
        edtAmount.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}