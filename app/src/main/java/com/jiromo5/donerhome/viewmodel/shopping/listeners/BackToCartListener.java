package com.jiromo5.donerhome.viewmodel.shopping.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.shopping.CartActivity;

public class BackToCartListener implements View.OnClickListener {

    private Context context;

    public BackToCartListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }
}

