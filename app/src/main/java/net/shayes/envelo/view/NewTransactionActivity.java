package net.shayes.envelo.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDate;

import net.shayes.envelo.R;
import net.shayes.envelo.util.LocalDateUtils;
import net.shayes.envelo.view.custom.NumberKeypad;

public class NewTransactionActivity extends AppCompatActivity {
    EditText edtAmount;
    NumberKeypad keypad;
    ConstraintLayout options;

    Button btnDate;
    Button btnAccount;
    Button btnEnvelope;
    Button btnVendor;

    DatePickerFragment dateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        setupToolbar();
        setupKeypad();
        setupButtons();

        options = findViewById(R.id.options);
        options.setVisibility(View.GONE);

        edtAmount.requestFocus();

        dateFragment = new DatePickerFragment();
        dateFragment.setListener(this::setDateText);
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

    private void setupButtons() {
        btnDate = findViewById(R.id.btn_date);
        btnAccount = findViewById(R.id.btn_account);
        btnEnvelope = findViewById(R.id.btn_envelope);
        btnVendor = findViewById(R.id.btn_vendor);

        btnDate.setOnClickListener(this::onClickDate);
        btnAccount.setOnClickListener(this::onClickAccount);
        btnEnvelope.setOnClickListener(this::onClickEnvelope);
        btnVendor.setOnClickListener(this::onClickVendor);

        setDateText(LocalDate.now());
    }

    private void setDateText(LocalDate date) {
        btnDate.setText(LocalDateUtils.formatFull(date));
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

    private void onClickDate(View view) {
        dateFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void onClickAccount(View view) {

    }

    private void onClickEnvelope(View view) {

    }

    private void onClickVendor(View view) {

    }


}