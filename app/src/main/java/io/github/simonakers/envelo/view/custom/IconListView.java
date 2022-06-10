package io.github.simonakers.envelo.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import io.github.simonakers.envelo.R;

public class IconListView extends LinearLayout {
    /** The size of the icons, in pixels */
    private static final int DEFAULT_ICON_SIZE = 24;
    /** The orientation of the icons (0 horizontal, 1 vertical) */
    private static final int DEFAULT_ORIENTATION = LinearLayout.HORIZONTAL;

    private int iconSize;
    private int orientation;

    private Context context;
    private FrameLayout.LayoutParams imgParams;

    private ArrayList<ImageView> icons;

    public IconListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme()
            .obtainStyledAttributes(attrs, R.styleable.IconListView, 0, 0);

        try {
            iconSize = a.getDimensionPixelSize(R.styleable.IconListView_iconSize, DEFAULT_ICON_SIZE);
            orientation = a.getInt(R.styleable.IconListView_orientation, DEFAULT_ORIENTATION);
        } finally {
            a.recycle();
        }

        this.context = context;

        imgParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );

        icons = new ArrayList<>();

        setOrientation(orientation);
    }

    public void addIcon(int resource) {
        ImageView v = new ImageView(context);
        v.setImageResource(resource);
        v.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        v.setLayoutParams(new LinearLayout.LayoutParams(iconSize, iconSize));

        addView(v);
    }
}
