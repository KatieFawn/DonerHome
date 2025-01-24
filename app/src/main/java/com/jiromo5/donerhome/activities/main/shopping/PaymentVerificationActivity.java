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

public class PaymentVerificationActivity extends AppCompatActivity {


    private ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        overridePendingTransition(0, 0);

        backButton = findViewById(R.id.back_button);



        eventHandler();
        configurePopupMenu();

    }

    private void eventHandler() {
        BackToCartListener backToCartListener = new BackToCartListener(this);

        backButton.setOnClickListener(backToCartListener);


    }
}
