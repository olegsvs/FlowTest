package ru.dmisb.flowtest.screens.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import flow.ClassKey;
import flow.Flow;

public abstract class BaseView<S extends ClassKey> extends FrameLayout {

    protected S mScreen;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            mScreen = Flow.getKey(this);
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