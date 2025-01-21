package com.jiromo5.donerhome.activities.main.shopping;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        overridePendingTransition(0,0);




        eventHandler();
        configurePopupMenu();

    }

    private void configurePopupMenu() {
        // Настройка адаптера
        Spinner spinner = findViewById(R.id.address_list);

        List<String> listOfAddress = new ArrayList<>();
        for (int i = 0; i < UserAddress.addressName.length; i ++){
            if (UserAddress.addressName[i] != null){
                listOfAddress.add(UserAddress.addressName[i]);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_addresses, // Ваш кастомный макет
                listOfAddress
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Выпадающий вид
        spinner.setAdapter(adapter);
    }





    private void eventHandler(){

    }
}
