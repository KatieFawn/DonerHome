package com.jiromo5.donerhome.viewmodel.profile;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;

import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

/**
 * LanguageManager class is responsible for managing the state of language selection buttons
 * (English, German, and Polish). It updates the UI based on the selected language and disables
 * the button for the currently selected language.
 */
public class LanguageManager {

    // Context for accessing resources and UI elements.
    private Context context;

    // Image buttons for each language option (English, Germany, and Poland).
    private ImageButton englishButton;
    private ImageButton germanyButton;
    private ImageButton polandButton;

    /**
     * Constructor for initializing the LanguageManager.
     *
     * @param context The context to access resources and UI elements.
     * @param englishButton The button for selecting English language.
     * @param germanyButton The button for selecting German language.
     * @param polandButton The button for selecting Polish language.
     */
    public LanguageManager(Context context, ImageButton englishButton, ImageButton germanyButton, ImageButton polandButton){
        this.context = context;
        this.englishButton = englishButton;
        this.germanyButton = germanyButton;
        this.polandButton = polandButton;
    }

    /**
     * Updates the state of the language selection buttons based on the selected language.
     * The button of the selected language will be displayed as selected and disabled.
     *
     * It uses the UserData.language to determine the selected language and update the UI accordingly.
     */
    public void updateState(){
        ViewHandler viewHandler = new ViewHandler(context);

        // Check for English language selection
        if (UserData.language.equals("english")) {
            viewHandler.setImageOnScreen(englishButton, ProfileResources.ENGLISH_BUTTON_SELECT);
            englishButton.setEnabled(false);
            Log.d("LanguageManager", "English language selected. Button disabled.");
        } else {
            viewHandler.setImageOnScreen(englishButton, ProfileResources.ENGLISH_BUTTON);
        }

        // Check for German language selection
        if (UserData.language.equals("germany")) {
            viewHandler.setImageOnScreen(germanyButton, ProfileResources.GERMAN_BUTTON_SELECT);
            germanyButton.setEnabled(false);
            Log.d("LanguageManager", "German language selected. Button disabled.");
        } else {
            viewHandler.setImageOnScreen(germanyButton, ProfileResources.GERMAN_BUTTON);
        }

        // Check for Polish language selection
        if (UserData.language.equals("poland")) {
            viewHandler.setImageOnScreen(polandButton, ProfileResources.POLAND_BUTTON_SELECT);
            polandButton.setEnabled(false);
            Log.d("LanguageManager", "Polish language selected. Button disabled.");
        } else {
            viewHandler.setImageOnScreen(polandButton, ProfileResources.POLAND_BUTTON);
        }
    }
}

