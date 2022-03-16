package com.istur.android_starter.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import org.apache.commons.lang3.StringUtils;

import butterknife.ButterKnife;

public abstract class AppCompoundView extends ConstraintLayout {

    protected Context context;

    public AppCompoundView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AppCompoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public AppCompoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    protected abstract int getLayoutId();

    protected void init() {
        bindView();
    }

    private void init(AttributeSet attrs) {
        init();
        evaluateAttributeSet(attrs);
    }

    private void bindView() {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        ButterKnife.bind(this);
        onBind();
        onDraw();
    }

    protected void onDraw() {

    }

    protected void onBind() {
        //STUB - To override if needed
    }

    protected void evaluateAttributeSet(AttributeSet attrs) {
        //STUB - To override if needed
    }

    /**
     * Utility method to set accessibilty content description, marking inner children as ignored by talkback.
     * <p>
     * contentDescription is appended to previous contentDescription to create a complete description in complex compound views.
     *
     * @param appendString
     */
    public void appendContentDescription(String appendString) {
        if (StringUtils.isEmpty(this.getContentDescription())) {
            setOnlyParentContentDescription();
        }
        String finalString = this.getContentDescription() + " " + appendString;
        this.setContentDescription(finalString);
    }

    private void setOnlyParentContentDescription() {
        this.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
        for (int i = 0; i < this.getChildCount(); i++) {
            this.getChildAt(i).setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
        }
    }

    private Drawable getBackgroundRippleDrawable(int drawableId, int rippleColor) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        return new RippleDrawable(getPressedState(rippleColor), drawable, null);
    }

    private Drawable getBackgroundRippleDrawable(Drawable drawable, int rippleColor) {
        return new RippleDrawable(getPressedState(rippleColor), drawable, null);
    }

    private ColorStateList getPressedState(int pressedColor) {
        pressedColor = ContextCompat.getColor(this.getContext(), pressedColor);
        return new ColorStateList(new int[][]{new int[]{}}, new int[]{pressedColor});
    }

    /**
     * Create a ripple background and set it to the view that must intercept interactions
     *
     * @param v             - view to use as container and target of interactions
     * @param drawableId    - background
     * @param rippleColorId - ripple_light or ripple_dark
     */
    protected void setRippleBackground(View v, int drawableId, int rippleColorId) {
        v.setBackground(getBackgroundRippleDrawable(drawableId, rippleColorId));
    }

    protected void setRippleBackground(View v, Drawable drawable, int rippleColorId) {
        v.setBackground(getBackgroundRippleDrawable(drawable, rippleColorId));
    }

}
