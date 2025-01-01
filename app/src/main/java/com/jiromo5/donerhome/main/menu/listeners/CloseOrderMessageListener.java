package com.jiromo5.donerhome.main.menu.listeners;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class CloseOrderMessageListener implements View.OnTouchListener {

    private ImageView completeOrderMessage;

    public CloseOrderMessageListener(ImageView completeOrderMessage){
        this.completeOrderMessage = completeOrderMessage;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            if (completeOrderMessage.getVisibility() == View.VISIBLE){
                completeOrderMessage.setVisibility(View.INVISIBLE);
            }
        }

        return true;
    }
}
