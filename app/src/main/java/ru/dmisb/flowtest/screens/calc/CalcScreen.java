package ru.dmisb.flowtest.screens.calc;

import android.view.View;

import flow.ClassKey;
import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.flow.Screen;

@Screen(R.layout.screen_calc)
public class CalcScreen extends ClassKey {
    private int mState = View.VISIBLE;

    int getState() {
        return mState;
    }

    void setState(int state) {
        mState = state;
    }
}
