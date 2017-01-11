package ru.dmisb.flowtest.flow;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import flow.Dispatcher;
import flow.Flow;
import flow.Traversal;
import flow.TraversalCallback;
import ru.dmisb.flowtest.R;
import ru.dmisb.flowtest.ui.activities.RootActivity;

public class TreeKeyDispatcher implements Dispatcher {

    private final RootActivity mActivity;

    public TreeKeyDispatcher(RootActivity activity) {
        mActivity = activity;
    }

    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Object destKey = traversal.destination.top();

        ViewGroup frame = (ViewGroup) mActivity.findViewById(R.id.root_frame);

        // We're already showing something, clean it up.
        if (frame.getChildCount() > 0) {
            final View currentView = frame.getChildAt(0);

            // Save the outgoing view state.
            if (traversal.origin != null) {
                traversal.getState(traversal.origin.top()).save(currentView);
            }

            // Short circuit if we would just be showing the same view again.
            final Object currentKey = Flow.getKey(currentView);
            if (destKey.equals(currentKey)) {
                callback.onTraversalCompleted();
                return;
            }

            frame.removeAllViews();
        }

        @LayoutRes final int layout;

        Screen screen = destKey.getClass().getAnnotation(Screen.class);

        if (screen != null) {
            layout = screen.value();
        } else {
            throw new AssertionError("Unrecognized screen annotation " + destKey);
        }

        View incomingView = LayoutInflater.from(traversal.createContext(destKey, mActivity)) //
                .inflate(layout, frame, false);

        frame.addView(incomingView);
        traversal.getState(traversal.destination.top()).restore(incomingView);

        callback.onTraversalCompleted();

        mActivity.updateToolBar();
    }
}