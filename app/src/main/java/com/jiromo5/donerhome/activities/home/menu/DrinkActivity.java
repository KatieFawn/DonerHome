package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.DrinkScrollManager;
import com.jiromo5.donerhome.viewmodel.menu.listeners.*;

/**
 * Activity where the user can select a drink item from the menu.
 * Provides functionality to navigate back to the menu, scroll through categories,
 * and select a specific drink item.
 */

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

        // Initialize selected item as null when the activity is created
        selectedItem = null;
        Log.d("DrinkActivity", "Activity created. Selected item is null.");

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        sodaButton = findViewById(R.id.soda);
        coffeeButton = findViewById(R.id.coffee);
        waterButton = findViewById(R.id.water);
        scrollView = findViewById(R.id.drink_list);
        sodaView = findViewById(R.id.soda_category);
        coffeeView = findViewById(R.id.coffee_category);
        waterView = findViewById(R.id.water_category);
        colaButton = findViewById(R.id.cola);

        // Initialize the DrinkScrollManager and update the state
        drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState(this);
        Log.d("DrinkActivity", "DrinkScrollManager initialized and state updated.");

        // Set UI images
        setView();

        // Set button click listeners
        setButtonClickListeners();
    }

    /**
     * Sets the click listeners for the back button and the drink buttons (soda, coffee, water, cola).
     */

    private void setButtonClickListeners(){
        // Listener for back button to navigate back to the menu
        backButton.setOnClickListener(new BackToMenuListener(this));
        // Listeners for each category button to scroll to corresponding category
        sodaButton.setOnClickListener(new SodaScrollListener(this, sodaView, scrollView, sodaButton, coffeeButton, waterButton));
        coffeeButton.setOnClickListener(new CoffeeScrollListener(this, coffeeView, scrollView, sodaButton, coffeeButton, waterButton));
        waterButton.setOnClickListener(new WaterScrollListener(this, waterView, scrollView, sodaButton, coffeeButton, waterButton));

        // Listener for the cola button to select cola
        colaButton.setOnClickListener(new SelectColaListener(this));

        Log.d("DrinkActivity", "Button click listeners set.");
    }

    /**
     * Sets the images for the UI elements like buttons and icons.
     * Utilizes the ViewHandler class to update the screen with appropriate images.
     */
    private void setView() {
        ViewHandler viewHandler = new ViewHandler(this);

        // Set images for the drink options
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), DrinkResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cola), DrinkResources.COLA_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.fanta), DrinkResources.FANTA_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.sprite), DrinkResources.SPRITE_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.latte), DrinkResources.LATTE_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.espresso), DrinkResources.ESPRESSO_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.still_water), DrinkResources.STILL_WATER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.soda_water), DrinkResources.SODA_WATER_PRICE);

        Log.d("DrinkActivity", "UI images set.");
    }
}
