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

public class CalcView extends BaseView<CalcScreen> implements IView {

    private ScreenCalcBinding mBinding;
    private CalcModel mModel;

    public CalcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mModel = new CalcModel("New Calc Screen");
    }

    private void showGraphic() {
        Flow.get(this).set(new GraphicScreen());
    }

    private void hideButton() {
        if (mScreen.getState() == VISIBLE) {
            mBinding.magicBtn.setText("SHOW BUTTON");
            mBinding.hidedBtn.setVisibility(GONE);
            mScreen.setState(GONE);
        } else {
            mBinding.magicBtn.setText("HIDE BUTTON");
            mBinding.hidedBtn.setVisibility(VISIBLE);
            mScreen.setState(VISIBLE);
        }
    }

    // region ==================== BaseView ====================

    @Override
    protected void bindScreen() {
        mBinding = DataBindingUtil.bind(this);
        mBinding.setCalc(mModel);

        switch (mScreen.getState()) {
            case VISIBLE:
                mBinding.magicBtn.setText("HIDE BUTTON");
                mBinding.hidedBtn.setVisibility(VISIBLE);
                break;
            case GONE:
                mBinding.magicBtn.setText("SHOW BUTTON");
                mBinding.hidedBtn.setVisibility(GONE);
                break;
        }

    }

    @Override
    protected void attachListeners() {
        mBinding.showGraphicBtn.setOnClickListener(v -> showGraphic());
        mBinding.magicBtn.setOnClickListener(v -> hideButton());
    }

    @Override
    protected void detachListeners() {
        mBinding.showGraphicBtn.setOnClickListener(null);
        mBinding.magicBtn.setOnClickListener(null);
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