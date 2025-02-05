package com.jiromo5.donerhome.service.auth;

import android.content.*;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

/**
 * A utility class to handle clickable spans within a TextView.
 * It allows part of the text to be clickable, leading to the specified activity.
 */
public class LinkClickHandler {

    private SpannableString spannableString;
    private TextView signUpTextView;
    private Context context;
    private String text;
    private int startLink;
    private Class<?> nextActivity;

    /**
     * Constructor to initialize the LinkClickHandler.
     *
     * @param signUpTextView The TextView where the link will be applied.
     * @param context The context used to start the activity.
     * @param text The full text that contains the link.
     */
    public LinkClickHandler(TextView signUpTextView, Context context, String text) {
        this.signUpTextView = signUpTextView;
        this.context = context;
        this.text = text;

        // Create a SpannableString from the provided text
        spannableString = new SpannableString(text);
    }

    /**
     * ClickableSpan implementation that defines the behavior when the link is clicked.
     * It starts the specified activity when the link is clicked.
     */
    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(@NonNull View view) {
            // Start the next activity when the link is clicked
            Log.d("LinkClickHandler", "User is click to link !");
            Intent intent = new Intent(context, nextActivity);
            context.startActivity(intent);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            // Underline the link text
            ds.setUnderlineText(true);
        }
    };

    /**
     * Configures the TextView to display the clickable link.
     * The link is applied from the specified start position to the end of the text.
     */
    public void configure() {
        // Apply the clickable span to the specified part of the text
        spannableString.setSpan(clickableSpan, startLink, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable text to the TextView
        signUpTextView.setText(spannableString);
        // Enable link movement method for the TextView
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * Sets the activity that should be started when the link is clicked.
     *
     * @param activity The class of the activity to start.
     */
    public void setNextActivity(Class<?> activity) {
        this.nextActivity = activity;
    }

    /**
     * Sets the start position of the clickable link within the text.
     *
     * @param start The start index of the link in the text.
     */
    public void setStartLink(int start) {
        this.startLink = start;
    }
}

