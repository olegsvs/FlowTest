package ru.dmisb.flowtest.ui.helpers;

import android.support.annotation.Nullable;

public class ActionBarBuilder {
    private boolean mBackArrowShow = false;
    private CharSequence mBarTitle;
    @Nullable
    private IActionBarView mView;

    public ActionBarBuilder(IActionBarView activity) {
        mView = activity;
    }

    public ActionBarBuilder setBackArrowShow(boolean backArrowShow) {
        mBackArrowShow = backArrowShow;
        return this;
    }

    public ActionBarBuilder setBarTitle(CharSequence barTitle) {
        mBarTitle = barTitle;
        return this;
    }

    public void build() {
        if (mView != null) {
            mView.setBackArrowShow(mBackArrowShow);
            mView.setBatTitle(mBarTitle);
        }
    }
}
