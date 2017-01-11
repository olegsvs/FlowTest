package ru.dmisb.flowtest.utils;

import android.util.Log;

public class L {

    public static void d(String message) {
        Throwable thr = new Throwable();
        StackTraceElement[] ste = thr.getStackTrace();
        String callerClassName = null;
        int i = 1;
        while (i < ste.length && ste[i].getMethodName().startsWith("access$")) {
            ++i;
        }
        if (i < ste.length) {
            callerClassName = ste[i].getClassName();
        }
        int point = callerClassName.lastIndexOf('.');
        callerClassName = "DMISB " + callerClassName.substring(point + 1);
        Log.d(callerClassName, message);
    }
}
