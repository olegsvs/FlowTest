package ru.dmisb.flowtest.screens.graphic;

import android.support.annotation.NonNull;

import flow.ClassKey;
import flow.TreeKey;
import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.flow.Screen;
import ru.dmisb.flowtest.screens.calc.CalcScreen;

@Screen(R.layout.screen_graphic)
public class GraphicScreen extends ClassKey implements TreeKey {
    @NonNull
    @Override
    public Object getParentKey() {
        return new CalcScreen();
    }
}
