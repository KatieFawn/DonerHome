package com.jiromo5.donerhome.viewmodel.shopping.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.shopping.PaymentActivity;
import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

public class BuyButtonListener implements View.OnTouchListener {

    private Context context;

    public BuyButtonListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Когда нажали на изображение, показываем pressed состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.buy_button), CartResources.BUY_BUTTON_IMAGE_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Когда отпустили или отменили нажатие, возвращаем нормальное состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.buy_button), CartResources.BUY_BUTTON_IMAGE);
                break;
        }

        replaceActivity();
        return false;
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }
}
