package io.github.simonakers.envelo.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.github.simonakers.envelo.R;

public class IconListView extends LinearLayout {
    /** The default size of the icons, in device-independent pixels (dp) */
    private static final float DEFAULT_ICON_SIZE = 24;
    /** The default size of icon margins, in device-independent pixels (dp) */
    private static final float DEFAULT_ICON_MARGINS = 0;

    /** The default size of the icons, in pixels */
    private int defaultIconSizePx;
    /** The default size of icon margins, in pixels */
    private int defaultIconMarginsPx;

    private int iconSize;
    private int iconMargins;

    private LayoutParams imvParams;
    private LayoutParams imvParamsNoMargins;
    private Context context;

    public IconListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

        TypedArray arr = context.getTheme()
            .obtainStyledAttributes(attrs, R.styleable.IconListView, 0, 0);

        try {
            setIconSize(arr.getDimensionPixelSize(
                R.styleable.IconListView_iconSize,
                defaultIconSizePx
            ));

            setIconMargins(arr.getDimensionPixelSize(
                R.styleable.IconListView_iconSpacing,
                defaultIconMarginsPx
            ));
        } finally {
            arr.recycle();
        }

        if (isInEditMode()) {
            addIcon(R.drawable.ic_arrow_out_small);
            addIcon(R.drawable.ic_notes_small);
            addIcon(R.drawable.ic_recur_small);
        }
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);

        if (!isInEditMode()) setIconMargins(iconMargins);
    }

    private void init(Context context) {
        this.context = context;

        defaultIconSizePx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_ICON_SIZE,
                getResources().getDisplayMetrics()
        );

        defaultIconMarginsPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_ICON_MARGINS,
                getResources().getDisplayMetrics()
        );
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
        imvParams = new LinearLayout.LayoutParams(iconSize, iconSize);
        imvParamsNoMargins = new LinearLayout.LayoutParams(iconSize, iconSize);

        // If there are child views, update their sizes
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount() - 1; i++) {
                View v = getChildAt(i);

                if (v instanceof ImageView) {
                    ImageView imv = (ImageView) v;
                    imv.setLayoutParams(imvParams);
                }
            }

            invalidate();
            requestLayout();
        }
    }

    public int getIconMargins() {
        return iconMargins;
    }

    public void setIconMargins(int iconMargins) {
        this.iconMargins = iconMargins;

        if (getOrientation() == LinearLayout.HORIZONTAL) {
            imvParams.setMargins(0, 0, iconMargins, 0);
        } else {
            imvParams.setMargins(0, 0, 0, iconMargins);
        }

        invalidate();
        requestLayout();
    }

    public void addIcon(int resource) {
        ImageView v = new ImageView(context);
        v.setImageResource(resource);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(imvParamsNoMargins);

        if (getChildCount() > 0) getChildAt(getChildCount() - 1).setLayoutParams(imvParams);

        addView(v);
    }
}
