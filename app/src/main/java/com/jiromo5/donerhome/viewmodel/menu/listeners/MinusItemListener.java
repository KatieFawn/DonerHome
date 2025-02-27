package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.viewmodel.menu.OrderState;

public class MinusItemListener implements View.OnClickListener {

    private EditText countOfItem;

    public MinusItemListener(EditText countOfItem){
        this.countOfItem = countOfItem;
    }

    @Override
    public void onClick(View view) {
        if (OrderState.countOfItem != 1) {
            OrderState.countOfItem = OrderState.countOfItem - 1;
        }

        countOfItem.setText(Integer.toString(OrderState.countOfItem));
        countOfItem.setTextColor(Color.parseColor("#919191"));
    }
}
