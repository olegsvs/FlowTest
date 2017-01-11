package ru.dmisb.flowtest.screens.base;

import android.os.Parcel;
import android.os.Parcelable;

import flow.ClassKey;

public class BaseScreen extends ClassKey implements Parcelable {

    public BaseScreen() {
    }

    // region ==================== Parcelable ====================

    private BaseScreen(Parcel in) {

    }

    public static final Creator<BaseScreen> CREATOR = new Creator<BaseScreen>() {
        @Override
        public BaseScreen createFromParcel(Parcel in) {
            return new BaseScreen(in);
        }

        @Override
        public BaseScreen[] newArray(int size) {
            return new BaseScreen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    // endregion
}
