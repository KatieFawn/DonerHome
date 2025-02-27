package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.viewmodel.profile.UpdateAddressManager;
import com.jiromo5.donerhome.viewmodel.profile.listeners.BackToAddAddressActivity;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SaveAddressData;

public class UpdateAddressActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private TextView addressName;
    private TextView city;
    private TextView street;
    private TextView build;
    private TextView apartment;
    private TextView postalCode;

    private ImageButton saveButton;

    private NavigationBarController navigationBarController;
    private UpdateAddressManager updateAddressManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_address_activity);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);
        addressName = findViewById(R.id.address_name);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        build = findViewById(R.id.build);
        apartment = findViewById(R.id.apartment);
        postalCode = findViewById(R.id.postal_code);

        saveButton = findViewById(R.id.save_address);

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);

        updateAddressManager = new UpdateAddressManager(
                addressName,city, street, build, apartment, postalCode);
        updateAddressManager.updateData();

        setView();
        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        backButton.setOnClickListener(new BackToAddAddressActivity(this));
        saveButton.setOnTouchListener(new SaveAddressData(this, updateAddressManager));
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.save_address), ProfileResources.SAVE_ADDRESS_BUTTON);

    }
}
