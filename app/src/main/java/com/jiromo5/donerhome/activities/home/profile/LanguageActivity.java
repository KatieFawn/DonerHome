package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.profile.LanguageManager;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SelectEnglishListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SelectGermanyListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SelectPolandListener;

public class LanguageActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ImageButton englishButton;
    private ImageButton germanyButton;
    private ImageButton polandButton;

    private LanguageManager languageManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity);
        overridePendingTransition(0,0);

        backButton = findViewById(R.id.back_button);

        englishButton = findViewById(R.id.english);
        germanyButton = findViewById(R.id.german);
        polandButton = findViewById(R.id.poland);

        setView();
        setButtonClickListeners();

        languageManager = new LanguageManager(this, englishButton, germanyButton, polandButton);
        languageManager.updateState();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackClickListener(this));

        englishButton.setOnClickListener(new SelectEnglishListener(this));
        germanyButton.setOnClickListener(new SelectGermanyListener(this));
        polandButton.setOnClickListener(new SelectPolandListener(this));
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.english), ProfileResources.ENGLISH_BUTTON_SELECT);
        viewHandler.setImageOnScreen(findViewById(R.id.poland), ProfileResources.POLAND_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.german), ProfileResources.GERMAN_BUTTON);
    }
}
