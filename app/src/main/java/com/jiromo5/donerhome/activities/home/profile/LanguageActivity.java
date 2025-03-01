package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.profile.LanguageManager;
import com.jiromo5.donerhome.viewmodel.profile.listeners.*;

/**
 * Activity for selecting the application language.
 * Allows users to choose between English, German, and Polish.
 */
public class LanguageActivity extends AppCompatActivity {

    /** Back button for returning to the previous screen. */
    private ImageButton backButton;

    /** Buttons for selecting different languages. */
    private ImageButton englishButton;
    private ImageButton germanyButton;
    private ImageButton polandButton;

    /** Manages language selection state. */
    private LanguageManager languageManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity);
        overridePendingTransition(0,0);

        Log.d("LanguageActivity", "onCreate: Initializing UI components");

        backButton = findViewById(R.id.back_button);

        englishButton = findViewById(R.id.english);
        germanyButton = findViewById(R.id.german);
        polandButton = findViewById(R.id.poland);

        setView();
        setButtonClickListeners();

        languageManager = new LanguageManager(this, englishButton, germanyButton, polandButton);
        languageManager.updateState();
    }

    /**
     * Sets up click listeners for buttons.
     * Includes navigation back and language selection.
     */
    private void setButtonClickListeners() {
        Log.d("LanguageActivity", "setButtonClickListeners: Setting up button listeners");

        backButton.setOnClickListener(new BackClickListener(this));

        englishButton.setOnClickListener(new SelectEnglishListener(this));
        germanyButton.setOnClickListener(new SelectGermanyListener(this));
        polandButton.setOnClickListener(new SelectPolandListener(this));
    }

    /**
     * Configures UI by setting images for buttons.
     */
    private void setView(){
        Log.d("LanguageActivity", "setView: Setting up UI elements");

        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.english), ProfileResources.ENGLISH_BUTTON_SELECT);
        viewHandler.setImageOnScreen(findViewById(R.id.poland), ProfileResources.POLAND_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.german), ProfileResources.GERMAN_BUTTON);
    }
}
