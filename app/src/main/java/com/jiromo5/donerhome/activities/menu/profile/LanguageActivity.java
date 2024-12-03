package com.jiromo5.donerhome.activities.menu.profile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.auth.BackClickListener;
import com.jiromo5.donerhome.menu.navigation.NavigationBarController;
import com.jiromo5.donerhome.menu.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.menu.profile.AddressManager;
import com.jiromo5.donerhome.menu.profile.LanguageManager;
import com.jiromo5.donerhome.menu.profile.listeners.AddAddressListener;
import com.jiromo5.donerhome.menu.profile.listeners.RemoveAddressListener;
import com.jiromo5.donerhome.menu.profile.listeners.SelectEnglishListener;
import com.jiromo5.donerhome.menu.profile.listeners.SelectGermanyListener;
import com.jiromo5.donerhome.menu.profile.listeners.SelectPolandListener;

public class LanguageActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ImageButton englishButton;
    private ImageButton germanyButton;
    private ImageButton polandButton;

    private LanguageManager languageManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        overridePendingTransition(0,0);

        backButton = findViewById(R.id.back_button);

        englishButton = findViewById(R.id.english);
        germanyButton = findViewById(R.id.german);
        polandButton = findViewById(R.id.poland);

        setButtonClickListeners();

        languageManager = new LanguageManager(englishButton, germanyButton, polandButton);
        languageManager.updateState();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackClickListener(this));

        englishButton.setOnClickListener(new SelectEnglishListener(this));
        germanyButton.setOnClickListener(new SelectGermanyListener(this));
        polandButton.setOnClickListener(new SelectPolandListener(this));
    }
}
