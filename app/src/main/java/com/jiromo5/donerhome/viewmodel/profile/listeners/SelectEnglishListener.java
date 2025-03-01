package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.LanguageActivity;
import com.jiromo5.donerhome.data.state.UserData;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * SelectEnglishListener is responsible for setting the language preference to "English"
 * and navigating back to the LanguageActivity.
 */
public class SelectEnglishListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the context.
     *
     * @param context The context used for starting activities.
     */
    public SelectEnglishListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        // Set the language to English
        selectEnglishLanguage();
        // Navigate to the LanguageActivity to reflect the language change
        replaceActivity();
    }

    /**
     * Sets the language preference to "English" in UserData.
     */
    private void selectEnglishLanguage() {
        Log.d("SelectEnglishListener", "Language set to English");
        UserData.language = "english"; // Set the language to English
    }

    /**
     * Navigates to the LanguageActivity after setting the language preference.
     */
    private void replaceActivity() {
        Log.d("SelectEnglishListener", "Navigating to LanguageActivity to update the view.");
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }
}

