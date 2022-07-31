package net.shayes.envelo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import net.shayes.envelo.R;
import net.shayes.envelo.database.Category;

public class NewCategoryActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAmount;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        edtName = ((TextInputLayout) findViewById(R.id.til_name)).getEditText();
        edtAmount = findViewById(R.id.edt_amount);

        findViewById(R.id.fab).setOnClickListener(this::onClickFab);

        type = getIntent().getIntExtra("categoryType", 0);
    }

    private void onClickFab(View view) {
        String name = edtName.getText().toString();

    }
}