package com.istur.android_starter.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.transition.TransitionManager;

import com.istur.android_starter.R;
import com.istur.android_starter.utils.Utils;

import butterknife.BindView;

public class ButtonCompound extends AppCompoundView {

    public final static int PRIMARY = 0;
    public final static int SECONDARY = 1;
    public final static int TEXT = 2;
    public final static int PRIMARY_NO_PADDING = 3;
    public final static int SECONDARY_NO_PADDING = 4;
    public final static int TEXT_NO_PADDING = 5;
    public final static int DISABLED = 6;
    public final static int DISABLED_SECONDARY = 7;
    public final static int DISABLED_PRIMARY_NO_PADDING = 8;
    public final static int DISABLED_SECONDARY_NO_PADDING = 9;
    public final static int TERTIARY = 10;


    public final static int NORMAL = 0;
    public final static int SMALL = 1;

    @BindView(R.id.compound_button_constraint_layout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.compound_button_container)
    LinearLayout container;
    @BindView(R.id.compound_button_cardview)
    CardView cardView;
    @BindView(R.id.compound_button_button)
    TextView button;
    @BindView(R.id.compound_button_loading)
    ProgressBar loadingView;

    private int currentStyle;

    public ButtonCompound(Context context) {
        super(context);
    }

    public ButtonCompound(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonCompound(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.compound_button;
    }

    protected void evaluateAttributeSet(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonCompound);
        String textString = a.getString(R.styleable.ButtonCompound_text);
        button.setText(textString);
        int style = a.getInt(R.styleable.ButtonCompound_button_style, 0);
        setStyle(style);
        int size = a.getInt(R.styleable.ButtonCompound_button_size, 0);
        setSize(size);
        a.recycle();
    }

    public void setText(String text) {
        button.setText(text);
    }

    public void setText(int id) {
        button.setText(id);
    }

    public void setStyle(int style) {
        currentStyle = style;
        switch (style) {
            case PRIMARY_NO_PADDING:
                constraintLayout.setPadding(Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4));
                cardView.setEnabled(true);
            case PRIMARY:
                button.setTextColor(getResources().getColor(R.color.textPrimary));
                setRippleBackground(container, R.drawable.rounded_shape_primary, R.color.ripple_light);
                cardView.setEnabled(true);
                break;
            case SECONDARY_NO_PADDING:
                constraintLayout.setPadding(Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4));
                setRippleBackground(container, R.drawable.rounded_shape_secondary, R.color.ripple_dark);
            case SECONDARY:
                button.setTextColor(getResources().getColor(R.color.colorPrimary));
                cardView.setEnabled(true);
                setRippleBackground(container, R.drawable.rounded_shape_secondary, R.color.ripple_dark);
                break;
            case TEXT_NO_PADDING:
                constraintLayout.setPadding(Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4));
            case TEXT:
                button.setTextColor(getResources().getColor(R.color.colorPrimary));
                setRippleBackground(cardView, R.drawable.rounded_shape_no_background, R.color.ripple_dark);
                cardView.setElevation(0);
                cardView.setEnabled(true);
                break;
            case DISABLED:
                container.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_shape_primary_opacity50));
                button.setTextColor(getResources().getColor(R.color.white_op50));
                cardView.setEnabled(false);
                break;
            case DISABLED_SECONDARY:
                button.setTextColor(getResources().getColor(R.color.red_violet_op50));
                button.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                cardView.setEnabled(false);
                setRippleBackground(container, R.drawable.rounded_shape_secondary_dark_white, R.color.ripple_dark);
                break;
            case DISABLED_PRIMARY_NO_PADDING:
                container.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_shape_primary_opacity50));
                button.setTextColor(getResources().getColor(R.color.white_op50));
                cardView.setEnabled(false);
                constraintLayout.setPadding(Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4));
                break;
            case DISABLED_SECONDARY_NO_PADDING:
                button.setTextColor(getResources().getColor(R.color.red_violet_op50));
                button.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                cardView.setEnabled(false);
                setRippleBackground(container, R.drawable.rounded_shape_secondary_dark_white, R.color.ripple_dark);
                constraintLayout.setPadding(Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4));
                break;
            case TERTIARY:
                button.setTextColor(getResources().getColor(R.color.colorPrimary));
                button.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                cardView.setEnabled(true);
                setRippleBackground(container, R.drawable.rounded_shape_tertiary, R.color.ripple_dark);
                break;

        }
    }

    public void setSize(int size) {
        ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
        switch (size) {
            case NORMAL:
                layoutParams.height = Utils.dpToPx(getContext(), 48);
                button.setLayoutParams(layoutParams);
                break;
            case SMALL:
                constraintLayout.setPadding(Utils.dpToPx(getContext(), 8),
                        Utils.dpToPx(getContext(), 8),
                        Utils.dpToPx(getContext(), 8),
                        Utils.dpToPx(getContext(), 8));
                container.setPadding(Utils.dpToPx(getContext(), 4), Utils.dpToPx(getContext(), 4),
                        Utils.dpToPx(getContext(), 4), Utils.dpToPx(getContext(), 4));
                layoutParams.height = Utils.dpToPx(getContext(), 30);
                button.setPadding(Utils.dpToPx(getContext(), 4), 0, Utils.dpToPx(getContext(), 4), 0);
                button.setLayoutParams(layoutParams);
                break;
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        cardView.setOnClickListener(listener);
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        cardView.setOnLongClickListener(listener);
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            switch (currentStyle) {
                case DISABLED:
                    setStyle(PRIMARY);
                    break;
                case DISABLED_SECONDARY:
                    setStyle(SECONDARY);
                    break;
                case DISABLED_PRIMARY_NO_PADDING:
                    setStyle(PRIMARY_NO_PADDING);
                    break;
                case DISABLED_SECONDARY_NO_PADDING:
                    setStyle(SECONDARY_NO_PADDING);
                    break;
            }
        } else {
            switch (currentStyle) {
                case PRIMARY:
                    setStyle(DISABLED);
                    break;
                case SECONDARY:
                    setStyle(DISABLED_SECONDARY);
                    break;
                case PRIMARY_NO_PADDING:
                    setStyle(DISABLED_PRIMARY_NO_PADDING);
                    break;
                case SECONDARY_NO_PADDING:
                    setStyle(DISABLED_SECONDARY_NO_PADDING);
                    break;
            }
        }
    }

    public void setEnableSecondary(boolean enableSecondary) {
        setEnabled(enableSecondary);
//        if (enableSecondary) {
//            setStyle(SECONDARY_NO_PADDING);
//        } else {
//            setStyle(DISABLED_SECONDARY);
//        }
    }

    public void setAlertButtonSize() {
        button.setTextSize(18);
    }

    public void showLoading(boolean enable) {
        TransitionManager.beginDelayedTransition(container);
        if (enable) {
            switch (currentStyle) {
                case PRIMARY:
                case PRIMARY_NO_PADDING:
                case DISABLED:
                case DISABLED_PRIMARY_NO_PADDING:
                    loadingView.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.white)));
                    break;
                case SECONDARY:
                case SECONDARY_NO_PADDING:
                case DISABLED_SECONDARY:
                case DISABLED_SECONDARY_NO_PADDING:
                    loadingView.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red_violet_op50)));
                    break;
            }
            loadingView.setVisibility(VISIBLE);
            setEnabled(false);
        } else {
            loadingView.setVisibility(GONE);
            setEnabled(true);
        }
    }

    public void setLeftIcon(Drawable icon) {
        button.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
    }
}
