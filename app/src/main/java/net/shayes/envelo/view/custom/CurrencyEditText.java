package net.shayes.envelo.view.custom;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import java.text.NumberFormat;

public class CurrencyEditText extends AppCompatEditText {

    public CurrencyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        setInputType(InputType.TYPE_CLASS_NUMBER);

        addTextChangedListener(new CurrencyTextWatcher(this));
        setText("0");
    }

    @Override
    protected void onSelectionChanged(int start, int end) {
        super.onSelectionChanged(start, end);

        setSelection(length());
    }

    private static class CurrencyTextWatcher implements TextWatcher {
        private final AppCompatEditText editText;
        private String current = "";

        public CurrencyTextWatcher(AppCompatEditText editText) {
            this.editText = editText;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!s.toString().equals(current)){
                editText.removeTextChangedListener(this);

                String cleanString = s.toString().replaceAll("[$,.]", "");

                double parsed = Double.parseDouble(cleanString);
                String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                current = formatted;
                editText.setText(formatted);
                editText.setSelection(formatted.length());

                editText.addTextChangedListener(this);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void afterTextChanged(Editable editable) { }
    }
}
