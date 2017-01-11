package ru.dmisb.flowtest.screens.calc;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;

import flow.Flow;
import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.databinding.ScreenCalcBinding;
import ru.dmisb.flowtest.screens.base.BaseView;
import ru.dmisb.flowtest.screens.graphic.GraphicScreen;
import ru.dmisb.flowtest.ui.helpers.IView;

public class CalcView extends BaseView implements IView {

    private ScreenCalcBinding mBinding;
    private CalcModel mModel;

    public CalcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mModel = new CalcModel("New Calc Screen");
    }

    private void showGraphic() {
        Flow.get(this).set(new GraphicScreen());
    }

    // region ==================== BaseView ====================

    @Override
    protected void bindScreen() {
        mBinding = DataBindingUtil.bind(this);
        mBinding.setCalc(mModel);
    }

    @Override
    protected void attachListeners() {
        mBinding.showGraphicBtn.setOnClickListener(v -> showGraphic());
    }

    @Override
    protected void detachListeners() {
        mBinding.showGraphicBtn.setOnClickListener(null);
    }

    // endregion

    // region ==================== IView ====================

    @Override
    public int getBarTitleResId() {
        return R.string.calc_title;
    }

    @Override
    public boolean getBackArrowShow() {
        return false;
    }

    // endregion
}