package io.github.simonakers.envelo.view.custom;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import io.github.simonakers.envelo.R;

public class NumberKeypad extends FrameLayout implements View.OnClickListener {
    private static final String BACKSPACE = "backspace";
    private static final String CONFIRM = "confirm";

    InputConnection inputConnection;
    OnConfirmListener onConfirmListener;

    public NumberKeypad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Initializes the view.
     *
     * @param context the context of the view
     */
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_number_keypad, this, true);

        // Get the root view group
        ViewGroup root = (ViewGroup) getChildAt(0);

        // For each row of the keypad
        for (int i = 0; i < root.getChildCount(); i++) {
            // Get the row
            ViewGroup vg = (ViewGroup) root.getChildAt(i);

            // For each view in the row
            for (int j = 0; j < vg.getChildCount(); j++) {
                // Set its click listener
                View v = vg.getChildAt(j);
                v.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null) return;

        String tag = (String) view.getTag();

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (tag.equals(BACKSPACE)) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                inputConnection.commitText("", 1);
            }
        } else if (tag.equals(CONFIRM)) {
            if (onConfirmListener != null) onConfirmListener.onConfirm();
        } else {
            inputConnection.commitText(tag, 1);
        }
    }

    /**
     * Connects the keypad to the given EditText.
     *
     * @param editText the EditText to connect the keypad to
     */
    public void connectEditText(EditText editText) {
        inputConnection = editText.onCreateInputConnection(new EditorInfo());
    }

    /**
     * Sets the listener for the keypad's confirm button.
     *
     * @param onConfirmListener the listener for the keypad's confirm button
     */
    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    /**
     * An observer interface for the NumberKeypad's confirm button.
     */
    public interface OnConfirmListener {
        /**
         * Called when the NumberKeypad's confirm button is clicked.
         */
        void onConfirm();
    }
}
