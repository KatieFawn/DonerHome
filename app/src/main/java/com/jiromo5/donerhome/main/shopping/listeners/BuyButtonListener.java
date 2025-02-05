package com.jiromo5.donerhome.main.shopping.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.shopping.PaymentActivity;

public class BuyButtonListener implements View.OnClickListener {

    private Context context;

    public BuyButtonListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }
}
