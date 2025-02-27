package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.ItemsBurgerManager;
import com.jiromo5.donerhome.viewmodel.menu.ItemsDrinkManager;
import com.jiromo5.donerhome.viewmodel.menu.OrderState;
import com.jiromo5.donerhome.viewmodel.menu.OrderDetails;
import com.jiromo5.donerhome.utils.CartStorage;

public class AddOrderListener implements View.OnTouchListener {

    private Context context;
    private ImageView completeOrderMessage;
    private ImageButton addToOrderButton;

    public AddOrderListener(Context context, ImageView completeOrderMessage, ImageButton addToOrderButton){
        this.context = context;
        this.completeOrderMessage = completeOrderMessage;
        this.addToOrderButton = addToOrderButton;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Когда нажали на изображение, показываем pressed состояние
                viewHandler.setImageOnScreen(addToOrderButton, BurgerResources.ADD_ORDER_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Когда отпустили или отменили нажатие, возвращаем нормальное состояние
                viewHandler.setImageOnScreen(addToOrderButton, BurgerResources.ADD_ORDER_BUTTON);
                break;
        }

        showMessage();

        return false;
    }

    private void showMessage(){
        completeOrderMessage.setVisibility(View.VISIBLE);
        saveOrder();
    }

    private void saveOrder(){
        if (ItemsDrinkManager.isColaButtonClicked) {
            if (OrderState.isSmallSizeSelect){
                OrderDetails.colaSizeSOrder.put(OrderDetails.orderQuantity, OrderState.countOfItem);
                CartStorage.addProduct(String.valueOf(OrderDetails.orderQuantity),
                        "Cola Size S" + "_" + OrderState.countOfItem);
                OrderDetails.orderQuantity++;
            } else if (OrderState.isMediumSizeSelect){

            } else if (OrderState.isLargeSizeSelect){

            }
        } else if (ItemsBurgerManager.isCheeseBurgerButtonClicked){
            OrderDetails.cheeseburgerOrder.put(OrderDetails.orderQuantity, OrderState.countOfItem);
            CartStorage.addProduct(String.valueOf(OrderDetails.orderQuantity),
                    "Cheeseburger" + "_" + OrderState.countOfItem);
            OrderDetails.orderQuantity++;
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
