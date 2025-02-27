package com.jiromo5.donerhome.activities.home.shopping;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.viewmodel.shopping.CartManager;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.BuyButtonListener;

public class CartActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private ImageView cartLogo;
    private TextView welcome;
    private LinearLayout orderList;

    private TextView totalPrice;
    private ImageButton buyButton;

    private NavigationBarController navigationBarController;
    private CartManager cartManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);
        welcome = findViewById(R.id.welcome);
        cartLogo = findViewById(R.id.cart_logo);
        orderList = findViewById(R.id.order_list);
        totalPrice = findViewById(R.id.total_price);
        buyButton = findViewById(R.id.buy_button);

        cartManager = new CartManager(this, welcome, cartLogo, totalPrice, buyButton);
        cartManager.addItemToCart(orderList);
        cartManager.removeItemFromCart(orderList);
        cartManager.toggleVisibility();

        setView();

        cartEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState(this);
    }

    private void cartEventHandler(){
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);
        BuyButtonListener buyButtonListener = new BuyButtonListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);
        buyButton.setOnTouchListener(buyButtonListener);
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.cart_logo), CartResources.BACKGROUND_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.buy_button), CartResources.BUY_BUTTON_IMAGE);
    }
}
