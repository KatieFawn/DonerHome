package com.jiromo5.donerhome.main.menu.listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.main.menu.ColaOrderState;
import com.jiromo5.donerhome.main.menu.OrderManager;

public class MinusItemListener implements View.OnClickListener {

    private EditText countOfItem;

    public MinusItemListener(EditText countOfItem){
        this.countOfItem = countOfItem;
    }

    @Override
    public void onClick(View view) {
        if (ColaOrderState.countOfItem != 1) {
            ColaOrderState.countOfItem = ColaOrderState.countOfItem - 1;
        }

        countOfItem.setText(Integer.toString(ColaOrderState.countOfItem));
        countOfItem.setTextColor(Color.parseColor("#919191"));
    }
}
