package io.github.simonakers.envelo.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import io.github.simonakers.envelo.R;

public class IconToggleButton extends AppCompatButton {
    private static final String DEFAULT_TEXT_ON = "On";
    private static final String DEFAULT_TEXT_OFF = "Off";

    private String textOn;
    private String textOff;
    private int textColorOn = -1;
    private int textColorOff = -1;

    private Drawable iconOn;
    private Drawable iconOff;
    private int iconTintOn;
    private int iconTintOff;

    private boolean toggled;

    public IconToggleButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);

        setOnClickListener(view -> setToggled(!toggled));
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray arr = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.IconToggleButton, 0, 0);

        try {
            // Set the initial icon size
            setTextOn(arr.getString(R.styleable.IconToggleButton_textOn));
            setTextOff(arr.getString(R.styleable.IconToggleButton_textOff));

            setIconOn(arr.getDrawable(R.styleable.IconToggleButton_iconOn));
            setIconOff(arr.getDrawable(R.styleable.IconToggleButton_iconOff));

            setTextColorOn(arr.getColor(
                R.styleable.IconToggleButton_textColorOn,
                0
            ));

            setTextColorOff(arr.getColor(
                R.styleable.IconToggleButton_textColorOff,
                0
            ));

            setIconTintOn(arr.getColor(
                R.styleable.IconToggleButton_iconTintOn,
                0
            ));

            setIconTintOff(arr.getColor(
                R.styleable.IconToggleButton_iconTintOff,
                0
            ));

            setToggled(arr.getBoolean(R.styleable.IconToggleButton_toggled, false));
        } finally {
            arr.recycle();
        }
    }

    private void update() {
        if (toggled) {
            setText(textOn);
            setTextColor(textColorOn);
            setCompoundDrawablesWithIntrinsicBounds(iconOn, null, null, null);
        } else {
            setText(textOff);
            setTextColor(textColorOff);
            setCompoundDrawablesWithIntrinsicBounds(iconOff, null, null, null);
        }

        invalidate();
        requestLayout();
    }

    public String getTextOn() {
        return textOn;
    }

    public void setTextOn(String textOn) {
        this.textOn = textOn;
        update();
    }

    public String getTextOff() {
        return textOff;
    }

    public void setTextOff(String textOff) {
        this.textOff = textOff;
        update();
    }

    public Drawable getIconOn() {
        return iconOn;
    }

    public void setIconOn(Drawable iconOn) {
        this.iconOn = iconOn;
        update();
    }

    public Drawable getIconOff() {
        return iconOff;
    }

    public void setIconOff(Drawable iconOff) {
        this.iconOff = iconOff;
        update();
    }

    public int getTextColorOn() {
        return textColorOn;
    }

    public void setTextColorOn(int textColorOn) {
        if (textColorOn != 0) {
            this.textColorOn = textColorOn;
            update();
        }
    }

    public int getTextColorOff() {
        return textColorOff;
    }

    public void setTextColorOff(int textColorOff) {
        if (textColorOff != 0) {
            this.textColorOff = textColorOff;
            update();
        }
    }

    public int getIconTintOn() {
        return iconTintOn;
    }

    public void setIconTintOn(int iconTintOn) {
        if (iconTintOn != 0) {
            iconOn.setTint(iconTintOn);
            update();
        }
    }

    public int getIconTintOff() {
        return iconTintOff;
    }

    public void setIconTintOff(int iconTintOff) {
        if (iconTintOn != 0) {
            iconOff.setTint(iconTintOff);
            update();
        }
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        update();
    }
}
