package com.jiromo5.donerhome.activities.main.profile;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.main.profile.LanguageManager;
import com.jiromo5.donerhome.main.profile.listeners.SelectEnglishListener;
import com.jiromo5.donerhome.main.profile.listeners.SelectGermanyListener;
import com.jiromo5.donerhome.main.profile.listeners.SelectPolandListener;

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
