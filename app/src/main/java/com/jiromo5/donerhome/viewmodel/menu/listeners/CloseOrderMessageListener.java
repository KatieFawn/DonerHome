package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Listener for handling touch events to close the order completion message.
 * When the user touches the associated view, it hides the 'completeOrderMessage' ImageView.
 */
public class CloseOrderMessageListener implements View.OnTouchListener {

    private ImageView completeOrderMessage;

    /**
     * Constructor to initialize the listener with the necessary ImageView.
     *
     * @param completeOrderMessage The ImageView to hide when touched.
     */
    public CloseOrderMessageListener(ImageView completeOrderMessage){
        this.completeOrderMessage = completeOrderMessage;
    }

    /**
     * Called when the user touches the associated view.
     * It hides the 'completeOrderMessage' ImageView if it is visible.
     *
     * @param view The view that was touched.
     * @param motionEvent The motion event representing the touch.
     * @return Returns true to indicate that the event is consumed.
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            Log.d("CloseOrderMessageListener", "Touch event detected, checking visibility of the completeOrderMessage.");
            if (completeOrderMessage.getVisibility() == View.VISIBLE){
                Log.d("CloseOrderMessageListener", "Hiding the completeOrderMessage.");
                completeOrderMessage.setVisibility(View.INVISIBLE);
            }
        }

        return true;
    }
}

