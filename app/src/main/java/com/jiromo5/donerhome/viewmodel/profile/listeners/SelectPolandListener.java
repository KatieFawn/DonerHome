package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.LanguageActivity;
import com.jiromo5.donerhome.data.state.UserData;

import android.util.Log;

/**
 * SelectPolandListener is responsible for setting the language preference to "Polish"
 * and navigating to the LanguageActivity to reflect the language change.
 */
public class SelectPolandListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the context.
     *
     * @param context The context used for starting activities.
     */
    public SelectPolandListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        // Set the language to Polish
        selectPolandLanguage();
        // Navigate to the LanguageActivity to reflect the language change
        replaceActivity();
    }

    /**
     * Sets the language preference to "Polish" in UserData.
     */
    private void selectPolandLanguage() {
        Log.d("SelectPolandListener", "Language set to Poland");
        UserData.language = "poland"; // Set the language to Polish
    }

    /**
     * Navigates to the LanguageActivity after setting the language preference.
     */
    private void replaceActivity() {
        Log.d("SelectPolandListener", "Navigating to LanguageActivity to update the view.");
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }
}
