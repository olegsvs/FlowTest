package ru.dmisb.flowtest.screens.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;

import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.databinding.ScreenListBinding;
import ru.dmisb.flowtest.screens.base.BaseView;
import ru.dmisb.flowtest.ui.helpers.IView;

public class ListView extends BaseView<ListScreen> implements IView {

    private ListModel mModel;
    private ScreenListBinding mBinding;

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mModel = new ListModel("New List Screen");
    }

    // region ==================== BaseView ====================

    @Override
    protected void bindScreen() {
        mBinding = DataBindingUtil.bind(this);
        mBinding.setList(mModel);
    }

    @Override
    protected void attachListeners() {
        // empty
    }

    @Override
    protected void detachListeners() {
        // empty
    }

    // endregion

    // region ==================== IView ====================

    @Override
    public int getBarTitleResId() {
        return R.string.list_title;
    }

    @Override
    public boolean getBackArrowShow() {
        return false;
    }

    // endregion
}