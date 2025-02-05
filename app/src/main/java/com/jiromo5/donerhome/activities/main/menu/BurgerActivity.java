package com.jiromo5.donerhome.activities.main.menu;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.main.menu.DrinkScrollManager;
import com.jiromo5.donerhome.main.menu.listeners.BackToMenuListener;
import com.jiromo5.donerhome.main.menu.listeners.CoffeeScrollListener;
import com.jiromo5.donerhome.main.menu.listeners.SelectCheeseBurgerListener;
import com.jiromo5.donerhome.main.menu.listeners.SelectColaListener;
import com.jiromo5.donerhome.main.menu.listeners.SodaScrollListener;
import com.jiromo5.donerhome.main.menu.listeners.WaterScrollListener;

import java.util.HashSet;

public class BurgerActivity extends AppCompatActivity {

    public static String selectedItem;

    private ImageButton backButton;
    private ImageButton cheeseburger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);
        overridePendingTransition(0, 0);

        selectedItem = null;

        backButton = findViewById(R.id.back_button);
        cheeseburger = findViewById(R.id.cheeseburger);

        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToMenuListener(this));
        cheeseburger.setOnClickListener(new SelectCheeseBurgerListener(this));
    }
}