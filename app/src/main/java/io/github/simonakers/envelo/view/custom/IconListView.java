package io.github.simonakers.envelo.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.github.simonakers.envelo.R;

/**
 * A custom View subclass for displaying a list of icons.
 */
public class IconListView extends LinearLayout {
    /** The default size of the icons, in device-independent pixels (dp) */
    private static final float DEFAULT_ICON_SIZE = 24;
    /** The default size of icon margins, in device-independent pixels (dp) */
    private static final float DEFAULT_ICON_SPACING = 5;

    /** The default size of the icons, in pixels */
    private int defaultIconSizePx;
    /** The default size of icon margins, in pixels */
    private int defaultIconSpacingPx;

    /** The size of the icons, in pixels */
    private int iconSize;
    /** The spacing of the icons, in pixels */
    private int iconSpacing;

    /** Layout parameters for the icons */
    private LayoutParams imvParams;
    /** Layout parameters without spacing, used for the last icon */
    private LayoutParams imvParamsNoSpacing;
    /** The context of the view */
    private Context context;

    /**
     * Constructs an IconListView from the given context and attributes.
     *
     * @param context the context of the view
     * @param attrs the attributes of the view
     */
    public IconListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

        TypedArray arr = context.getTheme()
            .obtainStyledAttributes(attrs, R.styleable.IconListView, 0, 0);

        try {
            // Set the initial icon size
            setIconSize(arr.getDimensionPixelSize(
                R.styleable.IconListView_iconSize,
                defaultIconSizePx
            ));

            // Set the initial icon spacing
            setIconSpacing(arr.getDimensionPixelSize(
                R.styleable.IconListView_iconSpacing,
                defaultIconSpacingPx
            ));
        } finally {
            arr.recycle();
        }

        // Show preview for edit mode
        if (isInEditMode()) {
            addIcon(R.drawable.ic_arrow_out_small);
            addIcon(R.drawable.ic_notes_small);
            addIcon(R.drawable.ic_recur_small);
        }
    }

    /**
     * Initializes the view.
     *
     * @param context the context of the view
     */
    private void init(Context context) {
        this.context = context;

        // Convert the default icon size from dp to px
        defaultIconSizePx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_ICON_SIZE,
                getResources().getDisplayMetrics()
        );

        // Convert the default icon spacing from dp to px
        defaultIconSpacingPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_ICON_SPACING,
                getResources().getDisplayMetrics()
        );
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);

        // Reset the icon spacing to match the orientation
        if (imvParams != null) setIconSpacing(iconSpacing);
    }

    /**
     * Gets the size of the icons in pixels.
     *
     * @return the size of the icons in pixels
     */
    public int getIconSize() {
        return iconSize;
    }

    /**
     * Sets the size of the icons, in pixels.
     *
     * @param iconSize the size of the icons, in pixels
     */
    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
        imvParams = new LinearLayout.LayoutParams(iconSize, iconSize);
        imvParamsNoSpacing = new LinearLayout.LayoutParams(iconSize, iconSize);

        // If there are child views, update their sizes
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount() - 1; i++) {
                View v = getChildAt(i);

                if (v instanceof ImageView) {
                    ImageView imv = (ImageView) v;
                    imv.setLayoutParams(imvParams);
                }
            }

            // Update display
            invalidate();
            requestLayout();
        }
    }

    /**
     * Gets the spacing of the icons, in pixels.
     *
     * @return the spacing of the icons, in pixels
     */
    public int getIconSpacing() {
        return iconSpacing;
    }

    /**
     * Sets the spacing of the icons, in pixels.
     *
     * @param iconSpacing the spacing of the icons, in pixels
     */
    public void setIconSpacing(int iconSpacing) {
        this.iconSpacing = iconSpacing;

        // If orientation is horizontal, set left and right margins
        // Else set top and bottom margins
        if (getOrientation() == LinearLayout.HORIZONTAL) {
            imvParams.setMargins(0, 0, iconSpacing, 0);
        } else {
            imvParams.setMargins(0, 0, 0, iconSpacing);
        }

        // Update display
        invalidate();
        requestLayout();
    }

    /**
     * Adds an icon to the view.
     *
     * @param resource the drawable resource ID of the icon to add
     */
    public void addIcon(int resource) {
        ImageView v = new ImageView(context);
        v.setImageResource(resource);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(imvParamsNoSpacing);

        // If there are children, set the current last one to have spacing
        if (getChildCount() > 0) getChildAt(getChildCount() - 1).setLayoutParams(imvParams);

        // Add the icon
        addView(v);
    }
}
