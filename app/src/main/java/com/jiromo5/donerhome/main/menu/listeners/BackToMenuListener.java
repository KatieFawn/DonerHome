package com.jiromo5.donerhome.main.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.menu.MenuActivity;
import com.jiromo5.donerhome.utils.CartStorage;
import com.jiromo5.donerhome.utils.TokenManager;

import java.util.Map;

public class BackToMenuListener implements View.OnClickListener {

    private Context context;

    public BackToMenuListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        //CartStorage.createContainer(context);
        CartStorage.remove("1");
        CartStorage.remove("2");
        CartStorage.addProduct("0", "Cola Size S_1" );
        CartStorage.addProduct("1", "Cheeseburger_4" );
        String[] res = CartStorage.getAllProducts();
        //CartStorage.remove();

        for (int i = 0; i < res.length; i ++){
            System.out.println(res[i] + " aaaaaaaaaaaaaaaaaaaa");
        }

        //CartStorage.logData();
        //TokenManager.logData();
        //CartStorage.testStorage();
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }
}
