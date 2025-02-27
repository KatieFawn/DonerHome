package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.profile.UpdateAddressActivity;
import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.profile.AddressManager;

public class AddAddressListener implements View.OnTouchListener {

    private Context context;

    public AddAddressListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Когда нажали на изображение, показываем pressed состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.add_address), ProfileResources.ADD_ADDRESS_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Когда отпустили или отменили нажатие, возвращаем нормальное состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.add_address), ProfileResources.ADD_ADDRESS_BUTTON);
                break;
        }
        handleButtonClick(v);
        replaceActivity();

        return false;
    }

    private void handleButtonClick(View view){
        if (view.getId() == R.id.address1) {
            AddressManager.clickedButtonNumber = 0;
        } else if (view.getId() == R.id.address2) {
            AddressManager.clickedButtonNumber = 1;
        } else if (view.getId() == R.id.address3) {
            AddressManager.clickedButtonNumber = 2;
        } else if (view.getId() == R.id.address4) {
            AddressManager.clickedButtonNumber = 3;
        } else if (view.getId() == R.id.address5) {
            AddressManager.clickedButtonNumber = 4;
        }
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, UpdateAddressActivity.class);
        context.startActivity(intent);
    }
}
