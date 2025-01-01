package com.jiromo5.donerhome.main.menu.listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.main.menu.ColaOrderState;

public class PlusItemListener implements View.OnClickListener {

    private EditText countOfItem;

    public PlusItemListener(EditText countOfItem){
        this.countOfItem = countOfItem;
    }

    @Override
    public void onClick(View view) {
        if (ColaOrderState.countOfItem != 9) {
            ColaOrderState.countOfItem = ColaOrderState.countOfItem + 1;
        }

        countOfItem.setText(Integer.toString(ColaOrderState.countOfItem));
        countOfItem.setTextColor(Color.parseColor("#919191"));
    }
}