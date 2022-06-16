package io.github.simonakers.envelo.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import io.github.simonakers.envelo.R;
import io.github.simonakers.envelo.view.custom.NumberKeypad;

public class TransactionActivity extends AppCompatActivity {
    EditText edtAmount;
    NumberKeypad keypad;
    ConstraintLayout options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        setupToolbar();
        setupKeypad();

        options = findViewById(R.id.options);
        options.setVisibility(View.GONE);

        edtAmount.requestFocus();
    }

    /**
     * Sets up the toolbar.
     */
    private void setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Sets up the keypad and amount edit text.
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setupKeypad() {
        edtAmount = findViewById(R.id.edt_amount);
        keypad = findViewById(R.id.keypad);

        edtAmount.setOnTouchListener((view, motionEvent) -> {
            edtAmount.requestFocus();
            view.performClick();
            return true;
        });

        edtAmount.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                showView(keypad);
                hideView(options);
            } else {
                showView(options);
                hideView(keypad);
            }
        });

        keypad.connectEditText(edtAmount);

        keypad.setOnConfirmListener(view -> edtAmount.clearFocus());
    }

    /**
     * Shows the given view with a sliding animation.
     *
     * @param view the view to show
     */
    private void showView(View view) {
        if (view.getVisibility() != View.VISIBLE) view.setVisibility(View.VISIBLE);

        view.animate().translationY(0).setDuration(300);
    }

    /**
     * Hides the given view with a sliding animation.
     *
     * @param view the view to hide
     */
    private void hideView(View view) {
        view.animate().translationY(getWindow().getDecorView().getHeight()).setDuration(300);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}