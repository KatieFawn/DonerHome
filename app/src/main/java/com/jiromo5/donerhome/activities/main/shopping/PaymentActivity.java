package com.jiromo5.donerhome.activities.main.shopping;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.main.shopping.listeners.BackToCartListener;
import com.jiromo5.donerhome.main.shopping.listeners.CompletePaymentListener;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {


    private ImageButton backButton;
    private EditText cartNumber;
    private EditText cartDate;
    private EditText cvvCart;
    private ImageButton completeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        overridePendingTransition(0,0);

        backButton = findViewById(R.id.back_button);
        completeButton = findViewById(R.id.complete_payment);
        cartNumber = findViewById(R.id.cart_number);
        cartDate = findViewById(R.id.MM_YY);
        cvvCart = findViewById(R.id.CVV);



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
        BackToCartListener backToCartListener = new BackToCartListener(this);
        CompletePaymentListener completePaymentListener = new CompletePaymentListener(cartNumber, cartDate, cvvCart);

        backButton.setOnClickListener(backToCartListener);
        completeButton.setOnClickListener(completePaymentListener);

    }
}
