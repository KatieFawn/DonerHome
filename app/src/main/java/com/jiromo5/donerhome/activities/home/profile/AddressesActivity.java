package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.viewmodel.profile.AddressManager;
import com.jiromo5.donerhome.viewmodel.profile.listeners.AddAddressListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.RemoveAddressListener;

public class AddressesActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private ImageButton[] addresses;
    private ImageButton[] deleteAddress;
    private TextView[] addressName;
    private ImageButton addAddress;

    private NavigationBarController navigationBarController;
    private AddressManager addressManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addresses_activity);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);

        addresses = new ImageButton[5];
        deleteAddress = new ImageButton[5];
        addressName = new TextView[5];

        addresses[0] = findViewById(R.id.address1);
        deleteAddress[0] = findViewById(R.id.remove_button1);
        addressName[0] = findViewById(R.id.name1);

        addresses[1] = findViewById(R.id.address2);
        deleteAddress[1] = findViewById(R.id.remove_button2);
        addressName[1] = findViewById(R.id.name2);

        addresses[2] = findViewById(R.id.address3);
        deleteAddress[2] = findViewById(R.id.remove_button3);
        addressName[2] = findViewById(R.id.name3);

        addresses[3] = findViewById(R.id.address4);
        deleteAddress[3] = findViewById(R.id.remove_button4);
        addressName[3] = findViewById(R.id.name4);

        addresses[4] = findViewById(R.id.address5);
        deleteAddress[4] = findViewById(R.id.remove_button5);
        addressName[4] = findViewById(R.id.name5);

        addAddress = findViewById(R.id.add_address);

        setView();
        setButtonClickListeners();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);

        addressManager = new AddressManager(addresses, deleteAddress,addressName);
        addressManager.nameSettings();
        addressManager.visibilityHandler();
        addressManager.lockAddButton(addAddress);

        AddressManager.clickedButtonNumber = -1;
    }

    private void setButtonClickListeners(){
        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        backButton.setOnClickListener(new BackClickListener(this));

        deleteAddress[0].setOnClickListener(new RemoveAddressListener(this, 0));
        deleteAddress[1].setOnClickListener(new RemoveAddressListener(this, 1));
        deleteAddress[2].setOnClickListener(new RemoveAddressListener(this, 2));
        deleteAddress[3].setOnClickListener(new RemoveAddressListener(this, 3));
        deleteAddress[4].setOnClickListener(new RemoveAddressListener(this, 4));

        addresses[0].setOnTouchListener(new AddAddressListener(this));
        addresses[1].setOnTouchListener(new AddAddressListener(this));
        addresses[2].setOnTouchListener(new AddAddressListener(this));
        addresses[3].setOnTouchListener(new AddAddressListener(this));
        addresses[4].setOnTouchListener(new AddAddressListener(this));

        addAddress.setOnTouchListener(new AddAddressListener(this));
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.add_address), ProfileResources.ADD_ADDRESS_BUTTON);

        for(int i = 0; i < addresses.length; i ++){
            viewHandler.setImageOnScreen(addresses[i], ProfileResources.ADDRESS);
            viewHandler.setImageOnScreen(deleteAddress[i], ProfileResources.DELETE_BUTTON);
        }
    }
}
