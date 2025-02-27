package com.jiromo5.donerhome.activities.home.shopping;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.PaymentAddress;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.BackToCartListener;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.CompletePaymentListener;

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
        setContentView(R.layout.payment_activity);
        overridePendingTransition(0,0);

        backButton = findViewById(R.id.back_button);
        completeButton = findViewById(R.id.complete_payment);
        cartNumber = findViewById(R.id.cart_number);
        cartDate = findViewById(R.id.MM_YY);
        cvvCart = findViewById(R.id.CVV);

        setView();

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

        // Обработчик выбора элемента в Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedAddress = parentView.getItemAtPosition(position).toString();
                PaymentAddress.paymentAddress = selectedAddress;
                Log.d("Selected Address", selectedAddress);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void eventHandler(){
        BackToCartListener backToCartListener = new BackToCartListener(this);
        CompletePaymentListener completePaymentListener = new CompletePaymentListener(
                this, cartNumber, cartDate, cvvCart);

        backButton.setOnClickListener(backToCartListener);
        completeButton.setOnTouchListener(completePaymentListener);

    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), CartResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.complete_payment), CartResources.PAYMENT_BUTTON_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.address_template), CartResources.ADDRESS_TEMPLATE);
    }
}
