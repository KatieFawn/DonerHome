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
import com.jiromo5.donerhome.main.menu.listeners.SelectColaListener;
import com.jiromo5.donerhome.main.menu.listeners.SodaScrollListener;
import com.jiromo5.donerhome.main.menu.listeners.WaterScrollListener;

import java.util.HashSet;

public class DrinkActivity extends AppCompatActivity {

    public static String selectedItem;

    private ImageButton backButton;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    private ImageButton colaButton;

    private TextView sodaView;
    private TextView coffeeView;
    private TextView waterView;

    private ScrollView scrollView;

    private HashSet<String> listOfDrink;
    private DrinkScrollManager drinkScrollManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        overridePendingTransition(0, 0);

        selectedItem = null;

        backButton = findViewById(R.id.back_button);
        sodaButton = findViewById(R.id.soda);
        coffeeButton = findViewById(R.id.coffee);
        waterButton = findViewById(R.id.water);
        scrollView = findViewById(R.id.drink_list);
        sodaView = findViewById(R.id.soda_category);
        coffeeView = findViewById(R.id.coffee_category);
        waterView = findViewById(R.id.water_category);
        colaButton = findViewById(R.id.cola);

        drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState();

        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToMenuListener(this));
        sodaButton.setOnClickListener(new SodaScrollListener(sodaView, scrollView, sodaButton, coffeeButton, waterButton));
        coffeeButton.setOnClickListener(new CoffeeScrollListener(coffeeView, scrollView, sodaButton, coffeeButton, waterButton));
        waterButton.setOnClickListener(new WaterScrollListener(waterView, scrollView, sodaButton, coffeeButton, waterButton));

        colaButton.setOnClickListener(new SelectColaListener(this));
    }
}
