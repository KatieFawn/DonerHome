package com.jiromo5.donerhome.main.menu.listeners;

import android.view.View;
import android.widget.ImageView;

import com.jiromo5.donerhome.main.menu.ItemsBurgerManager;
import com.jiromo5.donerhome.main.menu.ItemsDrinkManager;
import com.jiromo5.donerhome.main.menu.OrderState;
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
        if (ItemsDrinkManager.isColaButtonClicked) {
            if (OrderState.isSmallSizeSelect){
                OrderDetails.colaSizeSOrder.put(OrderDetails.countOfOrder, OrderState.countOfItem);
                OrderDetails.countOfOrder++;
            } else if (OrderState.isMediumSizeSelect){

            } else if (OrderState.isLargeSizeSelect){

            }
        } else if (ItemsBurgerManager.isCheeseBurgerButtonClicked){
            OrderDetails.cheeseburgerOrder.put(OrderDetails.countOfOrder, OrderState.countOfItem);
            OrderDetails.countOfOrder++;
        }
    }

    private int getColaSize(){
        if (OrderState.isSmallSizeSelect){
            return 1;
        } else if (OrderState.isMediumSizeSelect){
            return 2;
        } else if (OrderState.isLargeSizeSelect) {
            return 3;
        }
        return 0;
    }
}
