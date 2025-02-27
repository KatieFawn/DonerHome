package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.listeners.BackToMenuListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.SelectCheeseBurgerListener;

public class BurgerActivity extends AppCompatActivity {

    public static String selectedItem;

    private ImageButton backButton;
    private ImageButton cheeseburger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.burger_activity);
        overridePendingTransition(0, 0);

        selectedItem = null;

        backButton = findViewById(R.id.back_button);
        cheeseburger = findViewById(R.id.cheeseburger);

        setView();

        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToMenuListener(this));
        cheeseburger.setOnClickListener(new SelectCheeseBurgerListener(this));
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), DrinkResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cheeseburger), BurgerResources.CHEESEBURGER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.double_cheeseburger), BurgerResources.DOUBLE_CHEESEBURGER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.hamburger), BurgerResources.HAMBURGER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.king_burger), BurgerResources.KING_BURGER_PRICE);
    }
}