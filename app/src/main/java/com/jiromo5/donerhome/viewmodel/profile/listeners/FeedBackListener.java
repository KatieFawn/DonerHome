package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.FeedBackActivity;

/**
 * FeedBackListener handles clicks on the feedback button,
 * navigating the user to the FeedBackActivity screen.
 */
public class FeedBackListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used for starting the new activity.
     */
    public FeedBackListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.d("FeedBackListener", "Feedback button clicked");
        replaceActivity();
    }

    /**
     * Navigates to the FeedBackActivity screen.
     */
    private void replaceActivity(){
        Log.d("FeedBackListener", "Navigating to FeedBackActivity");
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }
}

