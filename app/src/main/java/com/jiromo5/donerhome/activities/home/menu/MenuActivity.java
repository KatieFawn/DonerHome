package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.MenuResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.BurgerListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.DrinkListener;

public class MenuActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private ImageButton drinkButton;
    private ImageButton burgerButton;
    private ImageButton shawarmaButton;
    private ImageButton sauceButton;
    private ImageButton kebabButton;
    private ImageButton potatoButton;
    private ImageButton pizzaButton;
    private ImageButton hotdogButton;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        overridePendingTransition(0, 0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        drinkButton = findViewById(R.id.drink_button);
        burgerButton = findViewById(R.id.burger_button);
        shawarmaButton = findViewById(R.id.shawarma_button);
        sauceButton = findViewById(R.id.sauce_button);
        kebabButton = findViewById(R.id.kebab_button);
        potatoButton = findViewById(R.id.potato_button);
        pizzaButton = findViewById(R.id.pizza_button);
        hotdogButton = findViewById(R.id.hotdog_button);

        setView();
        homeEventListener();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState(this);
    }

    private void homeEventListener(){
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);

        drinkButton.setOnClickListener(new DrinkListener(this));
        burgerButton.setOnClickListener(new BurgerListener(this));
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(drinkButton, MenuResources.DRINK_BUTTON);
        viewHandler.setImageOnScreen(burgerButton, MenuResources.BURGER_BUTTON);
        viewHandler.setImageOnScreen(shawarmaButton, MenuResources.SHAWARMA_BUTTON);
        viewHandler.setImageOnScreen(sauceButton, MenuResources.SAUCE_BUTTON);
        viewHandler.setImageOnScreen(kebabButton, MenuResources.KEBAB_BUTTON);
        viewHandler.setImageOnScreen(potatoButton, MenuResources.POTATO_BUTTON);
        viewHandler.setImageOnScreen(pizzaButton, MenuResources.PIZZA_BUTTON);
        viewHandler.setImageOnScreen(hotdogButton, MenuResources.HOTDOG_BUTTON);
    }
}
