package ru.dmisb.flowtest.screens.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class BaseView extends FrameLayout {

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            bindScreen();
        }
    }

    protected abstract void bindScreen();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()){
            attachListeners();
        }
    }

    protected abstract void attachListeners();

    @Override
    protected void onDetachedFromWindow() {
        if (!isInEditMode()){
            detachListeners();
        }
        super.onDetachedFromWindow();
    }

    protected abstract void detachListeners();
}
