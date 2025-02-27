package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.DrinkScrollManager;
import com.jiromo5.donerhome.viewmodel.menu.listeners.BackToMenuListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.CoffeeScrollListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.SelectColaListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.SodaScrollListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.WaterScrollListener;

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
    private DrinkScrollManager drinkScrollManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_activity);
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
        drinkScrollManager.updateState(this);

        setView();

        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToMenuListener(this));
        sodaButton.setOnClickListener(new SodaScrollListener(this, sodaView, scrollView, sodaButton, coffeeButton, waterButton));
        coffeeButton.setOnClickListener(new CoffeeScrollListener(this, coffeeView, scrollView, sodaButton, coffeeButton, waterButton));
        waterButton.setOnClickListener(new WaterScrollListener(this, waterView, scrollView, sodaButton, coffeeButton, waterButton));

        colaButton.setOnClickListener(new SelectColaListener(this));
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), DrinkResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cola), DrinkResources.COLA_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.fanta), DrinkResources.FANTA_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.sprite), DrinkResources.SPRITE_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.latte), DrinkResources.LATTE_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.espresso), DrinkResources.ESPRESSO_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.still_water), DrinkResources.STILL_WATER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.soda_water), DrinkResources.SODA_WATER_PRICE);
    }
}
