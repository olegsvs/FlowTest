package ru.dmisb.flowtest.screens.graphic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;

import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.databinding.ScreenGraphicBinding;
import ru.dmisb.flowtest.screens.base.BaseView;
import ru.dmisb.flowtest.ui.helpers.IView;

public class GraphicView extends BaseView<GraphicScreen> implements IView {

    private GraphicModel mModel;
    private ScreenGraphicBinding mBinding;

    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mModel = new GraphicModel("New Graphic Screen");
    }

    // region ==================== BaseView ====================

    @Override
    protected void bindScreen() {
        mBinding = DataBindingUtil.bind(this);
        mBinding.setGraphic(mModel);
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
        return R.string.graphic_title;
    }

    @Override
    public boolean getBackArrowShow() {
        return true;
    }

    // endregion
}