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
 * SelectGermanyListener is responsible for setting the language preference to "German"
 * and navigating back to the LanguageActivity.
 */
public class SelectGermanyListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the context.
     *
     * @param context The context used for starting activities.
     */
    public SelectGermanyListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        // Set the language to German
        selectGermanLanguage();
        // Navigate to the LanguageActivity to reflect the language change
        replaceActivity();
    }

    /**
     * Sets the language preference to "German" in UserData.
     */
    private void selectGermanLanguage() {
        Log.d("SelectGermanyListener", "Language set to Germany");
        UserData.language = "germany"; // Set the language to German
    }

    /**
     * Navigates to the LanguageActivity after setting the language preference.
     */
    private void replaceActivity() {
        Log.d("SelectGermanyListener", "Navigating to LanguageActivity to update the view.");
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }
}
