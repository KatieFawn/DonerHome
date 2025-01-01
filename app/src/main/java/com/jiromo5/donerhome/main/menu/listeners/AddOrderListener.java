package com.jiromo5.donerhome.main.menu.listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiromo5.donerhome.main.menu.ColaOrderState;
import com.jiromo5.donerhome.main.menu.OrderDetails;

public class AddOrderListener implements View.OnClickListener {

    private ImageView completeOrderMessage;

    public AddOrderListener(ImageView completeOrderMessage){
        this.completeOrderMessage = completeOrderMessage;
    }

    @Override
    public void onClick(View view) {

        showMessage();
    }

    private void showMessage(){
        completeOrderMessage.setVisibility(View.VISIBLE);
        saveOrder();
    }

    private void saveOrder(){
        OrderDetails.colaOrder.put(OrderDetails.countOfOrder, new int[]{ColaOrderState.countOfItem, getColaSize()});
        OrderDetails.countOfOrder++;
    }

    private int getColaSize(){
        if (ColaOrderState.isSmallSizeSelect){
            return 1;
        } else if (ColaOrderState.isMediumSizeSelect){
            return 2;
        } else if (ColaOrderState.isLargeSizeSelect) {
            return 3;
        }
        return 0;
    }
}
