package ru.dmisb.flowtest.flow;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import flow.KeyParceler;

public class TreeKeyParceler implements KeyParceler {

    @NonNull
    @Override
    public Parcelable toParcelable(@NonNull Object key) {
        return (Parcelable) key;
    }

    @NonNull
    @Override
    public Object toKey(@NonNull Parcelable parcelable) {
        return parcelable;
    }
}
